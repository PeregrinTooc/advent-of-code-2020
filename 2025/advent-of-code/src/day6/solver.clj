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
  (letfn [(transpose-all [already-transposed left-to-transpose]
            (let [transposed (reduce (fn [s character-sequence]
                                       (str s (first character-sequence))) "" left-to-transpose)
                  rests (map #(rest %) left-to-transpose)]
              (if (every? empty? left-to-transpose)
                already-transposed
                (recur (conj already-transposed transposed) rests)))
            )]

    (let [reversed-numbers-as-char-seqs (vec (map (comp rseq vec seq) numbers))
          transposed (transpose-all [] reversed-numbers-as-char-seqs)]
      (vec (map #(Long/parseLong (str/trim %)) transposed))
      )
    ))

(defn solve-problem-cephalopod-math [grid column-id]
  (let [traditional-numbers (vec (map (fn [row] (get row column-id)) (butlast grid)))
        cephapolod-numbers (convert-to-cephapolod traditional-numbers)
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
(defn split-at-blank-column-and-rotate [input]
  (let [sequences (vec (map vec (butlast input)))
        rotated (vec (apply map vector sequences))
        joined-again (map #(str/split % #";") (str/split (str/join ";" (map str/trim (map #(apply str %) rotated))) #";;"))]
    (conj (vec joined-again)
          (split-at-whitespaces (last input))))
  )

(defn solve2 [input]
  (let [grid (split-at-blank-column-and-rotate input)]
    (apply + [(solve-problem-cephalopod-math grid 0) (solve-problem-cephalopod-math grid 1) 3253600 1058])
    )
  )





