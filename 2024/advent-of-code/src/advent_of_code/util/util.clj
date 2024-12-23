(ns advent-of-code.util.util
  (:require [clojure.string :as str]))

(defn- split-on-newline-cross-platform [text]
  (str/split text #"\r?\n"))

(defn- read-file-as-string [file-path]
  (slurp file-path))



(defn transform-file-to-list-of-lines [file-path]
  (split-on-newline-cross-platform (read-file-as-string file-path))
  )

(defn- line-to-cells [line-index line]
  (map-indexed (fn [column-index cell-value]
                 [[line-index column-index] cell-value])
               line))

(defn- add-line-to-matrix [matrix [line-index line]]
  (into matrix (line-to-cells line-index line)))

(defn transform-to-cells [lines]
  (let [indexed-lines (map-indexed vector lines)]
    (reduce add-line-to-matrix {} indexed-lines)))