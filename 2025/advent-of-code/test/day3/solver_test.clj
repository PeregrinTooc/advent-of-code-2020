(ns day3.solver-test
  (:require [clojure.test :refer :all]
            [day3.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day3/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day3/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 357 (solve1 acceptance-input)))
    ;(is (= nil (solve1 input)))
    )
  (testing "part 2"
    (is (= nil (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= 98 (determine-highest-joltage "987")))
    ;(is (= [98] (determine-highest-joltage "978")))
    )
  )


