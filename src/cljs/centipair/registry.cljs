(ns centipair.registry
  (:require [centipair.core.user.forms :as user-forms]
            ))


(def function-registry {:render-register-form user-forms/render-register-form})



(defn ^:export load-function [name]
  (((keyword name) function-registry)))
