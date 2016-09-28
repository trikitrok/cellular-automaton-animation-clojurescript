(ns cellular-animation.db
  (:require
    [cellular-animation.rules :as rules]))

(def default-db
  {:automaton-states [[0 0 0 1 0 0 0]
                      [1 0 0 1 0 0 1]]
   :rule rules/rule-90})
