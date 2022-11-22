;; ## Day 3: Binary Diagnostic

;; The submarine has been making some odd creaking noises, so you ask it to produce a diagnostic report just in case.

;; The diagnostic report (your puzzle input) consists of a list of binary numbers which, when decoded properly, can tell you many useful things about the conditions of the submarine. The first parameter to check is the power consumption.

;; You need to use the binary numbers in the diagnostic report to generate two new binary numbers (called the gamma rate and the epsilon rate). The power consumption can then be found by multiplying the gamma rate by the epsilon rate.

;; Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position of all numbers in the diagnostic report. For example, given the following diagnostic report:

;; ```
;; 00100
;; 11110
;; 10110
;; 10111
;; 10101
;; 01111
;; 00111
;; 11100
;; 10000
;; 11001
;; 00010
;; 01010
;; ```

;; Considering only the first bit of each number, there are five 0 bits and seven 1 bits. Since the most common bit is 1, the first bit of the gamma rate is 1.

;; The most common second bit of the numbers in the diagnostic report is 0, so the second bit of the gamma rate is 0.

;; The most common value of the third, fourth, and fifth bits are 1, 1, and 0, respectively, and so the final three bits of the gamma rate are 110.

;; So, the gamma rate is the binary number 10110, or 22 in decimal.

;; The epsilon rate is calculated in a similar way; rather than use the most common bit, the least common bit from each position is used. So, the epsilon rate is 01001, or 9 in decimal. Multiplying the gamma rate (22) by the epsilon rate (9) produces the power consumption, 198.

;; Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate, then multiply them together. What is the power consumption of the submarine? (Be sure to represent your answer in decimal, not binary.)

(ns day3-part1
  (:require [clojure.string :as str]))

(def file-input (vec (str/split (slurp "./advent/data/3.txt") #"\n")))

(count file-input)

(defn match-index
  "if c is 0 increment old value, otherwise return old value"
  [diag i c]
  (let [val (get-in diag [i] 0)]
    (if (= c \0)
      (inc val)
      val)))

(defn parse-binary-number [diagnostics number]
  (reduce
   (fn [diag, [i c]]
     (assoc diag i (match-index diag i c)))
   diagnostics
   (map-indexed list number)))

(def zeroes (reduce parse-binary-number
                    {}
                    file-input))

(defn find-ones []
  (into {} (for [[k v] zeroes]
             [k (- 1000 v)])))

(def ones (find-ones))

(println zeroes)

(println ones)

(def gamma-rate (merge-with (fn [left right] (if (> left right) "0" "1")) zeroes ones))

(def gamma-binary (apply str (vals (into (sorted-map) gamma-rate))))

(def gamma (Long/parseLong gamma-binary 2))

(def epsilon-rate (merge-with (fn [left right] (if (> left right) "1" "0")) zeroes ones))

(def epsilon-binary (apply str (vals (into (sorted-map) epsilon-rate))))



(def epsilon (Long/parseLong epsilon-binary 2))


;; Alternate ways

zeroes

(def freq (into (sorted-map)
      (for [i (range 12)]
        [i (frequencies (map #(nth % i) file-input))])))


(defn get-binary [freq a b]
  (->> freq
       (map (fn [[_ digit]] (let [zero-count (digit \0)] (if (> zero-count 500) a b)) ))
       (str/join)
       (#(Long/parseLong % 2))))

(count (filter #(= % \0) (map first file-input)))

(def gamma-new (get-binary freq \0 \1))
(def epsilon-new (get-binary freq \1 \0))

(* gamma-new epsilon-new)
(* epsilon-new gamma-new)

(list gamma epsilon)

;; (def gamma 1776)
;; (def epsilon 2319)

(Long/toBinaryString gamma)
(Long/toBinaryString epsilon)

(Long/toBinaryString gamma-new)
(Long/toBinaryString epsilon-new)


(* epsilon gamma)

(* gamma epsilon)

(type (* gamma epsilon))

(long (* gamma epsilon))

(* 1776 2319)

(* 2319 1776)

(type gamma)
(type gamma-new)
(type epsilon)
(type epsilon-new)


