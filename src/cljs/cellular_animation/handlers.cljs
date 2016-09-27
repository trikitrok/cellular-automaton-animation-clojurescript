(ns cellular-animation.handlers
    (:require [re-frame.core :as re-frame]
              [cellular-animation.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
