(ns advent-of-code.day7.solver
  (:require [clojure.string :as str]))
(defn all-results-with [operators [arg0 arg1 & tail]]
  (if (nil? arg1)
    (map #(apply % [arg0]) operators)
    (let [first-results (map #(apply % [arg0 arg1]) operators)]
      (if (nil? tail)
        first-results
        (->> first-results
             (map #(cons % tail))
             (map #(all-results-with operators %))
             (apply concat))))))

(defn split-input-line [line]
  (let [[test-value inputs-as-string] (str/split line #":")
        inputs (map read-string (str/split (str/triml inputs-as-string) #" "))]
    [(read-string test-value) inputs])
  )

(defn ||
  ([arg]
   arg)
  ([arg1 arg2]
   (read-string (str/join [(str arg1) (str arg2)]))
   )
  )

(defn valid? [operators [test-value input-numbers]]
  (some #(= % test-value) (all-results-with operators input-numbers))
  )


(defn solve1 [input]
  (let [validity-function (partial valid? [+ *])]
    (->> input
         (map split-input-line)
         (filter validity-function)
         (map #(% 0))
         (reduce +)))
  )

(defn solve2 [input]
  (let [validity-function (partial valid? [+ * ||])]
    (->> input
         (map split-input-line)
         (filter validity-function)
         (map #(% 0))
         (reduce +)))
  )




