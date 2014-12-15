(ns centipair.core.user.forms
  (:require [centipair.core.components.input :as input]
            [centipair.core.utilities.validators :as v]
            [centipair.core.ui :as ui]
            [reagent.core :as reagent :refer [atom]]))


(def email (atom {:id "email" :type "email" :label "Email" :validator v/required} ))
(def password (atom {:id "password" :type "password" :label "Password" :validator v/required}))

(defn register-submit []
  (.log js/console (clj->js @email))
  (.log js/console (clj->js @password)))

(def register-submit-button (atom {:label "Submit" :on-click register-submit}))


(defn registration-form []
  (input/form-aligned "Sign Up" [email password] register-submit-button))


(defn render-register-form []
  (ui/render registration-form "register-form"))
