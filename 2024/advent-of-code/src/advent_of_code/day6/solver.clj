(ns advent-of-code.day6.solver
  (:require [advent-of-code.util.util :refer :all]))




(defn- turn [direction]
  (let [next-directions {\^ \>, \> \v, \v \<, \< \^}]
    (next-directions direction)
    ))
(defn- calculate-next-position-no-cache [line column direction]
  (case direction
    \^ [(dec line) column]
    \v [(inc line) column]
    \> [line (inc column)]
    \< [line (dec column)]
    )
  )

(def calculate-next-position (memoize calculate-next-position-no-cache))

(defn- create-next-map [marked-position [line column] direction]
  (let [next-position (marked-position (calculate-next-position line column direction))]
    (if (= \# next-position)
      (assoc marked-position [line column] (turn direction))
      (assoc marked-position (calculate-next-position line column direction) direction)
      )
    )
  )
(defn- end-of-patrol? [marked-position line column direction]
  (let [next-position (calculate-next-position line column direction)]
    (nil? (marked-position next-position)))
  )

(defn- move-guard [current-situation [line column]]
  (let [direction (current-situation [line column])
        marked-position (assoc current-situation [line column] \X)
        ]
    (if (end-of-patrol? marked-position line column direction)
      marked-position
      (create-next-map marked-position [line column] direction)
      )
    )
  )


(defn step [current-situation]
  (let [position-of-guard (some (fn [[k v]] (when (contains? #{\^ \> \< \v} v) k)) current-situation)]
    (if (nil? position-of-guard)
      current-situation
      (move-guard current-situation position-of-guard)
      )
    )
  )

(defn- repeat-until-stable [f lst]
  (loop [lst lst]
    (let [first-item (first lst)
          second-item (second lst)]
      (if (= first-item second-item)
        lst
        (recur (cons (f first-item) lst))))))


(defn solve1 [input]
  (let [lines-as-cells (transform-to-cells input)
        all-steps (repeat-until-stable step (list lines-as-cells))]
    (count (filter #(= \X %) (vals (first all-steps))))
    )
  )

(defn solve2 [input]

  )





