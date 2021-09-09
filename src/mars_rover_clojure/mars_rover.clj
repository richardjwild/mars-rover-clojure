(ns mars-rover-clojure.mars-rover)

(def rotations
  {'L' {:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST, :EAST :NORTH}
   'R' {:NORTH :EAST, :WEST :NORTH, :SOUTH :WEST, :EAST :SOUTH}})

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn- rotation? [command]
  (or (= command 'L')
      (= command 'R')))

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (if (rotation? command)
      (assoc rover :heading ((rotations command) current-heading))
      (if (= current-heading :NORTH)
        (assoc rover :y (+ 1 (rover :y)))
        (assoc rover :x (- 1 (rover :x)))))))

(defn mars-rover-driver [commands]
  nil)