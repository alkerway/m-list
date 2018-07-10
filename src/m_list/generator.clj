(ns m-list.generator
    (:require [rum.core :as rum]))

(rum/defc wrapper []
    (let [names (.list (clojure.java.io/file "./public"))]
        (for [eachFile names]
            [:div {:style {:padding "10px 0 10px 0" :border-bottom "1px solid black"}} eachFile
            [:audio {:style {:display "block"} :src eachFile :controls true}]])))

(defn getAudio []
    (rum/render-html (wrapper)))