(ns advent-of-code.day2.solver
  (:require [clojure.string :as str]))

(defn safe? [sequence]
  (or (every? (fn [[a b]] (and (< a b) (>= 3 (- b a)))) (partition 2 1 sequence))
      (every? (fn [[a b]] (and (< b a) (>= 3 (- a b)))) (partition 2 1 sequence))))

(defn updated-safe? [sequence]
  (let [list1 (map (fn [[a b]] (and (< a b) (>= 3 (- b a)))) (partition 2 1 sequence))
        list2 (map (fn [[a b]] (and (< b a) (>= 3 (- a b)))) (partition 2 1 sequence))]
    (or (> 2 (count (filter #((false? %)) list1))) (> 2 (count (filter #((false? %)) list2))))
    ))

(defn is-safe [sequence safety-function]
  (let
    [number-sequence (map read-string (str/split sequence #"\s+"))
     ]
    (safety-function number-sequence))

  )
(defn solve1 [input]
  (count (filter #(is-safe % safe?) input))
  )
(defn solve2 [input]
  (count (filter #(is-safe % updated-safe?) input)))




