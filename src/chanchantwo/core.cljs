(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [dommy.macros :refer [sel1 deftemplate]])
  (:require [clojure.string :as string :refer [split blank?]]
            [cljs.core.async :as async :refer [chan <! close!]]
            [goog.net.XhrIo :as xhr]
            [dommy.core :as dommy]
            [dommy.template :as template]
            [markdown.core :as md]
            [cemerick.url :refer (url-decode query->map)]))


(def blog-config-url "/blog-config.yaml")
(def blog-content-url "/blog.md")


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


(defn keywordize-map [string-map]
  "Convert string keys to keywords"
  (into {} (for [[k v] string-map]
             [(keyword k) v])))


(defn split-url [url]
  "Split URL into the path and query"
  (let [[resource query] (take 2 (-> (split url #"[?]")
                                     (concat (repeat ""))))]
    [resource (-> query (query->map) (keywordize-map))]))


(defn split-posts [src]
  "Split a string separated by '---'.  Also removes empty results."
  (remove blank? (split src #"---")))


(defn parse-metadata [metadata]
  "Transform string of yaml-style metadata into a map of keyword to value"
  (let [string-kvps (->> metadata
                         (string/split-lines)
                         (map #(string/split % #":" 2)))]
    (into {} (for [[k v] string-kvps :when (not (blank? v))]
               [(keyword k) (string/trim v)]))))


; Template for a post title.  Returns Dom Object representing a
; single post title.
(deftemplate post-title-template [post]
  (let [title ((post :metadata) :title)]
    [:h3 title]))


; Template for a post.  Returns Dom Object representing a single post.
(deftemplate post-template [post]
  (let [title ((post :metadata) :title)
        content (post :content)
        md-post (-> content (md/mdToHtml) (template/html->nodes))]
    [:article
     [:header [:h3 title]]
     [:p md-post]]))


;(defn transform-posts [post-data post-content]
;  (let [metadata (parse-metadata post-data)]
  ;(let [metadata post-data]
;    (post-template metadata post-content)))


(defn append-post! [post]
  "Insert a post into the page"
  (dommy/append! (sel1 :#blogapp)
                 (post-template post)))


(defn append-post-title! [post]
  "Insert a post title into the page"
  (dommy/append! (sel1 :#blogapp)
                 (post-title-template post)))


(deftemplate page-title [metadata]
  [:header
   [:h1 (:title metadata)]])

(defn apply-title! [metadata]
  "Apply the blog title"
  (dommy/append! (sel1 :#title) (page-title metadata)))


(defn fetch-blog-config []
  "Request the config, returning it on a channel"
  (let [ch (chan 1)]
    (go (let [config (<! (GET blog-config-url))
              metadata (parse-metadata config)]
          (>! ch metadata)
          (close! ch)))
    ch))


(defn fetch-blog-contents []
  "Request the blog contents, returning it on a channel.
   Channel returns a seq of records with :metadata and :content"
  (let [ch (chan 1)]
    (go (let [blog-contents (<! (GET blog-content-url))]
          (>! ch
              (->> blog-contents
                   (split-posts)
                   (partition 2)
                   (map #(hash-map :metadata (parse-metadata (first %))
                                   :content (second %))))))
          (close! ch))
    ch))


(defn show-front-page []
  "Grab the config and the blog content and display the titles"
  (go (let [config (<! (fetch-blog-config))]
        (apply-title! config)))
  (go (let [posts (<! (fetch-blog-contents))]
        (doall (map #(append-post-title! %) (reverse posts))))))

;(defn show-post [title-id]
;  (log (str "Showing post: " title-id)))

;(defn show-posts-for-tag [tag]
;  (log (str "Showing posts for tag: " tag)))


;(def url (.-URL js/document))
(show-front-page)
