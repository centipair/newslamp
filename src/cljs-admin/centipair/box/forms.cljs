(ns centipair.box.forms
  (:require [centipair.core.components.input :as input]
            [reagent.core :as reagent :refer [atom]]))



(def create-box-inputs (atom [{:id "box-name" :type "text" :label "Box Name"}
                              {:id "box-description" :type "text" :label "Box Description"}]))

(defn create-box-form []
  (input/form-aligned "Create box" create-box-inputs))


(defn render-box-form []
  (reagent/render-component 
   [create-box-form] 
   (. js/document (getElementById "content"))))
