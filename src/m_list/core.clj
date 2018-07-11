(ns m-list.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
	    [ring.middleware.file :refer [wrap-file]]
            [m-list.generator :as generator]))

(defroutes app-routes
  (GET "/m/" [] (generator/getAudio))
  (route/not-found "<h1>Page not found</h1>"))

(def app
	(-> app-routes
            (wrap-file "public" {})))
