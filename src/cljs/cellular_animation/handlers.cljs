(ns cellular-animation.handlers
  (:require
    [cellular-animation.evolution :as evolution]))

(defn evolve [{:keys [db]} _]
  (if (:evolving db)
    {:db (update db :automaton-states (partial evolution/evolve (:rule db)))
     :dispatch-later [{:ms 100 :dispatch [:evolve]}]}
    {:db db}))

(defn start-stop-evolution [{:keys [db]} _]
  {:db (update db :evolving not)
   :dispatch [:evolve]})
