(ns day6.solver-test
  (:require [clojure.test :refer :all]
            [day6.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day6/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day6/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 4277556 (solve1 acceptance-input)))
    (is (= 5381996914800 (solve1 input)))
    )
  (testing "part 2"
    (is (= 3263827 (solve2 acceptance-input)))
    ;(is (= nil (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= [356 24 1] (convert-to-cephapolod ["123" " 45" "  6"])))
    (is (= [8 248 369] (convert-to-cephapolod ["328" "64 " "98 "])))
    (is (= [13 2] (convert-to-cephapolod ["1" "23"])))
    (is (= [["123" "328" " 51" "64 "]
            [" 45" "64 " "387" "23 "]
            ["  6" "98 " "215" "314"]
            ["*" "+" "*" "+"]]
           (split-at-blank-column-and-rotate ["123 328  51 64 "
                                              " 45 64  387 23 "
                                              "  6 98  215 314"
                                              "*   +   *   +"])))
    )
  )

