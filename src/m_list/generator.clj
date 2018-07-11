(ns m-list.generator
    (:require 
	[rum.core :as rum]
	[clojure.string :as cljstr]))

(def audioFormats ["wav" "flac" "mp3" "aac"])
(def videoFormats ["mkv" "m3u8" "mov" "mp4" "flv" "avi"])

(rum/defc wrapper []
    (let [names (.list (clojure.java.io/file "./public/m"))]
        (for [eachFile names]
	    (let [extension (last (cljstr/split eachFile #"\."))]
	            [:div {:style {:padding "10px 0 10px 0" :border-bottom "1px solid black"}} eachFile
	            (if (some #(= % extension) audioFormats)
			[:audio {:style {:display "block"} :src eachFile :controls true}]
		    (if (some #(= % extension) videoFormats)
			[:video {:style {:display "block"} :src eachFile :controls true}]))]))))

(defn getAudio []
    (rum/render-html (wrapper)))
