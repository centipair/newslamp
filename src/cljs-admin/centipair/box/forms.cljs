(ns centipair.box.forms
  (:require [centipair.core.components.input :as input]
            [centipair.core.utilities.validators :as v]
            [centipair.core.ui :as ui]
            [reagent.core :as reagent :refer [atom]]))


(def box-name (atom {:id "box-name" :type "text" :label "Box Name" :validator v/required} ))
(def box-description (atom {:id "box-description" :type "text" :label "Box Description" :validator v/required}))

(def box-form (atom {:title "Create Box"}))

(defn save-box []
  (.log js/console (clj->js @box-name))
  (.log js/console (clj->js @box-description)))

(def save-box-button (atom {:label "Save" :on-click save-box}))


(defn create-box-form []
  (input/form-aligned box-form [box-name box-description] save-box-button))


(defn render-box-form []
  (ui/render create-box-form "content"))
