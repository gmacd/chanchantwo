(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [goog.events :as events]
            [goog.net.XhrIo :as xhr]
            [cljs.core.async :as async :refer [put! chan <! close!]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import [goog.net Jsonp]
           [goog Uri]))

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

(defn widget [contents data]
  (om/component
    (dom/div nil contents)))

(go
  (let [blog (<! (GET "http://localhost:8000/blog.md"))]
    (om/root {} (partial widget blog) (elem "blog-contents"))))
