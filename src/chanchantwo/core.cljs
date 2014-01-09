(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [dommy.macros :refer [sel1 deftemplate]])
  (:require [clojure.string :as string :refer [split blank?]]
            [cljs.core.async :as async :refer [chan <! close!]]
            [goog.net.XhrIo :as xhr]
            [dommy.core :as dommy]
            [dommy.template :as template]
            [markdown.core :as md]))

(def blog-content-url "http://localhost:8000/blog.md")

(defn log [s] (.log js/console (str s)))

(defn GET [url]
  "HTTP GET - returns channel on which the response will be sent"
  (let [ch (chan 1)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (go (>! ch res)
                      (close! ch)))))
    ch))

(defn split-posts [src]
  "Split a string separated by '---'.  Also removes empty results."
  (remove blank? (split src #"---")))

(defn parse-metadata [metadata]
  "Transform string of yaml-style metadata into a map of keyword to value"
  (let [string-kvps (->> metadata
                         (string/split-lines)
                         (map #(string/split % #":" 2)))]
    ;string-kvps))
    (into {} (for [[k v] string-kvps :when (not (blank? v))]
               [(keyword k) (string/trim v)]))))

; Template for a post.  Returns Dom Object representing a single post.
(deftemplate post-template [metadata content]
  (let [md-post (-> content (md/mdToHtml) (template/html->nodes))]
    [:section
     [:h3 (:title metadata)]
     [:p md-post]]))

(defn transform-posts [post-data post-content]
  (let [metadata (parse-metadata post-data)]
  ;(let [metadata post-data]
    (post-template metadata post-content)))

(defn append-post! [post]
  "Insert a post into the page"
    (dommy/append! (sel1 :#blogapp) post))

; Entry point
; Request the blog contents, await the response and then present them.
(go
  (let [blog-src (<! (GET blog-content-url))
        src-posts (split-posts blog-src)
        posts (->> (partition 2 src-posts)
                   (map #(transform-posts (first %) (second %))))]
    (doall (map #(append-post! %) posts))))
