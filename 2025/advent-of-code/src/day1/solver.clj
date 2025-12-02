(ns day1.solver)

(def op-map
  {\L -
   \R +})

(defn parse-command [operation]
  (let [operator (op-map (first operation))
        argument (Integer/parseInt (subs operation 1))]
    [operator argument]
    )
  )

(defn map-input-to-functions [input]
  (map parse-command input)
  )

(defn map-input-to-next-result [input]
  (let [functions (map-input-to-functions input)]
    (map #(mod % 100) (reductions (fn [acc [op arg]] (op acc arg)) 50 functions))
    )
  )

(defn solve1 [input]
  (let [numbers (map-input-to-next-result input)]
    (count (filter #(= % 0) numbers))
    )
  )

(defn count-zeros [operator argument start positions]
  (if (zero? argument)
    (count (filter zero? positions))
    (recur operator
           (dec argument)
           (operator start 1)
           (cons (mod (operator start 1) 100) positions))))


(defn apply-function-and-count-zeros [[operator argument] start]
  [(operator start argument) (count-zeros operator argument start [])]
  )

(defn apply-input-and-count-zeroes [start operations acc]
  (let [[operation & tail] operations
        [next zeroes] (apply-function-and-count-zeros (parse-command operation) start)
        ]
    (if (empty? tail)
      (+ acc zeroes)
      (recur next tail (+ acc zeroes))
      )
    )
  )


(defn solve2 [input]
  (apply-input-and-count-zeroes 50 input 0)
  )




