(ns centipair.core.auth.user.routes
  (:use centipair.core.utilities.appresponse
        centipair.core.auth.user.forms
        centipair.core.auth.user.models
        centipair.core.utilities.cryptography)
  (:require [compojure.core :refer :all]
            [centipair.layout :as layout]
            [centipair.util :as util]))


(defn register-page []
  (layout/render "core/user/register.html"))

(defn login-page []
  (layout/render "core/user/login.html"))


(defn register-submit [request]
  (send-response (user-registration-form (:params request))))


(defn activate [registration-key]
  (let [user-id (activate-account (str-uuid registration-key))]
  (if user-id
    (layout/render "core/user/login.html" {:title "Account activated" :message "Your account has been activated.Please Login"})
    (layout/render "core/user/account-activation.html" {:title "Account activation error" :message "Invalid activation code"}))))


(defroutes user-routes 
  (GET "/register" [] (register-page))
  (GET "/login" [] (login-page))
  (POST "/register-submit" request (register-submit request))
  (GET "/activate/:key" [key] (activate key)))
