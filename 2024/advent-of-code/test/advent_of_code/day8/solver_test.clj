(ns advent-of-code.day8.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day8.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day8/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/advent_of_code/day8/acceptance.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 14 (solve1 acceptance-input)))
    (is (= 265 (solve1 input)))
    )
  (testing "part 2"
    (is (= 34 (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "all antennas with their locations"
    (is (= (into {} [[[8 8] \A]
                     [[2 5] \0]
                     [[9 9] \A]
                     [[1 8] \0]
                     [[5 6] \A]
                     [[4 4] \0]
                     [[3 7] \0]]) (extract-all-antenna-positions acceptance-input)))
    )
  (testing "antinodes"
    (is (= (set [[7 7] [10 10]]) (antinodes \A (into {} [[[8 8] \A] [[9 9] \A] [[4 5] \0]]) 12)) "only looks for the given frequency")
    (is (= (set [[7 7] [10 10]]) (antinodes \A (into {} [[[8 8] \A] [[9 9] \A]]) 12)))
    (is (= (set [[2 2] [5 5] [1 4] [3 6] [6 0] [7 1]]) (antinodes \A (into {} [[[5 2] \A] [[3 3] \A] [[4 4] \A]]) 12)))
    (is (= (set [[7 7]]) (antinodes \A (into {} [[[8 8] \A] [[9 9] \A]]) 9)) "discard those out of bounds of the map")
    (is (= (set [[2 2]]) (antinodes \A (into {} [[[0 0] \A] [[1 1] \A]]) 9)) "discard those out of bounds of the map")

    )

  )
