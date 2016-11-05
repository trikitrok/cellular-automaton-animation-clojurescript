(ns cellular-animation.register-handlers
  (:require
    [re-frame.core :as re-frame]
    [cellular-animation.db :as db]
    [cellular-animation.handlers :as handlers]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  :evolution-started-or-stopped
  handlers/start-stop-evolution)

(re-frame/reg-event-db
  :evolve
  handlers/evolve-handler)
