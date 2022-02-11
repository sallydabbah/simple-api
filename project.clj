(defproject home-task "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[fmnoise/flow "4.0.0"]
                 [org.clojure/clojure "1.9.0"]
                 [http-kit "2.3.0"]
                 [org.postgresql/postgresql "9.4-1204-jdbc41"]
                 [cheshire "5.10.0"]
                 [honeysql "1.0.444"]
                 [com.mchange/c3p0 "0.9.5.5"]
                 [compojure "1.6.0"]
                 [amalloy/ring-gzip-middleware "0.1.3"]
                 [ring/ring-json "0.4.0"]
                 [mount "0.1.16"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [javax.servlet/servlet-api "2.5"]
                 [nilenso/honeysql-postgres "0.2.6"]
                 [org.clojure/core.async "1.2.603"]
                 [org.clojure/core.match "0.3.0"]
                 [com.taoensso/carmine "2.16.0"]
                 [javax.annotation/javax.annotation-api "1.3.2"]
                 [javax.xml.bind/jaxb-api "2.3.1"]
                 [clj-time "0.15.1"]]
  :repl-options {:init-ns home-task.core})
