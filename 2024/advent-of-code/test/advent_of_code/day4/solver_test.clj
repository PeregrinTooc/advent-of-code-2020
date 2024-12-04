(ns advent-of-code.day4.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day4.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day4/input.txt"))


(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 18 (solve1 ["MMMSXXMASM"
                       "MSAMXMSMSA"
                       "AMXSXMAAMM"
                       "MSAMASMSMX"
                       "XMASAMXAMM"
                       "XXAMMXXAMA"
                       "SMSMSASXSS"
                       "SAXAMASAAA"
                       "MAMMMXMMMM"
                       "MXMXAXMASX"]))))

  (testing "Solution Tests"
    (is (= 686 (solve1 input))))
  )

(deftest unit-tests
  (testing "unit")
  )

(deftest all-tests-part2
  )
