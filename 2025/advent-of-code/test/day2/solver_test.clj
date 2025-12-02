(ns day2.solver-test
  (:require [clojure.test :refer :all]
            [day2.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day2/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day2/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 1227775554 (solve1 acceptance-input)))
    ;(is (= nil (solve1 input)))
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


