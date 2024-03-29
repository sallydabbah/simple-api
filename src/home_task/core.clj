(ns home-task.core
  (:require [org.httpkit.server :refer [run-server]]
            [compojure.core :refer :all]
            [home-task.http.server :refer [http-server]]
            [mount.core :refer [only start with-args only]]))

(defn home-task [mode]
  (-> (only #{#'http-server})
      (with-args {:mode mode})))

(defn -main [& mode]
  (print "start api-server service in mode:")
  (start (home-task (first mode))))
