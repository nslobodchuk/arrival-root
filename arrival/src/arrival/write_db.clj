(ns arrival.write-db
  (:require [datomic.client.api :as d]
            [arrival.app-state :refer [app-state]]))


(defn write-db
  "This is a utility function to write to the database"

  [data]
  (d/transact (@app-state :db-conn) {:tx-data data}))
