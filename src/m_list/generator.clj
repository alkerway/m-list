(ns m-list.generator
    (:require 
	[rum.core :as rum]
	[clojure.string :as cljstr]))

(def audioFormats ["wav" "flac" "mp3" "aac"])
(def videoFormats ["mkv" "mov" "mp4" "flv" "avi"])

(rum/defc file-upload-ui []
	[:form {:enctype "multipart/form-data" :method "post" :action "/upload"} 
		[:input {:type "file"}]
		[:input {:type "submit" :value "Upload"}]])

(rum/defc file-list []
	(let [names (.list (clojure.java.io/file "./public/m"))]
		(for [eachFile names]
		(let [extension (last (cljstr/split eachFile #"\."))]
				[:div {:style {:padding "10px 0 10px 0" :border-bottom "1px solid black"}} eachFile
				(if (some #(= % extension) audioFormats)
					[:audio {:style {:display "block"} :src eachFile :controls true}]
				(if (some #(= % extension) videoFormats)
					[:video {:style {:display "block" :height "240px"} :src eachFile :controls true}]
					[:a {:style {:display "block"} :href eachFile :target "_blank"} "link"]))]))))

(rum/defc wrapper []
	[:div (file-list) (file-upload-ui)])

(defn getFiles []
    (rum/render-html (wrapper)))
