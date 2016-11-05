(ns cellular-animation.handlers
  (:require
    [cellular-animation.evolution :as evolution]
    [re-frame.core :as re-frame]))

(defn evolve-handler [db _]
  (if (:evolving db)
    (let [db (update db :automaton-states (partial evolution/evolve (:rule db)))]
      (js/setTimeout (fn [] (re-frame/dispatch [:evolve])) 100)
      db)
    db))

(defn start-stop-evolution [db _]
  (let [db (update db :evolving not)]
    (re-frame/dispatch [:evolve])
    db))
