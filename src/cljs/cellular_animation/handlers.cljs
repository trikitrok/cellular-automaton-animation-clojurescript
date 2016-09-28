(ns cellular-animation.handlers
  (:require
    [cellular-animation.evolution :as evolution]))

(defn evolve-handler [{:keys [db]} _]
  {:db (update db :automaton-states (partial evolution/evolve (:rule db)))
   :dispatch-later [{:ms 100 :dispatch [:evolve-handler]}]})
