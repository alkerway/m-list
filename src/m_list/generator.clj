(ns m-list.generator
    (:require [rum.core :as rum]))

(rum/defc wrapper []
    (println)
    (println)
    (let [names (.list (clojure.java.io/file "./public"))]
    (println (count names)))
    ; (println files))
    [:h1 "suuu"])

(defn getAudio []
    (rum/render-html (wrapper)))