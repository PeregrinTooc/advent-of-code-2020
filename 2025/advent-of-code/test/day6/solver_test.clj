(ns day6.solver-test
  (:require [clojure.test :refer :all]
            [day6.solver :refer :all]
            [clojure.string :as str]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day6/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day6/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 4277556 (solve1 acceptance-input)))
    (is (= 5381996914800 (solve1 input)))
    )
  (testing "part 2"
    ;not working due to the removal of the needed trailing blanks in the txt file
    ;(is (= 3263827 (solve2 acceptance-input)))
    (is (= 9627174150897 (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= [["1" "24" "356"]
            ["369" "248" "8"]
            ["32" "581" "175"]
            ["623" "431" "4"]
            ["*" "+" "*" "+"]]
           (split-at-blank-column-and-rotate ["123 328  51 64 "
                                              " 45 64  387 23 "
                                              "  6 98  215 314"
                                              "*   +   *   +"])))
    )
  )

