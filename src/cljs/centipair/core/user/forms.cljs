(ns centipair.core.user.forms
  (:require [centipair.core.components.input :as input]
            [centipair.core.utilities.validators :as v]
            [centipair.core.ui :as ui]
            [centipair.core.utilities.ajax :as ajax]
            [reagent.core :as reagent :refer [atom]]))



(def registration-form-state (atom {:title "Sign Up" :action "/register-submit" :id "registration-form"}))
(def email (atom {:id "email" :type "email" :label "Email" :validator v/email-required} ))
(def username (atom {:id "username" :type "text" :label "Email" :validator v/required} ))
(def password (atom {:id "password" :type "password" :label "Password" :validator v/required}))
(def accept-box-terms (atom {:id "box-terms" :type "checkbox" :label "I've read the terms and conditions" :validator v/agree-terms}))

(defn password-required-match [field]
  (if (v/has-value? (:value field))
    (if (= (:value field) (:value @password))
      (v/validation-success)
      (v/validation-error "Passwords does not match"))
    (v/validation-error v/required-field-error)))

(def confirm-password (atom {:id "confirm-password" :type "password" :label "Confirm Password" :validator password-required-match}))

(defn register-submit []
  (ajax/form-post
   registration-form-state
   "/register-submit"
   [email password confirm-password]
   (fn [response] (.log js.console "yay!!!"))
   ))

(def register-submit-button (atom {:label "Submit" :on-click register-submit}))

(defn registration-form []
  (input/form-aligned  
   registration-form-state
   ;;[email password confirm-password accept-box-terms]
   [email password confirm-password accept-box-terms]
   register-submit-button))


(defn render-register-form []
  (ui/render registration-form "register-form"))


(def login-form-state (atom {:title "Login" :action "/login-submit" :id "login-form"}))


(defn login-submit []
  (ajax/form-post
   login-form-state
   "/login-submit"
   [username password]
   (fn [response] (.log js.console "login yay!!!"))
   ))

(def login-submit-button (atom {:label "Submit" :on-click login-submit}))

(defn login-form []
  (input/form-aligned login-form-state [username password] login-submit-button))

(defn render-login-form []
  (ui/render login-form "login-form"))
