(ns cellular-animation.views
  (:require
    [re-frame.core :as re-frame]))

(def ^:private cell-representations
  {0 "_" 1 "x"})

(defn- render-state [state-index cell-index cell-state]
  (with-meta
    [:span (cell-representations cell-state)]
    {:key (str "cell-state-" state-index "-cell-" cell-index)}))

(defn- states-component [state-index state]
  (with-meta
    [:div
     (map-indexed (partial render-state state-index) state)]
    {:key (str "state-" state-index)}))

(defn- automaton-component []
  (let [automaton-states (re-frame/subscribe [:automaton-states])]
    (fn []
      [:div (map-indexed states-component @automaton-states)])))

(defn main-panel []
  [automaton-component])