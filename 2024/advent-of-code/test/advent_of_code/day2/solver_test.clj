(ns advent-of-code.day2.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day2.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day2/input.txt"))


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
  (testing "safe"
    (is (safe? [7 6 4 2 1]))
    (is (not (safe? [1 2 7 8 9])))
    (is (not (safe? [9 7 6 2 1])))
    (is (not (safe? [1 3 2 4 5])))
    (is (not (safe? [8 6 4 4 1])))
    (is (safe? [1 3 6 7 9]))
    )
  (testing "updated-safe"
    (is (updated-safe? [7 6 4 2 1]))
    (is (not (updated-safe? [1 2 7 8 9])))
    (is (not (updated-safe? [9 7 6 2 1])))
    (is (updated-safe? [1 3 2 4 5]))
    (is (updated-safe? [8 6 4 4 1]))
    (is (updated-safe? [1 3 6 7 9]))
    ))

(deftest ^:skip all-tests-part2
  (testing "Acceptance Test"
    (is (= 4 (solve2 ["7 6 4 2 1"
                      "1 2 7 8 9"
                      "9 7 6 2 1"
                      "1 3 2 4 5"
                      "8 6 4 4 1"
                      "1 3 6 7 9"]))))
  (testing "Solution Tests"
    (is (= 22565391 (solve2 input))))
  )
