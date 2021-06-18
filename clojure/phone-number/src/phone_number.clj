(ns phone-number)

(defn- digit? [c]
  (Character/isDigit c))

(defn- invalid-num []
  (repeat 11 \0))

(defn- parse-string [num-string]
  (let [num-digits (filter digit? num-string)
        length (count num-digits)]
    (cond
      (= length 10) (conj num-digits \1)
      (= length 11) num-digits
      :else (invalid-num))))

(defn- make-number [num-digits]
  (let [country (first num-digits)
        area (seq (take 3 (drop 1 num-digits)))
        number (seq (drop 4 num-digits))]
    {:country country
     :area area
     :exchange number}))

(defn- validate-number [number]
  (let [country? (= \1 (get number :country))
        area? (and
               (not= \0 (first (get number :area)))
               (not= \1 (first (get number :area))))
        exchange? (and
               (not= \0 (first (get number :exchange)))
               (not= \1 (first (get number :exchange))))]
    (cond
      (and country? area? exchange?) number
      :else (make-number (invalid-num)))))

(defn- str->number [num-string]
  (-> num-string
      (parse-string)
      (make-number)
      (validate-number)))

(defn- number->str [number]
  (->> number
       (vals)
       (flatten)
       (drop 1)
       (apply str)))

(defn number [num-string]
  (-> num-string
      (str->number)
      (number->str)))

(defn area-code [num-string]
  (->> num-string
       (str->number)
       (#(get % :area))
       (apply str)))

(defn pretty-print [num-string]
  (let [number (str->number num-string)
        area (get number :area)
        exchange (get number :exchange)]
    (format "(%s) %s-%s"
            (apply str area)
            (apply str (take 3 exchange))
            (apply str (drop 3 exchange)))))
