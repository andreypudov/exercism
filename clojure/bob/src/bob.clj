(ns bob
  (:require [clojure.string :as string]))

(defn filter-string [s]
  (string/trim s))

(defn not-letter? [s]
  (some? (re-matches #"[\W\d]+" s)))

(defn question? [s]
  (= \? (last s)))

(defn yelling? [s]
  (if-not (not-letter? s)
    (some? (re-matches #"[A-Z\W\d]+" s))))

(defn yelling-question? [s]
  (and (yelling? s)
       (question? s)))

(defn silence? [s]
  (string/blank? s))

(defn response-for [s]
  (let [s (filter-string s)]
    (cond
      (yelling-question? s) "Calm down, I know what I'm doing!"
      (question? s) "Sure."
      (yelling? s) "Whoa, chill out!"
      (silence? s) "Fine. Be that way!"
      :something "Whatever.")))
