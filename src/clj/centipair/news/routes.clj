(ns centipair.news.routes
  (:require [compojure.core :refer :all]
            [centipair.core.layout :as layout]
            [centipair.util :as util]
            [environ.core :refer [env]]))

(defn home-page []
  (layout/render
    "news/dev.html" ))

(defn about-page []
  (layout/render "about.html"))

(defroutes news-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))
