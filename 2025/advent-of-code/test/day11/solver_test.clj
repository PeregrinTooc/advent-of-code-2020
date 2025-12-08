(ns day11.solver-test
  (:require [clojure.test :refer :all]
            [day11.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day11/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day11/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= nil (solve1 acceptance-input)))
    (is (= nil (solve1 input)))
    )
  (testing "part 2"
    (is (= nil (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= 0 (+ 0 0)))
    )
  )


