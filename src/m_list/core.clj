(ns m-list.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.params :refer [wrap-params]]
            [m-list.generator :as generator]))

(defn upload-file [file]
  (let [file-name (file :filename)
        size (file :size)]
      (do
          (println file)
          (clojure.java.io/copy (:tempfile file) (clojure.java.io/file "./public" "m" file-name))
          {:status 200 :headers {"Content-Type" "text/html"} :body (str file-name size)})))

(defroutes app-routes
  (GET "/m/" [] (generator/getFiles))
  (POST "/upload" {params :params}
    (let [file (get params "file")] (upload-file file)))
  (route/not-found "Not found"))

(def app
  (-> app-routes
    wrap-params
    wrap-multipart-params
    (wrap-file "public" {})))
