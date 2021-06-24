(ns app.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]))

(def color
  (r/atom ["red" "gray" "gray" 
           "gray" "yellow" "gray"
           "gray" "gray" "green" 
           "gray" "yellow" "gray"
           ]))


(defn timer-component []
  (fn []
    (js/setTimeout #(swap! color (fn []
                                   (drop 3 (concat @color (take 3 @color))))) 1000)
    [:div
     [:div
      [:span.dot {:style {:background (first @color)}}]]
     [:div
      [:span.dot {:style {:background (second @color)}}]]
     [:div
      [:span.dot {:style {:background (nth @color 2)}}]]
     [:div]]))


(defn app-container []
  [:<>
   [timer-component]
   ]
  )

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [app-container]
               (.getElementById js/document "app")))


(defn main []
  (js/console.log "MAIN FN Reloaded")
  (render))