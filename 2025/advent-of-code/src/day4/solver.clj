(ns day4.solver)

(defn transform-to-grid [input]
  (vec (map vec input))
  )

(defn map-grid [f grid]
  (vec (map (fn [row]
              (vec (map f row)))
            grid)))

(defn neighbours [grid row column]
  (let [adjacencies [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]]
        value (map (fn [[x y]] (get-in grid [(+ row x) (+ column y)])) adjacencies)]
    (filter #(= \@ %) value)
    )
  )

(defn identify-accessible-rolls
  ([grid]
   (map-indexed (partial identify-accessible-rolls grid) grid)
   )
  ([grid row-index row]
   (map-indexed (partial identify-accessible-rolls grid row-index) row)
   )
  ([grid row-index column-index element]
   (if (= \@ element)
     (count (neighbours grid row-index column-index))
     element
     )
   )

  )


(defn solve1 [input]
  (let [grid (transform-to-grid input)
        accessible-rolls (flatten (identify-accessible-rolls grid))]
    (count (filter #(< % 4) (filter number? accessible-rolls)))
    )
  )

(defn remove-rolls-repeatedly
  [acc grid]
  (let [accessible-rolls (identify-accessible-rolls grid)
        [removed next-grid] [(count (filter #(< % 4) (filter number? (flatten accessible-rolls))))
                             (map-grid #(cond (= \. %) % (< % 4) \. :else \@) accessible-rolls)]]
    (if (= 0 removed)
      acc
      (recur (+ acc removed) next-grid))
    )
  )

(defn solve2 [input]
  (let [grid (transform-to-grid input)]
    (remove-rolls-repeatedly 0 grid)
    )
  )




