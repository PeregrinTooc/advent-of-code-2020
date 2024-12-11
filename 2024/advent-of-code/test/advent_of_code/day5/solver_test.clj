(ns advent-of-code.day5.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day5.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/advent_of_code/day5/input.txt"))
(def acceptance-input ["47|53"
                       "97|13"
                       "97|61"
                       "97|47"
                       "75|29"
                       "61|13"
                       "75|53"
                       "29|13"
                       "97|29"
                       "53|29"
                       "61|53"
                       "97|53"
                       "61|29"
                       "47|13"
                       "75|47"
                       "97|75"
                       "47|61"
                       "75|61"
                       "47|29"
                       "75|13"
                       "53|13"
                       ""
                       "75,47,61,53,29"
                       "97,61,53,29,13"
                       "75,29,13"
                       "75,97,47,61,53"
                       "61,13,29"
                       "97,13,75,29,47"])

(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 143 (solve1 acceptance-input)))
    (is (= 3608 (solve1 input))))
  )

(deftest all-tests-part2
  (testing "Acceptance Test"
    (is (= 123 (solve2 acceptance-input)))
    ;(is (= 3608 (solve2 input)))
    )
  )

(deftest unit-tests
  (testing "input transformation"
    (is (= [] (create-rules-and-pages [])))
    (is (= [] (create-rules-and-pages [""])))
    (is (= [["53|13"] []] (create-rules-and-pages ["53|13" ""])))
    (is (= [["53|13"] ["21"]] (create-rules-and-pages ["53|13" "" "21"])))
    (is (= [["75|13" "53|13"] ["75,47,61,53,29" "97,61,53,29,13"]]
           (create-rules-and-pages ["75|13" "53|13" "" "75,47,61,53,29" "97,61,53,29,13"])))
    )
  (testing "rule-matching"
    (is (= true (matches-rule "3,4" "1|2")) "1|2 is not applicable to 3,4")
    (is (= true (matches-rule "1,4" "1|2")) "1|2 is not applicable to 1,4")
    (is (= false (matches-rule "2,1" "1|2")) "2,1 does not fulfill 1|2")
    (is (= true (matches-rule "1,2" "1|2")) "1,2 fulfills 1|2")
    )
  (testing "receiving middle element"
    (is (= 1 (middle-of [1])))
    (is (= 1 (middle-of [2 1 "3"])))
    (is (= "75" (middle-of ["97" "13" "75" "29" "47"])))
    )

  (testing "sort by rule"
    (is (= -1 (sort-with ["1|2"] 1 2)) "1,2 does already fulfill 1|2")
    (is (= 1 (sort-with ["1|2"] 2 1)) "1 must come before 2 according to 1|2")
    (is (= 1 (sort-with ["1|2" "4|2"] 2 4)) "4 must come before 2 according to 4|2")
    (is (= -1 (sort-with ["1|2" "4|2"] 4 2)) "4 must come before 2 according to 4|2")
    )
  )


