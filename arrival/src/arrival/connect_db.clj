(ns arrival.connect-db
  (:require [datomic.client.api :as d]
            [arrival.create-db :refer [create-db]]))


(def client (d/client {:server-type :dev-local
                       :system "dev"}))

;; If you want to delete the database (to clear all records), uncomment the line below.
;; (d/delete-database client {:db-name "arrival-nslobodchuk"})


(def d-list (d/list-databases client {}))

; Check if the database exists
(def nslobodchuk-exists? (some #(= "arrival-nslobodchuk" %) d-list))

; If the database doesn't exist, create it
(when (not nslobodchuk-exists?)
  (create-db client))

; Connect to the database. 'Conn' is referred to in other files.
(def conn (d/connect client {:db-name "arrival-nslobodchuk"}))





