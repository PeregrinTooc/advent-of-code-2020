(ns advent-of-code.core
    )
(defn split-on-newline-cross-platform [text]
      (str/split text #"\r?\n"))

(defn read-file-as-string [file-path]
      (slurp file-path))



(defn transform-file-to-list-of-lines [file-path]
      (split-on-newline-cross-platform (read-file-as-string file-path))
      )