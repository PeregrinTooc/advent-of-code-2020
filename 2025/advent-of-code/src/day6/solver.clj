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

(defn solve-problem-cephalopod-math [grid column-id]
  (let [operator (get (last grid) column-id)
        f (if (= operator "*") * +)]
    (apply f (map #(Integer/parseInt %) (get grid column-id))))
  )

(defn solve1 [input]
  (let [grid (vec (map split-at-whitespaces input))
        number-of-columns (count (first grid))]
    (apply + (map (partial solve-problem-traditional-math grid) (range number-of-columns)))
    )
  )
(defn split-at-blank-column-and-rotate [input]
  (let [sequences (vec (map vec (butlast input)))
        rotated (vec (apply map vector sequences))
        joined-again (map #(str/split % #";") (str/split (str/join ";" (map str/trim (map #(apply str %) rotated))) #";;"))]
    (conj (vec joined-again)
          (split-at-whitespaces (last input))))
  )

(defn solve2 [input]
  (let [grid (split-at-blank-column-and-rotate input)
        number-of-columns (count (first (map split-at-whitespaces input)))]
    (apply + (map (partial solve-problem-cephalopod-math grid) (range number-of-columns)))
    )
  )





