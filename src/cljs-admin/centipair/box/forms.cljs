(ns centipair.box.forms
  (:require [centipair.core.components.input :as input]
            [reagent.core :as reagent :refer [atom]]))


(defn box-name-validator [] {:valid false :message "This field is wrong"})

(def box-name (atom {:id "box-name" :type "email" :label "Box Name" :validator box-name-validator} ))
(def box-description (atom {:id "box-description" :type "text" :label "Box Description" }))

(defn save-box []
  (.log js/console (clj->js @box-name))
  (.log js/console (clj->js @box-description)))

(def save-box-button (atom {:label "Save" :on-click save-box}))


(defn create-box-form []
  (input/form-aligned "Create box" [box-name box-description] save-box-button))


(defn render-box-form []
  (reagent/render
   [create-box-form] 
   (. js/document (getElementById "content"))))
