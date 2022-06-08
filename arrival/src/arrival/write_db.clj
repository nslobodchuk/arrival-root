(ns arrival.write-db
    (:require [datomic.client.api :as d]
              [arrival.connect-db :refer [conn]]))


(defn write-db 
   "This is a utility function to write to the database"
  
  [data]
    (d/transact conn {:tx-data data})
  )
