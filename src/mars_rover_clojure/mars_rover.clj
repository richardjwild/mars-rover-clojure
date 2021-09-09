(ns mars-rover-clojure.mars-rover)

(def rotations-left {:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST, :EAST :NORTH})

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (assoc rover :heading (rotations-left current-heading))))

(defn mars-rover-driver [commands]
  nil)