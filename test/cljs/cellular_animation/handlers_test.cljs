(ns cellular-animation.handlers-test
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [cellular-animation.handlers :as handlers]
    [cellular-animation.rules :as rules]))

(defn- make-spy [args]
  (fn [& parameters] (swap! args conj parameters)))

(deftest evolve-handler-test
  (testing "when the automaton is evolving it calls itself after changing its state"
    (let [initial-states [[1]]
          expected-states [[0 1 0] [1 0 1]]
          db {:automaton-states initial-states :rule rules/rule-90 :evolving true}
          args (atom [])
          dispatch-later-fn (make-spy args)
          evolve-handler (partial handlers/evolve-handler dispatch-later-fn)
          resulting-db (evolve-handler db :not-used-event)]
      (is (= resulting-db (merge db {:automaton-states expected-states})))
      (let [first-call-args (nth @args 0)]
        (is (= (count first-call-args) 2))
        (is (= (first first-call-args) :evolve))
        (is (= (second first-call-args) 100))))))

(testing "when the automaton is not evolving it does not change its state"
  (let [db :some-db
        args (atom [])
        dispatch-later-fn (make-spy args)
        evolve-handler (partial handlers/evolve-handler dispatch-later-fn)
        resulting-db (evolve-handler db :not-used-event)]
    (is (= resulting-db db))
    (is (zero? (count @args)))))

(deftest start-stop-evolution-test
  (testing "when the automaton is evolving it stops the evolution"
    (let [db {:evolving true}
          args (atom [])
          dispatch (make-spy args)
          start-stop-evolution (partial handlers/start-stop-evolution dispatch)
          resulting-db (start-stop-evolution db :not-used-event)]
      (is (= resulting-db (assoc db :evolving false)))
      (let [first-call-args (nth @args 0)]
        (is (= (count first-call-args) 1))
        (is (= (first first-call-args) :evolve)))))

  (testing "when the automaton is not evolving it starts the evolution"
    (let [db {:evolving false}
          args (atom [])
          dispatch (make-spy args)
          start-stop-evolution (partial handlers/start-stop-evolution dispatch)
          resulting-db (start-stop-evolution db :not-used-event)]
      (is (= resulting-db (assoc db :evolving true)))
      (let [first-call-args (nth @args 0)]
        (is (= (count first-call-args) 1))
        (is (= (first first-call-args) :evolve))))))
