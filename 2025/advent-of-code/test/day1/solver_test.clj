(ns day1.solver-test
  (:require [clojure.test :refer :all]
            [day1.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day1/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day1/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 3 (solve1 acceptance-input)))
    (is (= 1086 (solve1 input)))
    )
  (testing "part 2"
    (is (= 6 (solve2 acceptance-input)))
    (is (= 6268 (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= -1 (- 0 1)))
    (is (= 0 (mod (+ 52 48) 100)))
    (is (= [+ 10] (parse-command "R10")))
    )

  )


