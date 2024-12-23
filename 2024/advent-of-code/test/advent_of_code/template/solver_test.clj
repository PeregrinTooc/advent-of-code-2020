(ns advent-of-code.template.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.template.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/template/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/advent_of_code/template/acceptance-input.txt"))

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


