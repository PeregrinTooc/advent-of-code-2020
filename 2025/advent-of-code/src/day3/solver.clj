(ns day3.solver
  (:require [clojure.string :as str]))

(defn max-subsequence [k s]
  "Return the lexicographically / numerically largest subsequence of length k
   from string s (preserving order). Returns a string.
   If k <= 0 returns \"\". If k >= (count s) returns s."
  (let [chars (seq s)
        n (count chars)]
    (cond
      (<= k 0) ""
      (>= k n) (apply str chars)
      :else
      (loop [k k
             chars chars
             acc []]
        (if (zero? k)
          (apply str acc)
          (let [needed (dec k)
                limit (- (count chars) needed)
                prefix (take limit chars)
                ;; find index + value of max element in prefix safely
                [max-pos max-ch]
                (reduce (fn [[mi mv] [i v]]
                          (if (or (nil? mv)
                                  (> (int v) (int mv)))
                            [i v]
                            [mi mv]))
                        [nil nil]
                        (map-indexed vector prefix))]
            (recur (dec k)
                   (drop (inc max-pos) chars)
                   (conj acc max-ch))))))))



(defn determine-highest-joltage
  ([length batterie-bank]
   (Long/parseLong (str/join (max-subsequence length batterie-bank)))
   )
  )

(defn solve1 [input]
  (reduce + (map (partial determine-highest-joltage 2) input))
  )


(defn solve2 [input]
  (reduce + (map (partial determine-highest-joltage 12) input))
  )




