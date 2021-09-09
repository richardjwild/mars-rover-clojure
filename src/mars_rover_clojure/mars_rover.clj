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

(defn- move [rover current-heading]
  (let [translation (translations current-heading)]
    (assoc rover :x (+ (translation :x) (rover :x))
                 :y (+ (translation :y) (rover :y)))))

(defn execute [rover command]
  (let [current-heading (rover :heading)]
    (if (rotation? command)
      (rotate rover command current-heading)
      (move rover current-heading))))

(defn- prettify [rover]
  (format "%d:%d:%s" (rover :x) (rover :y) ({:NORTH \N, :SOUTH \S, :EAST \E, :WEST \W} (rover :heading))))

(defn mars-rover-driver [commands]
  (prettify (reduce execute initial-rover-state commands)))