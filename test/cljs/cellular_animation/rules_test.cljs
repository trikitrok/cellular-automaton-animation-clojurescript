(ns cellular-animation.rules-test
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [cellular-animation.rules :as rules]))

(deftest rules-tests

  (testing "rule 90"
    ;+-----------------------------------------------------------------+
    ;| Neighborhood    | 111 | 110 | 101 | 100 | 011 | 010 | 001 | 000 |
    ;+-----------------------------------------------------------------+
    ;| New Center Cell |  0  |  1  |  0  |  1  |  1  |  0  |  1  |  0  |
    ;+-----------------------------------------------------------------+
    (is (= (rules/rule-90 [1 1 1]) 0))
    (is (= (rules/rule-90 [1 1 0]) 1))
    (is (= (rules/rule-90 [1 0 1]) 0))
    (is (= (rules/rule-90 [1 0 0]) 1))
    (is (= (rules/rule-90 [0 1 1]) 1))
    (is (= (rules/rule-90 [0 1 0]) 0))
    (is (= (rules/rule-90 [0 0 1]) 1))
    (is (= (rules/rule-90 [0 0 0]) 0)))

  (testing "rule 30"
    ;+-----------------------------------------------------------------+
    ;| Neighborhood    | 111 | 110 | 101 | 100 | 011 | 010 | 001 | 000 |
    ;+-----------------------------------------------------------------+
    ;| New Center Cell |  0  |  0  |  0  |  1  |  1  |  1  |  1  |  0  |
    ;+-----------------------------------------------------------------+
    (is (= (rules/rule-30 [1 1 1]) 0))
    (is (= (rules/rule-30 [1 1 0]) 0))
    (is (= (rules/rule-30 [1 0 1]) 0))
    (is (= (rules/rule-30 [1 0 0]) 1))
    (is (= (rules/rule-30 [0 1 1]) 1))
    (is (= (rules/rule-30 [0 1 0]) 1))
    (is (= (rules/rule-30 [0 0 1]) 1))
    (is (= (rules/rule-30 [0 0 0]) 0))))