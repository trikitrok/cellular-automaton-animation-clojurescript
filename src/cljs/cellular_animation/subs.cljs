(ns cellular-animation.subs
  (:require-macros
    [reagent.ratom :refer [reaction]])
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :automaton-states
  (fn [db]
    (:automaton-states db)))
