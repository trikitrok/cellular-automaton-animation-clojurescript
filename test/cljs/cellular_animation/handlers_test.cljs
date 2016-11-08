(ns cellular-animation.handlers-test
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [cellular-animation.handlers :as handlers]
    [cellular-animation.rules :as rules]))

(deftest evolve-handler-test
  (testing "when the automaton is evolving it calls itself after changing its state"
    (let [initial-states [[1]]
          expected-states [[0 1 0] [1 0 1]]
          db {:automaton-states initial-states :rule rules/rule-90 :evolving true}]
      (is (= (handlers/evolve {:db db} [])
             {:db (merge db {:automaton-states expected-states})
              :dispatch-later [{:ms 100 :dispatch [:evolve]}]}))))

  (testing "when the automaton is not evolving it does not change its state"
    (let [db {:automaton-states :not-used-initial-states :rule :not-used-rule :evolving false}]
      (is (= (handlers/evolve {:db db} []) {:db db})))))

(deftest start-stop-evolution-test
  (testing "when the automaton is evolving it stops the evolution"
    (let [db {:evolving true}]
      (is (= (handlers/start-stop-evolution {:db db} [])
             {:db (assoc db :evolving false)
              :dispatch [:evolve]}))))

  (testing "when the automaton is not evolving it starts the evolution"
    (let [db {:evolving false}]
      (is (= (handlers/start-stop-evolution {:db db} [])
             {:db (assoc db :evolving true)
              :dispatch [:evolve]})))))
