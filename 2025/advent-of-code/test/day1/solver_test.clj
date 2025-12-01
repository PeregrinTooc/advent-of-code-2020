(ns day1.solver-test
  (:require [clojure.test :refer :all]
            [day1.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day1/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day1/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 3 (solve1 acceptance-input)))
    ;(is (= nil (solve1 input)))
    )
  (testing "part 2"
    (is (= nil (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= 0  (mod (+ 52 48) 100 )))
    )
  )


