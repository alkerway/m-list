(defproject m-list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [compojure "1.6.1"]
    [ring/ring-core "1.7.0-RC1"]
    [ring/ring-jetty-adapter "1.6.3"]
    [ring/ring-defaults "0.3.2"]
    [rum "0.11.2" :exclusions [cljsjs/react cljsjs/react-dom sablono]]]

  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler m-list.core/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
