(ns advent-of-code.day1.solver
  (:require [clojure.string :as str]))
(defn transform-to-lists
  ([string]
   (let [[first-list second-list] (map read-string (str/split string #"\s+"))]
     [first-list second-list]))
  ([string first-list second-list]
   (let [[n1 n2] (transform-to-lists string)]
     [(conj first-list n1) (conj second-list n2)])))

(defn transform [[string & rest] l1 l2]
  (let [[new-l1 new-l2] (transform-to-lists string l1 l2)]
    (if (empty? rest)
      [new-l1 new-l2]
      (transform rest new-l1 new-l2))))

(defn calculate-diff-sum [list1 list2]
  (reduce + (map #(Math/abs (- %1 %2)) list1 list2)))

(defn solve1 [input]
  (let [[l1 l2] (transform input [] [])]
    (calculate-diff-sum (sort l1) (sort l2))
    )
  )
(defn map-to-frequencies [coll1 coll2]
  (let [freq-map (frequencies coll2)]
    (map #(get freq-map % 0) coll1)))

(defn solve2 [input]
  (let [[l1 l2] (transform input [] [])] (reduce + (map #(* %1 %2) l1 (map-to-frequencies l1 l2)))))




