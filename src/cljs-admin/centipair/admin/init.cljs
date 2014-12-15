(ns centipair.admin.init
  (:require [centipair.admin.menu :as admin-menu]
            [centipair.core.components.notifier :as notifier]))


(defn ^:export init-admin []
  (do 
    (admin-menu/render-admin-menu)
    (notifier/render-notifier-component)))
