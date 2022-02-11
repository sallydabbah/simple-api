(ns home-task.common-test
  (:require [clojure.test :refer :all]
            [home-task.utils.common :refer :all]))

(deftest convert-time-to-record-format-test
  (testing "convert epoch time to  "
    (let [time 1347845034
          expected-res "2012-09-17T01:23:54.000Z"]
      (is (= expected-res (convert-time-to-record-format time))))))

(deftest break-time-test
  (testing "break time to json"
    (let [time "2012-09-17T01:23:54.000Z"
          expected-res {:year    "2012"
                        :month   "09"
                        :day     "17"
                        :hours   "01"
                        :minutes "23"
                        :seconds "54.000"}]
      (is (= expected-res (break-time time))))))

(deftest calculate-succisive-test
  (testing "calculate-succisive testing"
    (let [data [{
                 :visitor_id    "111"
                 :site_url      "22"
                 :page_view_url ""
                 :timestamp     {:year    "2012"
                                 :month   "09"
                                 :day     "17"
                                 :hours   "01"
                                 :minutes "23"
                                 :seconds "54.000"

                                 }},
                {
                 :visitor_id    "111"
                 :site_url      "22"
                 :page_view_url ""
                 :timestamp     {:year    "2012"
                                 :month   "09"
                                 :day     "17"
                                 :hours   "01"
                                 :minutes "23"
                                 :seconds "12.000"}}]
          expected [-42.0]]
      (= expected (calculate-succisive data)))))

(deftest aggregate-results-test
  (testing "aggregate-results testing"
    (let [data [-1.0 -3.0 23.0 -7.0]
          expected-res 34.0]
      (= expected-res (aggregate-results data)))))