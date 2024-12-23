(ns advent-of-code.day8.solver
  (:require [clojure.math.combinatorics :as combo]
            [advent-of-code.util.util :refer :all]))

(defn extract-all-antenna-positions [input]
  (->>
    input
    (transform-to-cells)
    (filter #(not (= (second %) \.
                     )))
    (into {})
    )
  )
(defn- in-bound? [size [x y]]
  (and (< x size) (< y size) (<= 0 x) (<= 0 y))
  )

(defn one-antinode [x1 x2 y1 y2]
  [(- (+ x1 x1) x2) (- (+ y1 y1) y2)])

(defn- determine-antinodes [[[x1 y1] [x2 y2]] size]
  (set (filter (partial in-bound? size) [(one-antinode x1 x2 y1 y2) (one-antinode x2 x1 y2 y1)]))
  )

(defn antinodes [frequency antenna-positions map-size]
  (let [generating-antennas (map key (filter #(= (second %) frequency) antenna-positions))]
    (->> (combo/combinations generating-antennas 2)         ;; Generate all combinations
         (map #(determine-antinodes (vec %) map-size))      ;; Compute antinodes for each combination
         (reduce into #{}))))                               ;; Merge all results into a single set

(defn solve1 [input]
  (let [antenna-positions (extract-all-antenna-positions input)
        frequencies (set (map val antenna-positions))]
    (println antenna-positions)
    (println frequencies)
    (count (set (reduce concat (map #(antinodes % antenna-positions (count input)) frequencies))))
    )
  )


(defn solve2 [input]
  )




