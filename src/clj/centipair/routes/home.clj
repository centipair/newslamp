(ns centipair.routes.home
  (:require [compojure.core :refer :all]
            [centipair.core.layout :as layout]
            [centipair.util :as util]))

(defn home-page []
  (layout/render
    "index.html"))

(defn about-page []
  (layout/render "about.html"))

(defn faq-page []
  (layout/render "faq.html"))


(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/faq" [] (faq-page)))
