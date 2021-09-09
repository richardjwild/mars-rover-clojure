(ns mars-rover-clojure.mars-rover)

(def rotations
  {'L' {:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST, :EAST :NORTH}
   'R' {:NORTH :EAST}})

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (assoc rover :heading ((rotations command) current-heading))))

(defn mars-rover-driver [commands]
  nil)