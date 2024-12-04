(ns advent-of-code.day2.solver
  (:require [clojure.string :as str]))

(defn safe? [sequence]
  (or (every? (fn [[a b]] (and (< a b) (>= 3 (- b a)))) (partition 2 1 sequence))
      (every? (fn [[a b]] (and (< b a) (>= 3 (- a b)))) (partition 2 1 sequence))))

(defn subsequences [sequence]
  (map #(concat (take % sequence) (drop (inc %) sequence))
       (range (count sequence))))


(defn updated-safe? [sequence]
  (if (safe? sequence)
    true
    (some safe? (subsequences sequence))
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
  (count (filter #(is-safe % updated-safe?) input))
  )




