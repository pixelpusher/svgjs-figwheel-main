(ns net.cassiel.svg.grass
   (:require [goog.string :as gstring]
            [goog.string.format])
  )

(defn single-grass[container x h num-segs]
  (let [path (.path container)
        path-str (gstring/format "M%d,0 %d,%d z" x x h)
        text (.text container #(do
                                 (-> (.tspan % "We go")
                                     (.dy 50))
                                 (-> (.tspan % "up")
                                     (.fill "#F09")
                                     (.dy 40))))
        text2 (.text container #(do
                                  (-> (.tspan % "try try again!")
                                      (.dy -80))))
        textPath (.path text2 path-str)]
    (-> textPath
        (.animate 2000)
        (.ease "<>")
        (.plot(gstring/format "M%d,0 %d,%d z" x (+ x 100) h))
        (.loop true true))
    ))

(defn render [container size form-state]
  (single-grass container 0 100 4))

(defn tick [container ts form-state]
  (js/console.log ts))

