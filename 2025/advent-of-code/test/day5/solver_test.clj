(ns day5.solver-test
  (:require [clojure.test :refer :all]
            [day5.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day5/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day5/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 3 (solve1 acceptance-input)))
    (is (= 744 (solve1 input)))
    )
  (testing "part 2"
    (is (= 14 (solve2 acceptance-input)))
    (is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= [[0 10]] (merge-ranges [[0 10]] [1 9])))
    (is (= [[0 11]] (merge-ranges [[0 10]] [1 11])))
    (is (= [[0 11]] (merge-ranges [[0 10]] [10 11])))
    (is (= [[0 11]] (merge-ranges [[0 10]] [11 11])))
    (is (= [[0 1] [3 4]] (merge-ranges [[0 1]] [3 4])))
    )
  )


