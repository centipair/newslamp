(ns centipair.core.ui
  (:require
   [reagent.core :as reagent :refer [atom]]))


(defn render [elements root-id]
  (reagent/render
   [elements] 
   (. js/document (getElementById root-id))))
