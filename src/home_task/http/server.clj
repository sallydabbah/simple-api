(ns home-task.http.server
  (:require [org.httpkit.server :as s]
            [home-task.http.handlers :as handler]
            [compojure.route :refer [not-found]]
            [mount.core :refer [defstate]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [compojure.core :refer [defroutes routes GET POST PUT DELETE]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [compojure.handler :refer [site]]))

(defroutes gen-routes
           (GET "/health-check" []
             (handler/http-response 200 "ok"))
           (GET "/get-num-of-sessions" [site-url]
             (handler/get-num-of-sessions site-url))
           (GET "/get-median-session-length" [site-url]
             (handler/get-median-session-length site-url))
           (GET "/get-unique-visited-sites" [visitor-id]
             (handler/get-unique-visited-sites visitor-id)))

(defn- create-routes []
  (try
    (routes gen-routes)
    (catch Exception e
      (print "Exception occurred in http server:" (.getMessage e))
      (handler/http-response 500 (str "ring handler error: " (.getMessage e))))))

(defn- create-server []
  (s/run-server (-> (site (create-routes))
                    (wrap-json-body {:keywords? true})
                    (wrap-gzip))
                {:port 8080 :thread 50}))

(defstate http-server
          :start (create-server))