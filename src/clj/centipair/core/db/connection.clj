(ns centipair.core.db.connection
  (:require [clojurewerkz.cassaforte.client :as cc]
            [clojurewerkz.cassaforte.cql    :as cql]))



;; Will connect to localhost
(defn get-db-connection []
  (let [conn (cc/connect ["127.0.0.1"])]
    (cql/use-keyspace conn "core")
    conn))


(def conn (atom nil))


(defn dbcon []
  (if (nil? @conn) 
    (reset! conn (get-db-connection))
    @conn))
