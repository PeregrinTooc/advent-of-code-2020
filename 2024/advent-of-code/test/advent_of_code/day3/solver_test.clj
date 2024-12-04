(ns advent-of-code.day3.solver-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day3.solver :refer :all]
            [advent-of-code.util.util :refer :all]))

(def input (slurp "resources/advent_of_code/day3/input.txt"))


(deftest all-tests-part1
  (testing "Acceptance Test"
    (is (= 161 (solve1 "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"))))

  (testing "Solution Tests"
    (is (= 166630675 (solve1 input))))
  )

(deftest unit-tests
  (testing "extract pairs"
    (is (= '("2,4") (extract-pairs "mul(2,4)")))
    (is (= '() (extract-pairs "mul(2223,4)")))
    (is (= '() (extract-pairs "mul(,4)")))
    (is (= '() (extract-pairs "mul(2,)")))
    (is (= '() (extract-pairs "mul(2,X)")))
    (is (= '() (extract-pairs "mul(2,1234)")))
    (is (= '("2,4") (extract-pairs "\"mul(2,4)%&mul")))
    (is (= '("2,4" "3,5") (extract-pairs "\"xmul(2,4)%&mul(3,5)")))
    (is (= '("2,4") (extract-pairs "\"mul(2,4)%&mulx(3,5)")))
    (is (= '("2,4" "5,5" "11,8" "8,5") (extract-pairs "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")))
    )
  (testing "multiplication and summation"
    (is (= 0 (multiply-and-add '())))
    (is (= 1 (multiply-and-add '("1,1"))))
    (is (= 2 (multiply-and-add '("1,2"))))
    (is (= 2 (multiply-and-add '("1,1" "1,1"))))
    )
  (testing "extract-instructions"
    (is (= '("2,4") (extract-instructions "mul(2,4)")))
    (is (= '("2,4" "do") (extract-instructions "xxfdmul(2,4)xxxdsdo()")))
    (is (= '("2,4" "don't") (extract-instructions "xxfdmul(2,4)xxxdsdon't()")))
    (is (= '("2,4" "don't" "5,5" "11,8" "do" "8,5") (extract-instructions "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")))
    )
  (testing "no instructions return identical"
    (is (= '("2,4") (filter-donts '("2,4"))))
    )
  (testing "skip all after (last) don't"
    (is (= '() (filter-donts '("don't" "2,4"))))
    )
  (testing "include all after (last) do"
    (is (= '("2,4") (filter-donts '("don't" "do" "2,4"))))
    )
  (testing "first instruction do is ignored"
    (is (= '("2,4") (filter-donts '("do" "2,4"))))
    )
  (testing "last instruction don't is ignored"
    (is (= '("2,4") (filter-donts '("do" "2,4" "don't"))))
    )
  (testing "transformed acceptance"
    (is (= '("2,4" "8,5") (filter-donts '("2,4" "don't" "5,5" "11,8" "do" "8,5"))))
    )
  )




(deftest all-tests-part2
  (testing "Acceptance Test"
    (is (= 48 (solve2 "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))))
  (testing "Solution Tests"
    (is (= 93465710 (solve2 input))))
  )