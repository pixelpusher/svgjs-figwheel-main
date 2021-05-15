(ns net.cassiel.svg.grass
   (:require [goog.string :as gstring]
            [goog.string.format])
  )

(defn single-grass [content container x h num-segs]
  (let [text1 (.text container #(do
                                  (-> (.tspan % "We go")
                                      (.dy 50))
                                  (-> (.tspan % "up")
                                      (.fill "#F09")
                                      (.dy 40))))]
    (-> text1
        (.path (gstring/format "M%d,%d %d,%d z" 0 h (+ x (.length text1)) h))
        (.animate 2000)
        (.ease "<>")
        (.plot (gstring/format "M%d,%d %d,%d z" 0 h h (+ x (.length text1))))
        (.loop true true))
    (-> text1
        (.root)
        (.mousemove (fn [event]
                      (js/console.log (.-clientY event)
                                      #_                         text1)
                      #_(.dx text1 (/ (.-offsetX event) 30))
                      #_(.x text1 (.-clientX event)))))

    (-> container
        (.text content)
        #_(.mousemove (fn [event]
                        (this-as this
                                 (js/console.log
                                  (.-clientX event))
                                 (.dx this
                                      (/
                                       (-
                                        (.-x this)
                                        (.-clientX event))
                                       10)))))
        (.fill "#F0A")
        (.path (gstring/format "M%d,%d %d,%d z" 0 h (+ x 300) h))
        #_(.animate 2000)
        #_(.ease "<>")
        #_(.plot (gstring/format "M%d,%d %d,%d z" h 0 h (+ x 300)))
        #_(.loop true true))))

(defn render [container size form-state]
  (single-grass "this is saying nothing" container 0 100 4))

(defn tick [container ts form-state]
  empty
  #_(js/console.log ts))

