(ns advent-of-code.day3.solver
  (:require [clojure.string :as str]))

(declare filter-donts include-instructions)

(defn extract-pairs [sequence]
  (map #(str/replace % #"mul\(|\)" "") (re-seq #"mul\(\d{1,3}+,\d{1,3}\)" sequence)))

(defn extract-instructions [sequence]
  (map #(str/replace % #"mul\(|\)|\(" "") (re-seq #"mul\(\d{1,3}+,\d{1,3}\)|do\(\)|don't\(\)" sequence))
  )

(defn multiply-and-add [instructions]
  (if (empty? instructions)
    0
    (reduce + (map #(reduce * (map read-string (str/split % #"\,"))) instructions))
    )
  )

(defn skip-instructions [[first-instruction & rest]]
  (if (empty? rest)
    '()
    (if (= first-instruction "do")
      (include-instructions rest)
      (skip-instructions rest)
      )
    )
  )
(defn include-instructions [[first-instruction & rest]]
  (if (empty? rest)
    [first-instruction]
    (cons first-instruction (filter-donts rest)))
  )

(defn filter-donts [[first-instruction & rest]]
  (if (= first-instruction "don't")
    (skip-instructions rest)
    (if (= first-instruction "do")
      (include-instructions rest)
      (include-instructions (cons first-instruction rest))
      )
    )
  )

(defn solve1 [input]
  (multiply-and-add (extract-pairs input))
  )
(defn solve2 [input]
  (multiply-and-add (filter-donts (extract-instructions input)))
  )




