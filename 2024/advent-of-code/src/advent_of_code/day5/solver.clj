(ns advent-of-code.day5.solver
  (:require [clojure.string :as str]))


(defn create-rules-and-pages [input]
  (if (< (count input) 2)
    []
    (let [[rules limit-and-pages] (split-with #(not= % "") input)
          pages (rest limit-and-pages)]
      [rules pages]))
  )

(defn middle-of [v]
  (v (/ (dec (count v)) 2))
  )

(defn matches-rule [pages-string rule-string]
  (let [pages (map read-string (str/split pages-string #","))
        [comes-first comes-after] (mapv read-string (str/split rule-string #"\|"))
        matched-pages (filterv #(or (= comes-first %) (= comes-after %)) pages)
        ]
    (or (< (count matched-pages) 2) (= [comes-first comes-after] matched-pages))
    )
  )

(defn matches-all-rules [rules pages]
  (every? (partial matches-rule pages) rules)
  )

(defn sort-with [rule-strings page1 page2]
  (let [rules (mapv #(mapv read-string (str/split % #"\|")) rule-strings)
        [comes-first comes-after] (rules 0)]
    (cond (= page1 comes-first) -1 :else 1)
    )
  )

(defn solve1 [input]
  (let [[rules pages] (create-rules-and-pages input)
        rule-abiding-pages (filter (partial matches-all-rules rules) pages)]
    (reduce + (map (comp middle-of #(mapv read-string (str/split % #","))) rule-abiding-pages))
    )
  )

(defn solve2 [input]
  (let [[rules pages] (create-rules-and-pages input)
        naughty-pages (filter (complement (partial matches-all-rules rules)) pages)]
    (apply + (map #(middle-of (sort (partial sort-with rules) (mapv read-string (str/split % #",")))) naughty-pages))
    )
  )





