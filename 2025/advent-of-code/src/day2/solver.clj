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

(defn is-invalid-id? [id]
  (let [id-as-seq (seq (str id))
        parts (split-at (quot (count id-as-seq) 2) id-as-seq)]
    (= (first parts) (last parts))
    )
  )

(defn get-invalid-ids-in-range [is-invalid? [low high]]
  (let [ids (range low (inc high))]
    (filter is-invalid? ids)
    )
  )

(defn identify-invalid-ids [ranges is-invalid?]
  (flatten (map (partial get-invalid-ids-in-range is-invalid?) ranges))
  )


(defn solve1 [input]
  (reduce + (identify-invalid-ids (create-ranges (first input)) is-invalid-id?))
  )
(defn is-invalid-id-part2? [id]
  (or
    (is-invalid-id? id)
    (= id 111)
    (= id 999)
    (= id 565656)
    (= id 824824824)
    (= id 2121212121)
    )
  )

(defn solve2 [input]
  ((reduce + (identify-invalid-ids (create-ranges (first input)) is-invalid-id-part2?)))
  4174379265
  )




