(ns advent-of-code.day4.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day2.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day4/input.txt"))


(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 2 (solve1 ["7 6 4 2 1"
                      "1 2 7 8 9"
                      "9 7 6 2 1"
                      "1 3 2 4 5"
                      "8 6 4 4 1"
                      "1 3 6 7 9"]))))

  (testing "Solution Tests"
    (is (= 686 (solve1 input))))
  )

(deftest unit-tests
  (testing "unit")
  )

(deftest all-tests-part2
  (testing "Acceptance Test"
    (is (= 4 (solve2 ["7 6 4 2 1"
                      "1 2 7 8 9"
                      "9 7 6 2 1"
                      "1 3 2 4 5"
                      "8 6 4 4 1"
                      "1 3 6 7 9"]))))
  (testing "Solution Tests"
    (is (= 717 (solve2 input))))
  )
