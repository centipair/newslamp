(ns centipair.core.admin.menu
  (:require [reagent.core :as reagent :refer [atom]]
            [centipair.box.forms :as box-form]
            [secretary.core :as secretary :include-macros true :refer [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))


(def admin-menu (atom [{:label "Dashboard" :url "/dashboard" :id "dashboard" :active true}
                       {:label "Settings" :url "/settings" :id "settings" :active false}
                       {:label "Box" :url "/box" :id "box" :active false}
                       ]))


(defn title-component [id]
  (reagent/render-component
   [:h3 (:label (first (filter (fn [each] (= (:id each) id)) @admin-menu)))]
   (. js/document (getElementById "admin-title"))))


(defn deactivate [id item]
  (if (= id (:id item))
    (assoc item :active true)
    (assoc item :active false)))


(defn activate-side-menu-item [id]
  (title-component id)
  (reset! admin-menu (into [] (map (partial deactivate id) @admin-menu))))


(defn menu-component [com-data]
  [:ul
   (map 
    (fn [each]
      [:li  {:key (:id each) :class (if (:active each) "menu-item-divided pure-menu-selected" "")} 
       [:a {:href (str "#" (:url each)) :key (:id each)} (:label each)]]) 
    @com-data)])


(defn admin-menu-component []
  (menu-component admin-menu))


(defn render-admin-menu []
  (reagent/render-component [admin-menu-component]
                            (. js/document (getElementById "admin-menu"))))


(defroute dashboard "/dashboard" []
  (activate-side-menu-item "dashboard")
  (js/console.log "Dashboard"))


(defroute settings "/settings" []
  (activate-side-menu-item "settings")
  (js/console.log "Settings"))


(defroute box "/box" []
  (activate-side-menu-item "box")
  (box-form/render-box-form))


(let [h (History.)]
  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h (.setEnabled true)))
