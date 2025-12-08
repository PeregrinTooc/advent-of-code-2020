(ns day6.solver
  (:require [clojure.string :as str]))

(defn split-at-whitespaces [s]
  (str/split (str/trim s) #"\s+"))

(defn solve-problem [grid column-id]
  (let [numbers (vec (map (fn [row] (Long/parseLong (get row column-id))) (butlast grid)))
        operator (get (last grid) column-id)
        f (if (= operator "*") * +)]
    (apply f numbers)
    )

  )

(defn solve1 [input]
  (let [grid (vec (map split-at-whitespaces input)) length (count (first grid))]
    (apply + (map (partial solve-problem grid) (range length)))
    )
  )

(defn solve2 [input]
  )




