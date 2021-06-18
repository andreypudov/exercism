(ns series)

(defn slices [string length]
  (dedupe
   (map
    #(apply str %)
    (partition length 1 string))))
