(ns advent-of-code.day7.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day7.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/template/input.txt"))
(def acceptance-input ["190: 10 19"
                       "3267: 81 40 27"
                       "83: 17 5"
                       "156: 15 6"
                       "7290: 6 8 6 15"
                       "161011: 16 10 13"
                       "192: 17 8 14"
                       "21037: 9 7 18 13"
                       "292: 11 6 16 20 "])

(deftest acceptance-testing
  (testing "part 1"
    (is (= 3749 (solve1 acceptance-input)))
    (is (= nil (solve1 input)))
    )
  (testing "part 2"
    (is (= nil (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )

(deftest unit-testing
  (testing "combine inputs"
    (is (= '(1) (all-results-with [+] '(1))))
    )
  )


