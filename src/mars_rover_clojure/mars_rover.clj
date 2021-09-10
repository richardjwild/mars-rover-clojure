(ns mars-rover-clojure.mars-rover)

(def direction
  {:north \N, :south \S, :east \E, :west \W})

(def rotations-for-command
  {\L {:north :west, :west :south, :south :east, :east :north}
   \R {:north :east, :west :north, :south :west, :east :south}})

(def translation-for-heading
  {:north {:x 0, :y 1}
   :west  {:x -1, :y 0}
   :south {:x 0, :y -1}
   :east  {:x 1, :y 0}})

(def initial-rover-state {:x 0, :y 0, :heading :north})
(def grid {:width 10, :height 10})
(def obstacles #{})

(defn- obstacle? [x y]
  (let [position [x y]]
    (some #(= %1 position) obstacles)))

(defn- wrap [sum size]
  (if (= size sum)
    (- sum size)
    (if (neg? sum) (+ sum size) sum)))

(defn- move [rover current-heading]
  (let [translation (translation-for-heading current-heading)]
    (let [new-x (wrap (+ (translation :x) (rover :x)) (grid :width))
          new-y (wrap (+ (translation :y) (rover :y)) (grid :height))]
      (if (obstacle? new-x new-y)
        (assoc rover :blocked true)
        (assoc rover :x new-x :y new-y)))))

(defn- rotating? [command]
  (or (= command \L)
      (= command \R)))

(defn- rotate [rover current-heading command]
  (let [rotation-for-heading (rotations-for-command command)
        new-heading (rotation-for-heading current-heading)]
    (assoc rover :heading new-heading)))

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (if (rotating? command)
      (rotate rover current-heading command)
      (move rover current-heading))))

(defn- to-string [rover]
  (format "%s%s"
          (if (rover :blocked) "O:" "")
          (format "%d:%d:%s" (rover :x) (rover :y) (direction (rover :heading)))))

(defn mars-rover-driver [commands]
  (to-string (reduce execute initial-rover-state commands)))