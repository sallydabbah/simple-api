(ns home-task.http.handlers
  (:require [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [cheshire.core :as json]
            [home-task.utils.common :refer [get-data session-info calculate-median unique-visited-sites]]))
(defonce input_1_path "/Users/sallydabbah/Desktop/home-task/resources/input_1.csv")

(defn http-response [status body]
  {:status  status
   :headers {"Content-Type" "application/json"}
   :body    (json/generate-string body)})

(defn get-num-of-sessions [site-url]
  (let [data (get-data input_1_path)
        num-of-sessions (:num-of-sessions (session-info data site-url))]
    (http-response 200 (str "Num sessions for site " site-url " = " num-of-sessions))))

(defn get-median-session-length [site-url]
  (let [data (get-data input_1_path)
        median-value (calculate-median data site-url)]
    (http-response 200 (str " median session length = " median-value))))

(defn get-unique-visited-sites [visitor-id]
  (let [data (get-data input_1_path)
        num-unique-sites (unique-visited-sites data visitor-id)]
    (http-response 200 (str "Num of unique sites for " visitor-id " = " num-unique-sites))))