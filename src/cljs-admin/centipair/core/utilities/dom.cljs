(ns centipair.core.utilities.dom
  (:require [goog.dom :as gdom]))

(defn get-value
  [id]
  (.-value ( gdom/getElement id)))
