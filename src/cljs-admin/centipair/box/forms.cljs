(ns centipair.box.forms
  (:require [centipair.core.components.input :as input]
            [reagent.core :as reagent :refer [atom]]))




(def box-name (atom {:id "box-name" :type "text" :label "Box Name"}))
(def box-description (atom {:id "box-description" :type "text" :label "Box Description" }))

(defn create-box-form []
  (input/form-aligned "Create box" [box-name box-description]))


(defn render-box-form []
  (reagent/render
   [create-box-form] 
   (. js/document (getElementById "content"))))
