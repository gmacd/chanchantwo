(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]]
                   [dommy.macros :refer [sel1 deftemplate]])
  (:require [clojure.string :refer [split blank?]]
        		[goog.events :as events]
            [goog.net.XhrIo :as xhr]
            [cljs.core.async :as async :refer [put! chan <! close!]]
            [dommy.core :as dommy])
  (:import [goog.net Jsonp]
           [goog Uri]))

(def blog-content-url "http://localhost:8000/blog.md")

(defn elem [id] (.getElementById js/document id))

(defn log [s] (.log js/console (str s)))

(defn listen [el type]
  (let [out (chan)]
    (events/listen el type
      (fn [e] (put! out e)))
    out))

(defn GET [url]
  (let [ch (chan 1)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (go (>! ch res)
                      (close! ch)))))
    ch))

(defn split-posts [src]
  (let [sections (remove blank? (split src #"---"))]
    (log (str "1) " (first sections)))
    (log (str "2) " (second sections)))
    sections))

(deftemplate post-template [metadata content]
  [:p content])

(defn transform-posts [post-data post-content]
  (post-template post-data post-content))

(defn append-post! [post]
  (dommy/append! (sel1 :#blogapp) post))

(go
  (let [posts-elem (elem "blog-contents")
        blog-src (<! (GET blog-content-url))
        src-posts (split-posts blog-src)
        posts (->> (partition 2 src-posts) (map #(transform-posts (first %) (second %))))]
    (doall (map #(append-post! %) posts))))
