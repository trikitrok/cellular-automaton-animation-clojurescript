(ns cellular-animation.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [cellular-animation.core-test]))

(doo-tests 'cellular-animation.core-test)
