(ns arrival.connect-db
  (:require [datomic.client.api :as d]
            [arrival.create-db :refer [create-db]]))


(defn connect-db
  "This function returns the connection to the db"
  []

  (let
   [client (d/client {:server-type :dev-local :system "dev"})
    d-list (d/list-databases client {})

    ;; If you want to delete the database (to clear all records), uncomment the line below.
    ;; _ (d/delete-database client {:db-name "arrival-nslobodchuk"})

  ; Check if the database exists
    nslobodchuk-exists? (some #(= "arrival-nslobodchuk" %) d-list)]

  ; If the database doesn't exist, create it
    (when (not nslobodchuk-exists?)
      (create-db client))

    ; return the connection to the db
    (d/connect client {:db-name "arrival-nslobodchuk"})))







