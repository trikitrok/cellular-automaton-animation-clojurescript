(ns cellular-animation.db
  (:require
    [cellular-animation.rules :as rules]))

(def default-db
  {:automaton-states [[1]]
   :rule rules/rule-90
   :evolving false})
