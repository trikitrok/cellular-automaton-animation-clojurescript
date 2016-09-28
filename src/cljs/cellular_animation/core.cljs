(ns cellular-animation.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [devtools.core :as devtools]
    [cellular-animation.register-handlers]
    [cellular-animation.subs]
    [cellular-animation.views :as views]
    [cellular-animation.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
