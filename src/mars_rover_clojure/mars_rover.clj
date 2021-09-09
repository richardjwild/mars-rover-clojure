(ns mars-rover-clojure.mars-rover)

(def rotations
  {'L' {:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST, :EAST :NORTH}
   'R' {:NORTH :EAST, :WEST :NORTH, :SOUTH :WEST, :EAST :SOUTH}})

(def translations
  {:NORTH {:x 0, :y 1}
   :WEST {:x -1, :y 0}
   :SOUTH {:x 0, :y -1}
   :EAST {:x 1, :y 0}})

(def grid {:width 10, :height 10})
(def obstacles #{})

(defn- rotation? [command]
  (or (= command 'L')
      (= command 'R')))

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (if (rotation? command)
      (assoc rover :heading ((rotations command) current-heading))
      (let [translation (translations current-heading)]
        (assoc rover :x (+ (translation :x) (rover :x))
                     :y (+ (translation :y) (rover :y)))))))

(defn mars-rover-driver [commands]
  nil)