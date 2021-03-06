(ns cellular-animation.views
  (:require
    [re-frame.core :as re-frame]))

(def ^:private cell-representations
  {0 "\u00A0" 1 "*"})

(defn- render-state [state-index cell-index cell-state]
  (with-meta
    [:span.cell (cell-representations cell-state)]
    {:key (str "cell-state-" state-index "-cell-" cell-index)}))

(defn- render-states [state-index state]
  (with-meta
    [:div
     (map-indexed (partial render-state state-index) state)]
    {:key (str "state-" state-index)}))

(defn- automaton-component []
  (let [automaton-states (re-frame/subscribe [:automaton-states])]
    (fn []
      [:div
       {:on-click #(re-frame/dispatch [:evolution-started-or-stopped])}
       (map-indexed render-states @automaton-states)])))

(defn main-panel []
  [automaton-component])
