(ns advent-of-code.day6.solver
  (:require [clojure.string :as str]))

(defn step [current-situation]
  (mapv #(str/replace #"\^" "X" %) current-situation)
  )


(defn solve1 [input]

  )

(defn solve2 [input]

  )





