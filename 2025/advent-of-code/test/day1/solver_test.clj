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
    ;(is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= 0 (mod (+ 52 48) 100)))
    (is (= [+ 10] (parse-command "R10")))
    (is (= 0 (passes-zero 1 2)))
    (is (= 1 (passes-zero 1 101)))
    (is (= 1 (passes-zero 99 101)))
    (is (= 1 (passes-zero 50 -18)))
    (is (= 2 (passes-zero -101 101)))
    (is (= 2 (passes-zero -201 -1)))
    (is (= -1 (quot -101 100)))
    )

  )


