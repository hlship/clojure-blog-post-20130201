(ns com.howardlewisship.map-utils
  (import [java.util Collection])
  (use clojure.test)
  (require [clojure.string :as s]))

(defn sorted-key-list-1
  [map1 map2]
  (let [all-keys (set (concat (keys map1) (keys map2)))]
    (if (empty? all-keys)
      "<none>"
      (let [key-names (map str all-keys)
            sorted-names (sort key-names)]
        (s/join ", " sorted-names)))))

(defn sorted-key-list-2
  [map1 map2]
  (let [all-keys (set (concat (keys map1) (keys map2)))
        key-names (map str all-keys)
        sorted-names (sort key-names)]
    (if (empty? sorted-names)
      "<none>"
      (s/join ", " sorted-names))))

(defn sorted-key-list-3
  [map1 map2]
  (let [sorted-names
        (->>
          (set (concat (keys map1) (keys map2)))
          (map str)
          sort)]
    (if (empty? sorted-names)
      "<none>"
      (s/join ", " sorted-names))))

(defn sorted-key-list-4
  [map1 map2]
  (let [sorted-names
        (->>
          (keys map1)
          (concat (keys map2))
          set
          (map str)
          sort)]
    (if (empty? sorted-names)
      "<none>"
      (s/join ", " sorted-names))))

(defn sorted-key-list-5
  [& maps]
  (let [sorted-names
        (->>
          (mapcat keys maps)
          set
          (map str)
          sort)]
    (if (empty? sorted-names)
      "<none>"
      (s/join ", " sorted-names))))


(defn replace-empty
  [replacement coll]
  (if (empty? coll)
    replacement
    coll))

(defn sorted-key-list-6
  [& maps]
  (->>
    (mapcat keys maps)
    set
    (map str)
    sort
    (replace-empty ["<none>"])
    (s/join ", ")))

(deftest sorted-key-list
  (doseq [f [sorted-key-list-1 sorted-key-list-2 sorted-key-list-3 sorted-key-list-4 sorted-key-list-5
             sorted-key-list-6]]
    (are [map1 map2 result]
      (= (f map1 map2) result)

      {} {} "<none>"

      {:fred true} {:barney true, :wilma true} ":barney, :fred, :wilma"

      {:fred true} {} ":fred"

      {:fred true} {:fred true, :barney true} ":barney, :fred"

      {200 true, [1, 2] true, Collection true} {} "200, [1 2], interface java.util.Collection")))

(run-tests)