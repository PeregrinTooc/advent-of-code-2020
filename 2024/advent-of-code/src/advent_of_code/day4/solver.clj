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
(defn left-to-right-diagonals [matrix]
  (map #((matrix %) (- 1 %)) (range (count matrix)))
  )

(defn join-to-diagonals [trails diagonals]
  (vec (map (fn [i]
              (let [diagonal (diagonals i)
                    trail (trails i)
                    joined (str/join [diagonal trail])]
                joined))
            (range (count trails)))
       )
  )

(defn drop-last-line-and-column [lines]
  (vec (extract-columns (drop-last (extract-columns (drop-last lines)))))
  )
(defn drop-first-line-and-column [lines]
  (vec (extract-columns (subvec (extract-columns (subvec lines 1)) 1)))
  )

(defn extract-trails [lines]
  (let [matrix (create-matrix-from-lines lines)]
    (if (= 2 (count matrix))
      [((matrix (dec (count matrix))) (dec (count matrix)))]
      (vec (concat [((matrix (dec (dec (count matrix)))) (dec (count matrix)))
                    ((matrix (dec (count matrix))) (dec (dec (count matrix))))
                    ] (extract-trails (drop-first-line-and-column lines))))
      ))
  )

(defn extract-diagonals [lines]
  (if (<= (count lines) 1)
    lines
    (let [matrix (create-matrix-from-lines lines)]
      (let [smaller-matrix (drop-last-line-and-column lines)
            intermediate (extract-diagonals smaller-matrix)
            joined ((partial join-to-diagonals (extract-trails lines)) intermediate)
            ]
        (vec (concat [((matrix 0) (dec (count matrix)))
                      ((matrix (dec (count matrix))) 0)] joined))
        )
      )
    )
  )

(defn solve1 [input]
  (+ (reduce + (map count-xmas-occurrences input))
     (reduce + (map count-xmas-occurrences (extract-columns input)))
     (reduce + (map count-xmas-occurrences (extract-diagonals input)))
     (reduce + (map count-xmas-occurrences (extract-diagonals (vec (map #(apply str (reverse %)) input)))))
     )
  )
(defn solve2 [input]

  )




