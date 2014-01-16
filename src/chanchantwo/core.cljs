(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [dommy.macros :refer [sel1 deftemplate]])
  (:require [clojure.string :as string :refer [split blank?]]
            [cljs.core.async :as async :refer [chan <! close!]]
            [goog.net.XhrIo :as xhr]
            [dommy.core :as dommy]
            [dommy.template :as template]
            [markdown.core :as md]
            [cemerick.url :refer (url-encode query->map)]))


(def blog-config-url "/blog-config.yaml")
(def blog-content-url "/blog.md")


(defn log [s] (.log js/console (str s)))


(defn find-first [pred coll]
  "Find first matching element in coll"
  (first (filter pred coll)))


(defn strip-pairs [s c]
  "Strip pairs of char c from string s if the string starts ands ends in c.
   Otherwise returns s."
  (if (and (= (first s) c)
           (= (last s) c))
    (subs s 1 (dec (count s)))
    s))


(defn strip-last [s c]
  "Strip last instance of char c from s, if it exists.  Otherwise returns s."
  (if (= (last s) c)
    (subs s 0 (dec (count s)))
    s))


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
  "Transform string of yaml-style metadata into a map of keyword to value.
   Also do some additional processing on the metadata."
  (let [string-kvps (->> metadata
                         (string/split-lines)
                         (map #(string/split % #":" 2)))
        metadata (into {}
                       (for [[k v] string-kvps :when (not (blank? v))]
                         [(keyword k)
                          (-> v (string/trim) (strip-pairs \"))]))]
    (assoc metadata :title-id (url-encode (:title metadata)))))


; Template for a post title.  Returns Dom Object representing a
; single post title.
(deftemplate post-title-template [post]
  (let [title (get-in post [:metadata :title])
        safe-title (url-encode title)]
    [:h3 [:a {:href (str "?post=" safe-title)} title]]))


; Template for a post.  Returns Dom Object representing a single post.
(deftemplate post-template [post]
  (let [title (get-in post [:metadata :title])
        content (post :content)
        md-post (-> content (md/mdToHtml) (template/html->nodes))]
    [:article
     [:header [:h3 title]]
     [:p md-post]]))


(defn append-post! [post]
  "Insert a post into the page"
  (dommy/append! (sel1 :#blogapp)
                 (post-template post)))


(defn append-post-title! [post]
  "Insert a post title into the page"
  (dommy/append! (sel1 :#blogapp)
                 (post-title-template post)))


(deftemplate page-title [metadata]
  [:h1 [:a {:href "/" :class "disguised"} (:title metadata)]])

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


(defn show-post [title-id]
  "Show a single post"
  (go (let [config (<! (fetch-blog-config))]
        (apply-title! config)))
  (go (let [posts (<! (fetch-blog-contents))
            requested-post (find-first #(= (get-in % [:metadata :title]) title-id)
                                       posts)]
        (append-post! requested-post))))


; Dispatch based on url parameters
(def url (.-URL js/document))
(def url-params (second (split-url url)))

(cond
 (contains? url-params :post) (show-post (strip-last (:post url-params) \/))
 :else (show-front-page))
