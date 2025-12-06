(ns day3.solver-test
  (:require [clojure.test :refer :all]
            [day3.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day3/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day3/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 357 (solve1 acceptance-input)))
    (is (= 17613 (solve1 input)))
    )
  (testing "part 2"
    (is (= 3121910778619 (solve2 acceptance-input)))
    (is (= 175304218462560 (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= 98 (determine-highest-joltage 2 "987")))
    (is (= 45 (determine-highest-joltage 2 "45")))
    (is (= 98 (determine-highest-joltage 2 "978")))
    (is (= 987654321111 (determine-highest-joltage 12 "987654321111111")))
    (is (= 987 (determine-highest-joltage 3 "9876")))
    )
  )


