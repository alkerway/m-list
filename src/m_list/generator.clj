(ns m-list.generator
    (:require [rum.core :as rum]))

(rum/defc wrapper []
    (let [directory (clojure.java.io/file "./public")
            files (file-seq directory)]
    (println (take 10 files)))
    [:h1 "suuu"])

(defn getAudio []
    (rum/render-html (wrapper)))