(ns chanchantwo.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.events :as events]
            [cljs.core.async :refer [put! chan <!]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import [goog.net Jsonp]
           [goog Uri]))

(defn elem [id] (.getElementById js/document id))

(defn listen [el type]
  (let [out (chan)]
    (events/listen el type
      (fn [e] (put! out e)))
    out))

(defn jsonp [uri]
  (let [out (chan)
        req (Jsonp. (Uri. uri))]
    (.send req nil (fn [res] (put! out res)))
    out))

(def wiki-search-url
  "http://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=")

(defn query-url [q]
  (str wiki-search-url q))

(defn user-query []
  (.-value (elem "query")))

(defn render-query [results]
  (str
    "<ul>"
    (apply str
      (for [result results]
        (str "<li>" result "</li>")))
    "</ul>"))

(defn render-query-om [results]
  (om/component
    (dom/ul #js {:id "results"}
            (for [result results]
              (dom/li nil result)))))

(defn widget [data]
  (om/component
    (om/ul nil
           (om/li nil "abc")
           (om/li nil "def"))))
;    (dom/div nil "Hello world!")))


(defn widget2 [data]
  (om/component
    (dom/div nil "Hello world2!")))

(defn init []
  (let [clicks (listen (elem "search") "click")
        results-view (elem "results")]
    (go (while true
          (let [[_ results] (<! (jsonp (query-url (user-query))))]
	            (om/root {} (fn [] (render-query-om results)) results-view))))))
            ;(om/root {} widget results-view))))))
            ;(set! (.-innerHTML results-view) (render-query results)))))))

(init)


(om/root {} widget (elem "xxx"))
