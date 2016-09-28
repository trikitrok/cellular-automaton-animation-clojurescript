(ns cellular-animation.evolve-handler-test
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [cellular-animation.handlers :as handlers]
    [cellular-animation.rules :as rules]))

(deftest evolve-handler-test
  (testing "it calls itself after evolving the automaton states"
    (let [initial-states [[1]]
          expected-states [[0 1 0] [1 0 1]]
          db {:automaton-states initial-states :rule rules/rule-90}]
      (is (= (handlers/evolve-handler {:db db} [])
             {:db (assoc db :automaton-states expected-states)
              :dispatch-later [{:ms 100 :dispatch [:evolve-handler]}]})))))