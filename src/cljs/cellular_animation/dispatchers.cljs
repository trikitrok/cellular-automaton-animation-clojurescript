(ns cellular-animation.dispatchers
  (:require
    [re-frame.core :as re-frame]))

(defn dispatch [event]
  (re-frame/dispatch [event]))

(defn dispatch-later [event ms]
  (js/setTimeout
    (fn [] (dispatch event))
    ms))
