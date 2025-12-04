(ns day2.solver-test
  (:require [clojure.test :refer :all]
            [day2.solver :refer :all]
            [util.util :refer :all]))

(def input (transform-file-to-list-of-lines "resources/day2/input.txt"))
(def acceptance-input (transform-file-to-list-of-lines "resources/day2/acceptance-input.txt"))

(deftest acceptance-testing
  (testing "part 1"
    (is (= 1227775554 (solve1 acceptance-input)))
    (is (= 29940924880 (solve1 input)))
    )
  (testing "part 2"
    (is (= 4174379265 (solve2 acceptance-input)))
    (is (= 48631958998 (solve2 input)))
    )
  )
(deftest unit-testing
  (testing "helper functions"
    (is (= [[11 22] [0 1]] (create-ranges "11-22,0-1")))
    (is (is-invalid-id-part2? 99))
    (is (is-invalid-id-part2? 111))
    (is (is-invalid-id-part2? 1010))
    (is (is-invalid-id-part2? 1188511885))
    (is (is-invalid-id-part2? 222222))
    (is (is-invalid-id-part2? 446446))
    (is (is-invalid-id-part2? 38593859))
    (is (is-invalid-id-part2? 565656))
    (is (is-invalid-id-part2? 824824824))
    (is (is-invalid-id-part2? 2121212121))
    (is (false? (is-invalid-id-part2? 20202)))
    )
  )


