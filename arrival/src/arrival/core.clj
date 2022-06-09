; The app is served at http://localhost:8080/testapp
; Need to have Datomic dev-local installed
; The files in the ../../resouces/build are generated in a separate Node.js project.

(ns arrival.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [resource-response]]
            [compojure.core :refer [defroutes GET POST]]
            [arrival.read-db :refer [read-db]]
            [arrival.process-submit :refer [process-submit]]
            [arrival.connect-db :refer [connect-db]]
            [arrival.app-state :refer [app-state]]))

(defroutes approutes

  (POST "/testapp/api/submit" [] process-submit)

  (GET "/testapp/api/tickets-list" [] read-db)

  (GET "/testapp/*" []
    (resource-response "build/index.html"))
  (GET "/testapp" []
    (resource-response "build/index.html")))

(def app (wrap-resource approutes "build"))

(defn server []
  (run-jetty app {:join? false
                  :port 8080
                  :async? true
                  :async-timeout 30000}))


(defn -main [& args]

  (swap! app-state #(assoc % :db-conn (connect-db)))
  (server))