(ns advent-of-code.day7.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day7.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day7/input.txt"))
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
    (is (= 3598800864292 (solve1 input)))
    )
  (testing "part 2"
    (is (= 11387 (solve2 acceptance-input)))
    (is (= 340362529351427 (solve2 input)))
    )
  )

(defn all-results-with-sum-and-product [input]
  (set (all-results-with [+ *] input))
  )



(deftest unit-testing
  (testing "combine inputs"
    (is (= '(1) (all-results-with [+] '(1))) "only addition and only one input delivers the one input")
    (is (= '(1 1) (all-results-with [+ *] '(1))) "only one input delivers the one input twice")
    (is (= '(2 1) (all-results-with [+ *] '(1 1))) "two inputs delivers the sum and the product of the input")
    (is (= #{1 2 3} (all-results-with-sum-and-product '(1 1 1))) "three inputs deliver the combinations of sums and products")
    (is (= #{10 24 9 20 13 36} (all-results-with-sum-and-product '(1 2 3 4))))
    )
  (testing "split input line"
    (is (= [1 '(1 1)] (split-input-line "1: 1 1")))
    (is (= [292 '(11 6 16 20)] (split-input-line "292: 11 6 16 20 ")))
    )
  (testing "valid combination"
    (is (= true (valid? [+ *] [292 '(11 6 16 20)])))
    (testing "|| operator"
      (is (= 1 (|| 1))) "| applied to a single argument returns the argument")
    (is (= 11 (|| 1 1))) "| applied to two arguments returns them joined")
  )



