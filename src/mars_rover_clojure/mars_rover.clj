(ns mars-rover-clojure.mars-rover)

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn execute [rover command]
  (assoc rover :heading :WEST))

(defn mars-rover-driver [commands]
  nil)