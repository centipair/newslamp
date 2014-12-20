(ns centipair.core.admin.routes
  (:require [compojure.core :refer :all]
            [centipair.core.layout :as layout]
            [centipair.util :as util]
            [environ.core :refer [env]]))

(defn admin-dashboard []
  (layout/render
    "admin/dashboard.html" ))

(defn admin-home []
  (layout/render
    "admin/admin-bootstrap.html" ))


(defn admin-settings []
  (layout/render "admin/settings.html"))

(defroutes admin-routes
  (GET "/admin" [] (admin-home))
  (GET "/admin/dashboard" [] (admin-dashboard))
  (GET "/admin/settings" [] (admin-settings)))

