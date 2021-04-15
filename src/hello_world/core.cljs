(ns ^:figwheel-hooks hello-world.core
  (:require [com.stuartsierra.component :as component]
            [hello-world.components.dummy :as dummy]
            [hello-world.components.resizer :as resizer]
            [hello-world.components.svg :as svg]
            [hello-world.components.form :as form]
            #_ [cljsjs.svgjs]))

(enable-console-print!)

(defn system []
  (component/system-map :dummy (dummy/map->DUMMY {})
                        :svg (svg/map->SVG {})
                        :form (form/map->FORM {})
                        :resizer (component/using (resizer/map->RESIZER {})
                                                  [:svg])))

(defonce S (atom (system)))

(defn ^:before-load teardown []
  (swap! S component/stop))

(defn ^:after-load startup []
  (swap! S component/start))
