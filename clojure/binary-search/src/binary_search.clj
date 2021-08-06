(ns binary-search)

(defn search-for [value vector]
  (loop [left 0
         right (- (count vector) 1)]
    (let [middle (int (/ (+ right left) 2))]
      (cond
        (> left right) (throw (Exception. "not found"))
        (< (nth vector middle) value) (recur (inc middle) right)
        (> (nth vector middle) value) (recur left (dec middle))
        :else middle))))

(defn middle [vector]
  (int (/ (count vector) 2)))
