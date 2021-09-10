(ns mars-rover-clojure.mars-rover)

(def heading-code
  {:north \N, :south \S, :east \E, :west \W})

(def rotations-for-direction
  {\L {:north :west, :west :south, :south :east, :east :north}
   \R {:north :east, :west :north, :south :west, :east :south}})

(def translation-for-heading
  {:north {:x 0, :y 1}
   :west  {:x -1, :y 0}
   :south {:x 0, :y -1}
   :east  {:x 1, :y 0}})

(def initial-rover-state {:x 0, :y 0, :heading :north})
(def grid {:width 10, :height 10})                          ; may be redefined by the tests
(def obstacles #{})                                         ; may be redefined by the tests

(defn- obstacle? [x y]
  (let [position [x y]]
    (some #(= %1 position) obstacles)))                     ; true if an obstacle equals [x y]

(defn- wrap [value size]
  (if (and (>= value 0) (< value size))
    value
    (if (neg? value) (+ value size) (- value size))))

(defn- move [rover]
  (let [delta (translation-for-heading (rover :heading))]
    (let [new-x (wrap (+ (delta :x) (rover :x)) (grid :width))
          new-y (wrap (+ (delta :y) (rover :y)) (grid :height))]
      (if (obstacle? new-x new-y)
        (assoc rover :blocked true)
        (assoc rover :x new-x :y new-y)))))

(defn- rotating? [command]
  (contains? rotations-for-direction command))

(defn- rotate [rover direction]
  (let [rotation-for-heading (rotations-for-direction direction)
        new-heading (rotation-for-heading (rover :heading))]
    (assoc rover :heading new-heading)))

(defn execute [rover command]
  (if (rotating? command)
    (rotate rover command)
    (move rover)))

(defn- to-string [rover]
  (format "%s%s"
          (if (rover :blocked) "O:" "")
          (format "%d:%d:%s" (rover :x) (rover :y) (heading-code (rover :heading)))))

(defn mars-rover-driver [commands]
  (to-string (reduce execute initial-rover-state commands)))