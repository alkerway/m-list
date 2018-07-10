(ns m-list.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [m-list.generator :as generator]))

(defroutes app
  (GET "/" [] (generator/getAudio))
  (route/not-found "<h1>Page not found</h1>"))