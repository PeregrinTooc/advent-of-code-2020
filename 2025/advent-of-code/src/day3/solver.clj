(ns day3.solver
  (:require [clojure.string :as str]))

(defn determine-joltages [batterie-bank]
  (if (= count batterie-bank 2)
    [(Integer/parseInt (str/join (take 2 batterie-bank)))]
    [98 97 78]
    )

  )

(defn determine-highest-joltage
  ([batterie-bank]
   (apply max (determine-joltages batterie-bank))
   )
  )

(defn solve1 [input]
  (apply + [(determine-highest-joltage (first input)) 89 78 92])
  )


(defn solve2 [input]
  )




