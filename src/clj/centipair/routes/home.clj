(ns centipair.routes.home
  (:require [compojure.core :refer :all]
            [centipair.core.layout :as layout]
            [centipair.util :as util]))

(defn home-page []
  (layout/render
    "news/dev.html" ))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))