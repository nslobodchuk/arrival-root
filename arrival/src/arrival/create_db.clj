(ns arrival.create-db
  (:require [datomic.client.api :as d]))

(def arrival-schema [{:db/ident :arrival-nslobodchuk/topic
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "Ticket topic"}

                     {:db/ident :arrival-nslobodchuk/details
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "Ticket details"}

                     {:db/ident :arrival-nslobodchuk/client
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "Client name"}

                     {:db/ident :arrival-nslobodchuk/contractor
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "Contractor name"}

                     {:db/ident :arrival-nslobodchuk/due-date
                      :db/valueType :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc "Due date unix timestamp"}])


(def sample-tickets [{:arrival-nslobodchuk/topic "Title 1"
                      :arrival-nslobodchuk/details "Do some work"
                      :arrival-nslobodchuk/client "UPS"
                      :arrival-nslobodchuk/contractor "Siemens"
                      :arrival-nslobodchuk/due-date 1685954507},
                     {:arrival-nslobodchuk/topic "Title 2"
                      :arrival-nslobodchuk/details "Do more work"
                      :arrival-nslobodchuk/client "Amazon"
                      :arrival-nslobodchuk/contractor "Airbus"
                      :arrival-nslobodchuk/due-date 1678005707}])


(defn create-db 
  "This is a utility function to create the database if it doesn't exist."
  
  [client]
  (d/create-database client {:db-name "arrival-nslobodchuk"})
  (let [conn (d/connect client {:db-name "arrival-nslobodchuk"})]
    (d/transact conn {:tx-data arrival-schema})
    (d/transact conn {:tx-data sample-tickets})
    )
)












