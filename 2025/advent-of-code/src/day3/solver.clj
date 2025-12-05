(ns day3.solver
  (:require [clojure.string :as str]))

(defn determine-joltages [batterie-bank]
  (if (= (count batterie-bank) 2)
    [(Integer/parseInt (str/join (take 2 batterie-bank)))]
    (let [[head & tail] batterie-bank]
      (concat (map #(Integer/parseInt %) (map #(str/join [head %]) tail)) (determine-joltages tail))
      )
    )
  )


(defn determine-highest-joltage
  ([batterie-bank]
   (apply max (determine-joltages batterie-bank))
   )
  )

(defn solve1 [input]
  (reduce + (map determine-highest-joltage input))
  )


(defn solve2 [input]
  )




