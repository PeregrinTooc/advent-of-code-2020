(ns advent-of-code.day6.solver-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [advent-of-code.day6.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day5/input.txt"))
(def acceptance-input ["....#....."
                       ".........#"
                       ".........."
                       "..#......."
                       ".......#.."
                       ".........."
                       ".#..^....."
                       "........#."
                       "#........."
                       "......#..."])


(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 41 (solve1 acceptance-input)))
    (is (= nil (solve1 input))))
  )

(deftest all-tests-part2
  (testing "Acceptance Test"
    (is (= nil (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )

(defn group-by-row [cells-map]
  "Groups the map entries by row index."
  (group-by (fn [[[row _] _]] row) cells-map))

(defn sort-row [row]
  "Sorts a single row by column index and extracts cell values."
  (->> row
       (sort-by (fn [[[row col] _]] col))                   ;; Sort by column index
       (map (fn [[[_ _] value]] value))))                   ;; Extract cell values

(defn combine-rows [grouped]
  "Combines sorted rows into strings to reconstruct the original array."
  (->> (sort-by key grouped)                                ;; Sort by row index
       (map (fn [[_ row]]                                   ;; Process each group
              (->> (sort-row row)                           ;; Sort columns in the row
                   (apply str))))))                         ;; Combine values into a single string

(defn cells-to-array [cells-map]
  "Transforms a map of cells back into an array of strings."
  (-> cells-map
      group-by-row                                          ;; Group by row index
      combine-rows                                          ;; Combine rows into strings
      vec))                                                 ;; Return as a vector

(def idx (comp cells-to-array transform-to-cells))
(def read-after-step (comp cells-to-array step transform-to-cells))

(deftest unit-tests
  (testing "one step at a time"
    (is (= [".X"
            ".."] (read-after-step [".^"
                                    ".."])))
    (is (= [".X"
            ".."] (read-after-step [".X"
                                    ".."])) "should not do anything if no guard is present")
    (is (= [".^"
            ".X"] (read-after-step [".X"
                                    ".^"])) "leaves at the top if at the end of map")
    (is (= ["#."
            ">."] (read-after-step ["#."
                                    "^."])) "turns right by 90° if blocked")
    (is (= ["#."
            "v#"] (read-after-step ["#."
                                    ">#"])) "turns downwards if blocked to the right")
    (is (= ["#<"
            ".#"] (read-after-step ["#v"
                                    ".#"])) "turns to left if blocked to the bottom")
    (is (= ["#^"
            ".#"] (read-after-step ["#<"
                                    ".#"])) "turns upwards if blocked to the left")
    (is (= ["#."
            ".X"] (read-after-step ["#."
                                    ".>"])) "leaves to the right if at end of map")
    (is (= ["#."
            "X."] (read-after-step ["#."
                                    "<."])) "leaves to the left if at end of map")
    (is (= ["#."
            "X."] (read-after-step ["#."
                                    "v."])) "leaves to the bottom if at end of map")
    )
  (testing "transform to cells"
    (is (= {[0 0] \X} (transform-to-cells ["X"])))
    (is (= {[0 0] \X, [0 1] \.} (transform-to-cells ["X."])))
    (is (= {[0 0] \A, [0 1] \B
            [1 0] \C, [1 1] \D} (transform-to-cells ["AB" "CD"])))
    (is (= {[0 0] \A, [0 1] \B [0 2] \C
            [1 0] \D, [1 1] \E [1 2] \F
            [2 0] \G, [2 1] \H [2 2] \I} (transform-to-cells ["ABC" "DEF" "GHI"])))
    )
  (testing "re-transfomration"

    (is (= ["aBc"] (idx ["aBc"]))
        )
    )
  )



