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
    ;(some #(= id %) [5 11 17])
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

(defn solve2 [input]
  (let [fresh-id-ranges-strings (first (parse-db-file input))
        fresh-id-ranges (map transform-range-string-to-vec fresh-id-ranges-strings)
        sorted-ranges (sort-by first fresh-id-ranges)
        ]
    (count (set (flatten (map (fn [[low high]] (range low (inc high))) fresh-id-ranges)))))
  )




