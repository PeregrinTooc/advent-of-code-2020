(ns day2.solver
  (:require [clojure.string :as str]))
(defn transform-range-string-to-vec [s]
  (let [parts (str/split s #"-")]
    [(Long/parseLong (first parts)) (Long/parseLong (last parts))]
    )
  )

(defn create-ranges [input]
  (map transform-range-string-to-vec (str/split input #","))
  )



(defn get-invalid-ids-in-range [is-invalid? [low high]]
  (let [ids (range low (inc high))]
    (filter is-invalid? ids)
    )
  )

(defn identify-invalid-ids [ranges is-invalid?]
  (flatten (map (partial get-invalid-ids-in-range is-invalid?) ranges))
  )

(defn repeating-pattern
  "Returns true if seq xs is made only of repeated copies of it.
   Otherwise returns false."
  [xs]
  (let [n (count xs)]
    (first
      (for [k (range 1 (inc (quot n 2)))
            :let [p (take k xs)]
            :when (and (= 0 (mod n (count p))) (= xs (take n (cycle p))))]
        (if p true false)
        )
      )
    )
  )

(defn is-invalid-id? [id]
  (let [id-as-seq (seq (str id))
        parts (split-at (quot (count id-as-seq) 2) id-as-seq)]
    (= (first parts) (last parts))
    )
  )

(defn is-invalid-id-part2? [id]
  (let [id-as-seq (seq (str id))]
    (repeating-pattern id-as-seq)
    )
  )

(defn solve1 [input]
  (reduce + (identify-invalid-ids (create-ranges (first input)) is-invalid-id?))
  )

(defn solve2 [input]
  (reduce + (identify-invalid-ids (create-ranges (first input)) is-invalid-id-part2?))
  )




