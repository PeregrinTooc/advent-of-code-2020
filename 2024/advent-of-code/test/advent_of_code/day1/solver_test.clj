(ns advent-of-code.day1.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day1.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day1/input.txt"))


(deftest all-tests-part1
  (testing "Acceptance Test"

    (is (= 11 (solve1 ["3   4"
                       "4   3"
                       "2   5"
                       "1   3"
                       "3   9"
                       "3   3"]))))
  (testing "Unit Tests"
    (is (= [1 1] (transform-to-lists "1 1")))
    (is (= [[1] [1]] (transform-to-lists "1 1" [] [])))
    (is (= [[1, 2] [2, 1]] (transform-to-lists "2 1" [1] [2])))
    (is (= 1 (solve1 ["3   4"])))
    (is (= 3 (solve1 ["3   4", "2 4"])))
    )
  (testing "Solution Tests"
    (is (= 3574690 (solve1 input))))
  )

(deftest all-tests-part2
  (testing "Accpetance Test"
    (is (= 31 (solve2 ["3   4"
                       "4   3"
                       "2   5"
                       "1   3"
                       "3   9"
                       "3   3"]))))
  (testing "Solution Tests"
    (is (= 22565391 (solve2 input))))
  )
