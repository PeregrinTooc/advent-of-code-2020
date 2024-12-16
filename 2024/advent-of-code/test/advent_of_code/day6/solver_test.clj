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



(deftest unit-tests
  (testing "one step at a time"
    (is (= [".X"
            ".."] (step [".^"
                         ".."])))
    (is (= [".^"
            ".X"] (step [".X"
                         ".^"])))
    )

  )



