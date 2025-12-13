(ns day5.solver
  (:require [clojure.string :as str]
            [util.util :refer [transform-range-string-to-vec]]))

(defn parse-db-file [db]
  (let [empty-line (.indexOf db "")]
    [(vec (take empty-line db)) (vec (drop (inc empty-line) db))]
    )
  )

(defn fresh? [fresh-id-ranges-strings id]
  (let [fresh-id-ranges (map transform-range-string-to-vec fresh-id-ranges-strings)]
    (some #(<= (first %) id (last %)) fresh-id-ranges)
    )
  )

(defn determine-fresh-goods [db]
  (let [[fresh-id-ranges ingredient-ids] (parse-db-file db)]
    (filter (partial fresh? fresh-id-ranges) (map #(Long/parseLong %) ingredient-ids)))
  )

(defn solve1 [input]
  (let [fresh-goods (determine-fresh-goods input)]
    (count fresh-goods))
  )

(defn merge-ranges [merged-ranges [low high]]
  (let [[last-low last-high] (last merged-ranges)]
    (cond (>= last-high high) merged-ranges
          (and (<= (dec low) last-high) (< last-high high)) (conj (vec (drop-last merged-ranges)) [last-low high])
          :else (conj merged-ranges [low high]))))

(defn solve2 [input]
  (let [fresh-id-ranges-strings (first (parse-db-file input))
        fresh-id-ranges (map transform-range-string-to-vec fresh-id-ranges-strings)
        sorted-ranges (sort-by first fresh-id-ranges)
        merged-ranges (reduce merge-ranges [(first sorted-ranges)] (rest sorted-ranges))
        ]
    (reduce (fn [acc [low high]] (+ 1 acc (- high low))) 0 merged-ranges)
    )
  )




