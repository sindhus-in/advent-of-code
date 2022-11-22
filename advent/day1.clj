;; Solving Day 1: https://adventofcode.com/2021/day/1

;; Part A
(ns day1
  (:require [clojure.string :as str]))

(def file-input (str/split (slurp "./advent/data/1.txt") #"\n"))

(def file-nums (for [num file-input]
                 (Integer/parseInt num)))

(def file-vec (vec file-nums))

(type file-vec)

(println file-nums)

(count file-vec)

(file-vec 6)

(def results
  (for [n (range 1 (count file-vec))]
    (if (< (file-vec (- n 1))
           (file-vec n)) "increased" "")))

results

(frequencies results)

;; Part B

"```
199  A      
200  A B    
208  A B C  
210    B C D
200  E   C D
207  E F   D
240  E F G  
269    F G H
260      G H
263        H
```"

(def set-of-3 (partition 3 1 file-vec))

(def reduced-nums
  (for [n set-of-3]
    (reduce + n)))

(def reduced-nums-vec (vec reduced-nums))

(def reduced-results (for [n (range 1 (count reduced-nums))]
                       (if (< (reduced-nums-vec (- n 1))
                              (reduced-nums-vec n)) "increased")))

(frequencies reduced-results)