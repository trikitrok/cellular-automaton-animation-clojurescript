(ns cellular-animation.handlers
  (:require
    [cellular-animation.evolution :as evolution]))

(defn evolve [dispatch-later-fn db _]
  (if (:evolving db)
    (let [db (update db :automaton-states
                     (partial evolution/evolve (:rule db)))]
      (dispatch-later-fn :evolve 100)
      db)
    db))

(defn start-stop-evolution [dispatch-fn db _]
  (let [db (update db :evolving not)]
    (dispatch-fn :evolve)
    db))
