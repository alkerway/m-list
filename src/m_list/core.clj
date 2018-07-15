(ns m-list.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [m-list.generator :as generator]))

(defn upload-file [file params]
  (println)
  (println)
  (println params)
  (let [file-name (:filename file)]
      (do
          (println file)
          (clojure.java.io/copy (:tempfile file) (clojure.java.io/file "./public" "m" file-name))
          {:status 200})))

(defn handleUpload [request]
  (println)
  (println)
  (println request) {:status 200})

(defroutes app-routes
  (GET "/m/" [] (generator/getFiles))
  ; (POST "/upload" {params :params}
  ;   (upload-file (get params "file") params))
  (POST "/upload" [req] handleUpload)
  (route/not-found "Not found"))

(def app
  (-> app-routes
    wrap-params
    (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
    (wrap-file "public" {})))
