(ns day1.solver
  (:require [clojure.string :as str]))

(def op-map
  {\L -
   \R +})

(defn parse-command [s]
  (let [operator (op-map (first s))
        argument (Integer/parseInt (subs s 1))]
    [operator argument]
    )
  )

(defn map-input-to-functions [input]
  (map parse-command input)
  )

(defn map-input-to-numbers [input]
  (let [functions (map-input-to-functions input)]
    (map #(mod % 100) (reductions (fn [acc [op arg]] (op acc arg)) 50 functions))
    )
  )

(defn solve1 [input]
  (let [numbers ( map-input-to-numbers input )]
  (count (filter  #(= % 0) numbers ) )
  )
  )
(defn solve2 [input]
 6
  )




