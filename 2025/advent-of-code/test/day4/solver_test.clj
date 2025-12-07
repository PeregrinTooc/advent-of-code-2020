(ns day4.solver-test
  (:require [clojure.test :refer :all]
            [day4.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day4/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day4/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 13 (solve1 acceptance-input)))
    (is (= 1409 (solve1 input)))
    )
  (testing "part 2"
    (is (= 43 (solve2 acceptance-input)))
    (is (= 8366 (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= [[\a \b \c] [\d \e \f] [\g \h \i]] (transform-to-grid ["abc" "def" "ghi"])))
    (is (nil? (get-in [[1] [1]] [-1 -1])))
    (is (= 1 (get-in [[1 2] [3 4]] [0 0])))
    )
  )


