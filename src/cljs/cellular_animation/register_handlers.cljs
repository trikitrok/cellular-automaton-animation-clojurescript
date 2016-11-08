(ns cellular-animation.register-handlers
  (:require
    [re-frame.core :as re-frame]
    [cellular-animation.db :as db]
    [cellular-animation.handlers :as handlers]
    [cellular-animation.dispatchers :as dispatchers]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  :evolution-started-or-stopped
  (partial
    handlers/start-stop-evolution
    dispatchers/dispatch))

(re-frame/reg-event-db
  :evolve
  (partial
    handlers/evolve
    dispatchers/dispatch-later))
