(ns arrival.process-submit
    (:require [clojure.data.json :as json]
              [arrival.write-db :refer [write-db]]
              [clojure.core.async :as a
               :refer [<! >!! go chan  thread]]))


(defn process-submit 
  "This is a handler function for a POST HTTP request to '/testapp/api/submit'. 
   The POST HTTP request needs to contain the stringified JSON of a new Ticket.
   The ticket is stored into the Datomic database."
  
  [request respond raise]

  (go
    (let [body-parsed (json/read-str (slurp (:body request)))
          sample-tickets [{:arrival-nslobodchuk/topic (body-parsed "topic")
                           :arrival-nslobodchuk/details (body-parsed "details")
                           :arrival-nslobodchuk/client (body-parsed "client")
                           :arrival-nslobodchuk/contractor (body-parsed "contractor")
                           :arrival-nslobodchuk/due-date (body-parsed "date")}]
          c (chan)]
      (thread
        (write-db sample-tickets)
        (>!! c "Success"))

      (<! c)
      (respond (json/write-str sample-tickets)))
    )
  
  )