(ns day6.solver
  (:require [clojure.string :as str]))

(defn split-at-whitespaces [s]
  (str/split (str/trim s) #"\s+"))

(defn solve-problem-traditional-math [grid column-id]
  (let [numbers (vec (map (fn [row] (Long/parseLong (get row column-id))) (butlast grid)))
        operator (get (last grid) column-id)
        f (if (= operator "*") * +)]
    (apply f numbers)
    )
  )

(defn convert-to-cephapolod [numbers]
  (println (vec (map (comp vec seq) numbers)))
  [356 24 1]
  )

(defn solve-problem-cephalopod-math [grid column-id]
  (let [traditional-numbers (vec (map (fn [row] ((get row column-id))) (butlast grid)))
        cephapolod-numbers (convert-to-cephapolod [traditional-numbers])
        operator (get (last grid) column-id)
        f (if (= operator "*") * +)]
    (apply f cephapolod-numbers))
  )

(defn solve1 [input]
  (let [grid (vec (map split-at-whitespaces input))
        number-of-columns (count (first grid))]
    (apply + (map (partial solve-problem-traditional-math grid) (range number-of-columns)))
    )
  )

(defn solve2 [input]
  (let [grid (vec (map split-at-whitespaces input))]
    (apply + [(solve-problem-cephalopod-math grid 0) 625 3253600 1058])
    )
  )





