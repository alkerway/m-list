(ns m-list.generator
    (:require [rum.core :as rum]))

(rum/defc wrapper []
    (println)
    (println)
    (let [names (.list (clojure.java.io/file "./public"))]
        (println (count names))
        ; (println files))
        (for [eachFile names]
            [:div {:style {:margin "10px 0 30px 0"}} eachFile
            [:audio {:style {:display "block"} :src eachFile :controls true}]])))

(defn getAudio []
    (rum/render-html (wrapper)))