(ns advent-of-code.day4.solver
  (:require [clojure.string :as str]))

(defn count-xmas-occurences [string]
  (count (re-seq #"XMAS" string))
  )

(defn- extract-columns-from-matrix [[first-line & other-lines]]
  (if (empty? other-lines)
    first-line
    [(str/join [(first-line 0) ((first other-lines) 0)])]
    )
  )

(defn extract-columns [lines]
  (if (empty? lines)
    [""]
    (let [matrix (vec (map #(str/split % #"") lines))]
      (extract-columns-from-matrix matrix)
      )
    )
  )

(defn solve1 [input]

  )
(defn solve2 [input]

  )




