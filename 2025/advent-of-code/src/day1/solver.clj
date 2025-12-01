(ns day1.solver
  (:require [clojure.string :as str]))

(def op-map
  {\L -
   \R +})

(defn parse-command [s]
  (let [operator (op-map (first s))
        argument (Integer/parseInt (subs s 1))]
    [operator argument]
    )
  )

(defn map-input-to-functions [input]
  (map parse-command input)
  )

(defn map-input-to-next-result [input]
  (let [functions (map-input-to-functions input)]
    (map #(mod % 100) (reductions (fn [acc [op arg]] (op acc arg)) 50 functions))
    )
  )

(defn solve1 [input]
  (let [numbers (map-input-to-next-result input)]
    (count (filter #(= % 0) numbers))
    )
  )

(defn passes-zero [n m]
  (let [a (quot n 100) b (quot m 100)]
    (abs (- a b))
    )
  )

(defn apply-input-and-count-rotations [input]
  (let [functions (map-input-to-functions input)
        no-modulo-sequence (reductions (fn [acc [op arg]] (op acc arg)) 50 functions)]
    (println no-modulo-sequence)
    (println (rest no-modulo-sequence))
    (println (map passes-zero no-modulo-sequence (rest no-modulo-sequence)))
    (map passes-zero no-modulo-sequence (rest no-modulo-sequence))
    )
  )

(defn solve2 [input]
  (+ (reduce + (apply-input-and-count-rotations input)) (solve1 input))
  )




