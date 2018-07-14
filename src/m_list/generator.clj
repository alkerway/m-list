(ns m-list.generator
    (:require 
	[rum.core :as rum]
	[clojure.string :as cljstr]))

(def audioFormats ["wav" "flac" "mp3" "aac" "ogg"])
(def videoFormats ["mkv" "mov" "mp4" "flv" "avi"])

(rum/defc file-upload-ui []
	[:form {:enctype "multipart/form-data" :method "post" :action "/upload"} 
		[:input {:type "file"}]
		[:input {:type "submit" :value "Upload"}]])

(rum/defc file-list []
	(let [names (.list (clojure.java.io/file "./public/m"))]
		(for [eachFile names]
			(let [extension (last (cljstr/split eachFile #"\."))
				  isVideo (some #(= % extension) videoFormats)
				  isAudio (some #(= % extension) audioFormats)]
				[:div {:style {:padding "10px 0 10px 0" :border-bottom "1px solid black"}} eachFile
					[(if isVideo :video (if isAudio :audio :a))
					{:style {:display "block" :height (if isVideo "240px" nil)}
						:src eachFile :controls true :href eachFile :target "_blank"} "link"]]))))

(rum/defc wrapper []
	[:div (file-list) (file-upload-ui)])

(defn getFiles []
    (rum/render-html (wrapper)))
