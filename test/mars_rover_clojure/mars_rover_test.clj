(ns mars-rover-clojure.mars-rover-test
  (:require [midje.sweet :refer :all]
            [mars-rover-clojure.mars-rover :refer :all]))

(facts "the mars rover is acceptable when"
       (fact "it drives around the grid"
             (mars-rover-driver "MMRMMLM") => "2:3:N")
       (fact "it wraps when it moves off the edge of the grid"
             (mars-rover-driver "MMMMMMMMMM") => "0:0:N")
       (fact "it stops when it meets an obstacle"
             (with-redefs [obstacles #{[0 3]}]
               (mars-rover-driver "MMMM")) => "O:0:2:N"))

(facts "about a mars rover facing north"
       (let [rover {:x 1 :y 1 :heading :north}]
         (fact "after turning left it faces west"
               (execute rover \L) => {:x 1 :y 1 :heading :west})
         (fact "after turning right it faces east"
               (execute rover \R) => {:x 1 :y 1 :heading :east})
         (fact "after moving forward its y coordinate is incremented and its heading is unchanged"
               (execute rover \M) => {:x 1 :y 2 :heading :north}))
       (with-redefs [grid {:width 1 :height 2}]
         (fact "after moving off the north of the grid it wraps around to the south"
               (execute {:x 0 :y 1 :heading :north} \M) => {:x 0 :y 0 :heading :north})))

(facts "about a mars rover facing west"
       (let [rover {:x 1 :y 1 :heading :west}]
         (fact "after turning left it faces south"
               (execute rover \L) => {:x 1 :y 1 :heading :south})
         (fact "after turning right it faces north"
               (execute rover \R) => {:x 1 :y 1 :heading :north})
         (fact "after moving forward its x coordinate is decremented and its heading is unchanged"
               (execute rover \M) => {:x 0 :y 1 :heading :west}))
       (with-redefs [grid {:width 2 :height 1}]
         (fact "after moving off the west of the grid it wraps around to the east"
               (execute {:x 0 :y 0 :heading :west} \M) => {:x 1 :y 0 :heading :west})))

(facts "about a mars rover facing south"
       (let [rover {:x 1 :y 1 :heading :south}]
         (fact "after turning left it faces east"
               (execute rover \L) => {:x 1 :y 1 :heading :east})
         (fact "after turning right it faces west"
               (execute rover \R) => {:x 1 :y 1 :heading :west})
         (fact "after moving forward its y coordinate is decremented and its heading is unchanged"
               (execute rover \M) => {:x 1 :y 0 :heading :south}))
       (with-redefs [grid {:width 1 :height 2}]
         (fact "after moving off the south of the grid it wraps around to the north"
               (execute {:x 0 :y 0 :heading :south} \M) => {:x 0 :y 1 :heading :south})))

(facts "about a mars rover facing east"
       (let [rover {:x 1 :y 1 :heading :east}]
         (fact "after turning left it faces north"
               (execute rover \L) => {:x 1 :y 1 :heading :north})
         (fact "after turning right it faces south"
               (execute rover \R) => {:x 1 :y 1 :heading :south})
         (fact "after moving forward its x coordinate is incremented and its heading is unchanged"
               (execute rover \M) => {:x 2 :y 1 :heading :east}))
       (with-redefs [grid {:width 2 :height 1}]
         (fact "after moving off the east of the grid it wraps around to the west"
               (execute {:x 1 :y 0 :heading :east} \M) => {:x 0 :y 0 :heading :east})))
