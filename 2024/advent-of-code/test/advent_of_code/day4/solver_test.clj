(ns advent-of-code.day4.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day4.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day4/input.txt"))
(def acceptance-input ["MMMSXXMASM"
                       "MSAMXMSMSA"
                       "AMXSXMAAMM"
                       "MSAMASMSMX"
                       "XMASAMXAMM"
                       "XXAMMXXAMA"
                       "SMSMSASXSS"
                       "SAXAMASAAA"
                       "MAMMMXMMMM"
                       "MXMXAXMASX"])

(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 18 (solve1 acceptance-input))))

  (testing "Solution Tests"
    (is (= 2496 (solve1 input))))
  )

(deftest unit-tests
  (testing "count in strings"
    (is (= 0 (count-xmas-occurrences "")))
    (is (= 1 (count-xmas-occurrences "XMAS")))
    (is (= 0 (count-xmas-occurrences "X")))
    (is (= 1 (count-xmas-occurrences "SAMX")))
    (is (= 2 (count-xmas-occurrences "XMASAMX")))
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
    (is (= (set ["MSXMAXSAMX" "MMASMASMS" "ASAMSAMA"
                 "MMAMMXM" "XXSAMX" "XMXMA" "SAMX" "SAM" "MX" "M"
                 "MASAMXXAM" "MMXSXASA" "SXMMAMS" "XMASMA"
                 "XSAMM" "MMMX" "ASM" "SA" "M"]) (set (extract-diagonals acceptance-input))))
    (is (= ["C" "G" "BF" "DH" "AEI"] (extract-diagonals ["ABC" "DEF" "GHI"])))
    (is (= (set ["AFKP" "BGL" "CH" "D" "EJO" "IN" "M"]) (set (extract-diagonals ["ABCD" "EFGH" "IJKL" "MNOP"]))))
    )

  (testing "extract-other-diagonals"
    (is (= (set ["A" "D" "BC"]) (set (extract-other-diagonals ["AB" "CD"]))))
    (is (= (set ["CEG" "FH" "I" "BD" "A"]) (set (extract-other-diagonals ["ABC" "DEF" "GHI"]))))
    (is (= (set ["MSAMMMMXAM" "AMSXXSAMX" "MMAXAMMM"
                 "XMASAMX" "MMXSXA" "ASAMX" "SAMM" "AMA" "MS" "X"
                 "SMAAAMSAM" "ASMASAMS" "MMXMAXS" "XXSAMX" "XMXSX"
                 "SAMM" "MSA" "MM" "M"])
           (set (extract-other-diagonals acceptance-input)))
        )
    )
  )


(deftest all-tests-part2
  )
