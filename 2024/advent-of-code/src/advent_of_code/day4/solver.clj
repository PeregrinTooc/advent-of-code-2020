(ns advent-of-code.day4.solver
  (:require [clojure.string :as str]))

(defn count-xmas-occurrences [string]
  (+ (count (re-seq #"XMAS" string)) (count (re-seq #"XMAS" (apply str (reverse string)))))
  )

(defn- extract-columns-from-matrix [[first-line & other-lines] column-count]
  (if (>= column-count (count first-line))
    nil
    (let [first-column (map #(nth % column-count) (cons first-line other-lines))]
      (vec (cons (str/join first-column) (extract-columns-from-matrix (cons first-line other-lines) (inc column-count))))
      )
    )
  )
(defn- create-matrix-from-lines [lines]
  (vec (map #(str/split % #"") lines))
  )

(defn extract-columns [lines]
  (if (empty? lines)
    []
    (extract-columns-from-matrix (create-matrix-from-lines lines) 0)
    )
  )
(defn extract-diagonals [lines]
  [])

(defn solve1 [input]
  (+ (reduce + (map count-xmas-occurrences input)) (reduce + (map count-xmas-occurrences (extract-columns input))))
  )
(defn solve2 [input]

  )




