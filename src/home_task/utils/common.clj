(ns home-task.utils.common
  (:require [clojure.string :as clj-str]
            [clojure.walk :as clj-wlk]
            [clojure.string :as string]
            [clj-time.format]
            [clj-time.coerce]))

(def headers [:visitor_id, :site_url, :page_view_url, :timestamp])
(def group-keys [:visitor_id, :site_url :page_view_url])

;TODO For Supporting large scale input:
; when we want to scale up this code, we should consider parallelize data
; everytime we use map,we should consider using pmap , this will reduce time significantly
; Regarding data : we should store this data in non-rational DB
; that works well with a high volume of data such as Druid and pull data instead of saving data under Resource Folder.
; we can work with a smaller group of data ,such as parallelize in threads every 6 hours to run separately.
(defn load-csv
  "Attempts to open a csv file and complains if the file is not present."
  [file-name]
  (let [file-data (try
                    (slurp file-name)
                    (catch Exception e (println (.getMessage e))))]
    file-data))

(defn parse-csv-response [raw-data]
  "Parse CSV data ,convert it to json format with relevant headers listed above."
  (try
    (let [rows (clj-str/split-lines raw-data)]
      (if (not-empty rows)
        (->> (map (comp #(zipmap headers %) #(clj-str/split % #",")) rows)
             (map #(clj-wlk/keywordize-keys %)))
        rows))
    (catch Exception e
      (print "Error occurred while parsing body data." (ex-data e)))))

(defn get-data [path]
  (-> path
      (load-csv)
      (parse-csv-response)))

(defn convert-time-to-record-format [time]
  (let [time (* time 1000)
        format (clj-time.format/formatters :date-time)]
    (clj-time.format/unparse format (clj-time.coerce/from-long time))))

(defn break-time [date]
  "Break time to json ,time format yyyy-mm-ddTHH:MM:SS.000Z"
  (let [[year month day-info] (string/split date #"-")
        day (string/split day-info #"T")
        [hours minutes seconds] (string/split (last day) #":")]
    {:year    year
     :month   month
     :day     (first day)
     :hours   hours
     :minutes minutes
     :seconds (string/join "" (drop-last seconds))}))

(defn normalize-timestamp [site-data]
  (map #(assoc % :timestamp (-> (:timestamp %)
                                (Integer/parseInt)          ; epoch string to int
                                (convert-time-to-record-format)
                                (break-time))) site-data))

(defn convert-time-to-minutes [record]
  (let [{:keys [hours minutes]} (:timestamp record)
        hours (Float/parseFloat hours)
        minutes (Float/parseFloat minutes)]
    (+ (* hours 60) minutes)))

(defn calculate-succisive [records]
  (let [data (map #(convert-time-to-minutes %) records)]
    (map - (rest data) data)))

(defn filter-successive-values [records]
  "Drop minutes successive values that are bigger than 30."
  (flatten (remove empty? (mapv (fn [values] (filter (fn [value]
                                                        (if (< value 0)
                                                          (< (* value -1) 30.0)
                                                          (< value 30.0))) values)) records))))

(defn aggregate-results [results]
  (reduce (fn [result input]
            (let [input (if (< input 0) (* -1 input) input)]
              (+ result input))) 0 results))

(defn format-number [number]
  "Take 2 digits after decimal."
  (float (/ (int (* 100 number)) 100)))

(defn session-info [data site-url]
  (let [site-data(filter #(= (:site_url %) site-url) data)
        normalized-data (normalize-timestamp site-data)
        grouped-data (group-by #(select-keys % group-keys) normalized-data)
        filtered-data (filter (fn [[_ v]] (> (count v) 1)) grouped-data)
        successive-values (map (fn [[_ v]] (calculate-succisive v)) filtered-data)
        filtered-successive-values (filter-successive-values successive-values)
        aggregated-sum (aggregate-results filtered-successive-values)
        num-of-sessions (count filtered-successive-values)]
    {:aggregated-sum  aggregated-sum
     :num-of-sessions num-of-sessions}))

(defn calculate-median [data site-url]
  (let [{:keys [aggregated-sum num-of-sessions]} (session-info data site-url)]
    (-> (/ aggregated-sum num-of-sessions) format-number)))

(defn unique-visited-sites [data vistor-id]
  (let [visitor-data (filter #(= (:visitor_id %) vistor-id) data)
        grouped-data (group-by #(select-keys % group-keys) visitor-data)]
    (count grouped-data)))