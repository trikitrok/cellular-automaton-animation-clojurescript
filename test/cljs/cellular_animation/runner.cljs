(ns cellular-animation.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [cellular-animation.rules-test]))

(doo-tests 'cellular-animation.rules-test)
