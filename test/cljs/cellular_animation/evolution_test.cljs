(ns cellular-animation.evolution-test
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [cellular-animation.evolution :as evolution]
    [cellular-animation.rules :as rules]))

(deftest cellular-automaton-evolution
  (testing "evolution from an initial state"
    (is (= (evolution/evolve rules/rule-90 [[1]])
           [[0 1 0]
            [1 0 1]]))

    (is (= (evolution/evolve rules/rule-90 [[0 1 0] [1 0 1]])
           [[0 0 1 0 0]
            [0 1 0 1 0]
            [1 0 0 0 1]]))))
