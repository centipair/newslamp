(ns centipair.core.admin.menu
  (:require [reagent.core :as reagent :refer [atom]]
            [secretary.core :as secretary :include-macros true :refer [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))


(def admin-menu (atom [{:label "Dashboard" :url "/dashboard" :id "dashboard" :active true}
                       {:label "Settings" :url "/settings" :id "settings" :active false}]))


(defn menu-component [com-data]
  [:ul
   (map 
    (fn [each] 
      [:li  {:key (:id each)} 
       [:a {:href (str "#" (:url each)) :key (:id each)} (:label each)]]) 
    @com-data)])


(defn admin-menu-component []
  (menu-component admin-menu))


(defn render-admin-menu []
  (reagent/render-component [admin-menu-component]
                            (. js/document (getElementById "admin-menu"))))


(defroute dashboard "/dashboard" []
  (js/console.log "Dashboard"))


(defroute dashboard "/settings" []
  (js/console.log "Settings"))


(defn ^:export init-admin []
  (do 
    (render-admin-menu)))


(let [h (History.)]
  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h (.setEnabled true)))
