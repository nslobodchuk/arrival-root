(ns arrival.read-db 
  (:require [datomic.client.api :as d]
            [arrival.connect-db :refer [conn]]
            [clojure.data.json :as json]
            [clojure.core.async :as a
             :refer [<! >!! go chan thread]]))

; Define the query
(def all-tickets '[:find ?e ?topic ?details ?client ?contractor ?due-date
                   :where
                   [?e :arrival-nslobodchuk/topic ?topic]
                   [?e :arrival-nslobodchuk/details ?details]
                   [?e :arrival-nslobodchuk/client ?client]
                   [?e :arrival-nslobodchuk/contractor ?contractor]
                   [?e :arrival-nslobodchuk/due-date ?due-date]])

(defn read-db 
  "This is a handler function for a GET request to '/testapp/api/tickets-list'.
   The function makes a query to the Datomic database and responds with the stringified JSON data."
  
  [request respond raise]

  (go
    (let [db (d/db conn)
          c (chan)]

      (thread
        ; Return to the channel the result of the query
        (>!! c (d/q all-tickets db)))

      (respond (json/write-str (<! c)))))
)

