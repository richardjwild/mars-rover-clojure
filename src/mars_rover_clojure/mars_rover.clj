(ns mars-rover-clojure.mars-rover)

(def rotations
  {\L {:NORTH :WEST, :WEST :SOUTH, :SOUTH :EAST, :EAST :NORTH}
   \R {:NORTH :EAST, :WEST :NORTH, :SOUTH :WEST, :EAST :SOUTH}})

(def translations
  {:NORTH {:x 0, :y 1}
   :WEST  {:x -1, :y 0}
   :SOUTH {:x 0, :y -1}
   :EAST  {:x 1, :y 0}})

(def grid {:width 10, :height 10})
(def obstacles #{})
(def initial-rover-state {:x 0 :y 0 :heading :NORTH})

(defn- rotation? [command]
  (or (= command \L)
      (= command \R)))

(defn- rotate [rover command current-heading]
  (assoc rover :heading ((rotations command) current-heading)))

(defn- add-and-wrap [increment value size]
  (let [sum (+ value increment)]
    (if (= size sum)
      (- sum size)
      (if (neg? sum) (+ sum size) sum))))

(defn- move [rover current-heading]
  (let [translation (translations current-heading)]
    (let [new-x (add-and-wrap (translation :x) (rover :x) (grid :width))
          new-y (add-and-wrap (translation :y) (rover :y) (grid :height))]
     (assoc rover :x new-x :y new-y))))

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (if (rotation? command)
      (rotate rover command current-heading)
      (move rover current-heading))))

(defn- prettify [rover]
  (format "%d:%d:%s" (rover :x) (rover :y) ({:NORTH \N, :SOUTH \S, :EAST \E, :WEST \W} (rover :heading))))

(defn mars-rover-driver [commands]
  (prettify (reduce execute initial-rover-state commands)))