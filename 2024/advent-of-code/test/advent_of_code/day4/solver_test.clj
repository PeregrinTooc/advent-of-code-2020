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
  (testing "count in strings"
    (is (= 0 (count-xmas-occurrences "")))
    (is (= 1 (count-xmas-occurrences "XMAS")))
    (is (= 0 (count-xmas-occurrences "X")))
    (is (= 1 (count-xmas-occurrences "SAMX")))
    )
  (testing "extract columns"
    (is (= [] (extract-columns [])))
    (is (= ["X"] (extract-columns ["X"])))
    (is (= ["X" "X"] (extract-columns ["XX"])))
    (is (= ["XX"] (extract-columns ["X" "X"])))
    (is (= ["AA" "BB"] (extract-columns ["AB" "AB"])))
    (is (= ["XXX"] (extract-columns ["X" "X" "X"])))
    )
  (testing "extract diagonals"
    (is (= [] (extract-diagonals [])))
    (is (= ["X"] (extract-diagonals ["X"])))
    (is (= (set ["B" "C" "AD"]) (set (extract-diagonals ["AB" "CD"]))))
    (is (= (set ["A" "D" "BC"]) (set (extract-diagonals (map #(apply str (reverse %)) ["AB" "CD"])))))
    (is (= (set ["C" "BF" "AEI" "DH" "G"])
           (set (extract-diagonals ["ABC"
                                    "DEF"
                                    "GHI"]))))
    )
  )

(deftest all-tests-part2
  )
