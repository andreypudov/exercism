(ns collatz-conjecture)

(defn- collatz-step [num]
  (if (even? num)
    (/ num 2)
    (+ (* num 3) 1)))

(defn- collatz-iter [num]
  (->> num
       (iterate collatz-step)
       (take-while #(> % 1))
       count))

(defn collatz [num]
  (assert (pos? num))
  (collatz-iter num))
