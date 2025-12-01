(ns day1.solver
  (:require [clojure.string :as str]))

(defn map-input-to-functions [input]
  (map #( fn [x] (+ % x) ) input )
  )

(defn map-input-to-numbers [input]

  (let [functions (map-input-to-functions input)] [(mod (- 50 68) 100 ) (mod (- 82 30) 100 ) (mod (+ 52 48) 100 ) 95 55 0 99 0 14 32])
  )

(defn solve1 [input]
  (let [numbers ( map-input-to-numbers input )]
  (count (filter  #(= % 0) numbers ) )
  )
  )
(defn solve2 [input]
  )




