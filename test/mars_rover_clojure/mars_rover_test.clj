(ns mars-rover-clojure.mars-rover-test
  (:require [midje.sweet :refer :all]
            [mars-rover-clojure.mars-rover :refer :all]))

(facts "the mars rover is acceptable when"
       ;(fact "it drives around the grid"
       ;      (mars-rover-driver "MMRMMLM") => "2:3:N")
       ;(fact "it wraps when it moves off the edge of the grid"
       ;      (mars-rover-driver "MMMMMMMMMM") => "0:0:N")
       ;(fact "it stops when it meets an obstacle"
       ;      (with-redefs [obstacles #{0 3}]
       ;        (mars-rover-driver "MMMM")) => "O:0:2:N")
       )

(facts "about a mars rover facing north"
       (let [rover {:x 1 :y 1 :heading :NORTH}]
         (fact "after turning left it faces west"
               (execute rover 'L') => {:x 1 :y 1 :heading :WEST})
         (fact "after turning right it faces east"
               (execute rover 'R') => {:x 1 :y 1 :heading :EAST})
         (fact "after moving forward its y coordinate is incremented and its heading is unchanged"
               (execute rover 'M') => {:x 1 :y 2 :heading :NORTH})))

(facts "about a mars rover facing west"
       (let [rover {:x 1 :y 1 :heading :WEST}]
         (fact "after turning left it faces south"
               (execute rover 'L') => {:x 1 :y 1 :heading :SOUTH})
         (fact "after turning right it faces north"
               (execute rover 'R') => {:x 1 :y 1 :heading :NORTH})
         (fact "after moving forward its x coordinate is decremented and its heading is unchanged"
               (execute rover 'M') => {:x 0 :y 1 :heading :WEST})))

(facts "about a mars rover facing south"
       (let [rover {:x 1 :y 1 :heading :SOUTH}]
         (fact "after turning left it faces east"
               (execute rover 'L') => {:x 1 :y 1 :heading :EAST})
         (fact "after turning right it faces west"
               (execute rover 'R') => {:x 1 :y 1 :heading :WEST})
         (fact "after moving forward its y coordinate is decremented and its heading is unchanged"
               (execute rover 'M') => {:x 1 :y 0 :heading :SOUTH})))

(facts "about a mars rover facing east"
       (let [rover {:x 1 :y 1 :heading :EAST}]
         (fact "after turning left it faces north"
               (execute rover 'L') => {:x 1 :y 1 :heading :NORTH})
         (fact "after turning right it faces south"
               (execute rover 'R') => {:x 1 :y 1 :heading :SOUTH})))
