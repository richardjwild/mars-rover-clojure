(ns mars-rover-clojure.mars-rover)

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (assoc rover :heading ({:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST} current-heading))))

(defn mars-rover-driver [commands]
  nil)