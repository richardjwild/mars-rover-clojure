(ns mars-rover-clojure.mars-rover)

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn execute [rover command]
  (if (= (rover :heading) :NORTH)
    (assoc rover :heading :WEST)
    (assoc rover :heading :SOUTH)))

(defn mars-rover-driver [commands]
  nil)