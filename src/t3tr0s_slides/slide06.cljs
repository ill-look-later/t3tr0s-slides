(ns t3tr0s-slides.slide06
  (:require
    [om.core :as om :include-macros true]
    [om-tools.core :refer-macros [defcomponent]]
    [sablono.core :refer-macros [html]]
    [t3tr0s-slides.syntax-highlight :as sx]
    ))

(def dark-green "#143")
(def light-green "#175")
(def dark-purple "#449")
(def light-purple "#6ad")

(def pieces
  {:I [[-1  0] [ 0  0] [ 1  0] [ 2  0]]
   :L [[ 1 -1] [-1  0] [ 0  0] [ 1  0]]
   :J [[-1 -1] [-1  0] [ 0  0] [ 1  0]]
   :S [[ 0 -1] [ 1 -1] [-1  0] [ 0  0]]
   :Z [[-1 -1] [ 0 -1] [ 0  0] [ 1  0]]
   :O [[ 0 -1] [ 1 -1] [ 0  0] [ 1  0]]
   :T [[ 0 -1] [-1  0] [ 0  0] [ 1  0]]})

(defn rotate-coord [[x y]] [(- y) x])
(defn rotate-piece [piece] (mapv rotate-coord piece))
(defn move-left [[x y]] [(dec x) y])
(defn move-right [[x y]] [(inc x) y])
(defn move-down [[x y]] [x (inc y)])

(def g0 {:position [4 6] :piece (:J pieces)})
(def g1 (update-in g0 [:position] move-left))
(def g2 (update-in g1 [:position] move-left))
(def g3 (update-in g2 [:piece] rotate-piece))
(def g4 (update-in g3 [:position] move-down))
(def g5 (update-in g4 [:position] move-down))
(def g6 (update-in g5 [:piece] rotate-piece))
(def g7 (update-in g6 [:position] move-right))
(def g8 (update-in g7 [:position] move-right))
(def g9 (update-in g8 [:piece] rotate-piece))
(def g10 (update-in g9 [:position] move-down))
(def g11 (update-in g10 [:position] move-down))
(def g12 (update-in g11 [:position] move-down))
(def g13 (update-in g12 [:position] move-down))
(def g14 (update-in g13 [:position] move-down))
(def g15 (update-in g14 [:position] move-down))

(def states [g0 g1 g2 g3 g4 g5 g6 g7 g8 g9 g10 g11 g12 g13 g14 g15])

(def state-places
  {g0  0
   g1  1
   g2  2
   g3  3
   g4  4
   g5  5
   g6  6
   g7  7
   g8  8
   g9  9
   g10 10
   g11 11
   g12 12
   g13 13
   g14 14
   g15 15})

(defn rotate-coord [[x y]] [(- y) x])
(defn rotate-piece [piece] (mapv rotate-coord piece))

(defn piece-abs-coords
  [piece [cx cy]]
  (mapv (fn [[x y]] [(+ cx x) (+ cy y)]) piece))

(def app-state (atom {:piece nil
                      :position nil}))

(def rows 20)
(def cols 10)
(def empty-row (vec (repeat cols 0)))
(def empty-board (vec (repeat rows empty-row)))

(defn state-code
  [app rkey state code-str]
  [:span
   {:key rkey
    :class (if (= state app) "active-col-d9099" "")
    :onMouseEnter #(om/update! app state)}
   code-str])

(defcomponent code
  [app owner]
  (render
    [_]
    (html
      [:div.code-cb62a
       [:pre
        [:code#lang-clj
         {:ref "code"}
         "(" (sx/core "def") " game-state\n"
         "  (" (sx/core "atom") " {" (sx/kw ":board") " empty-board]\n"
         "         " (sx/kw ":piece") " (" (sx/kw ":J") " pieces)\n"
         "         " (sx/kw ":position") " [" (sx/lit "4") " " (sx/lit "6") "]}))\n"
         "\n\n"
         (sx/cmt "; TRY IT: mouse over the states.") "\n\n"
         (state-code app "g1"  g1  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-left)\n"))
         (state-code app "g2"  g2  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-left)\n"))
         (state-code app "g3"  g3  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":piece") "]    rotate-piece)\n"))
         (state-code app "g4"  g4  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g5"  g5  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g6"  g6  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":piece") "]    rotate-piece)\n"))
         (state-code app "g7"  g7  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-right)\n"))
         (state-code app "g8"  g8  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-right)\n"))
         (state-code app "g9"  g9  (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":piece") "]    rotate-piece)\n"))
         (state-code app "g10" g10 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g11" g11 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g12" g12 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g13" g13 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g14" g14 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         (state-code app "g15" g15 (list "(" (sx/core "swap!") " game-state " (sx/core "update-in") " [" (sx/kw ":position") "] move-down)\n"))
         ]]])))

(def cell-size (quot 600 rows))

(defn draw-cell!
  [ctx [x y] is-center]
  (set! (.. ctx -lineWidth) 2)
  (let [rx (* cell-size x)
        ry (* cell-size y)
        rs cell-size
        cx (* cell-size (+ x 0.5))
        cy (* cell-size (+ y 0.5))
        cr (/ cell-size 4)
        ]
    (.. ctx (fillRect rx ry rs rs))
    (.. ctx (strokeRect rx ry rs rs))
    (when is-center
      (.. ctx beginPath)
      (.. ctx (arc cx cy cr 0 (* 2 (.-PI js/Math))))
      (.. ctx fill)
      (.. ctx stroke)
      )
    ))

(defn draw-piece!
  [ctx piece pos ]
  (doseq [[i c] (map-indexed vector (piece-abs-coords piece pos))]
    (draw-cell! ctx c (= c pos))))

(defn draw-canvas!
  [app canvas]
  (let [ctx (.. canvas (getContext "2d"))]

    (set! (.. ctx -fillStyle) "#222")
    (.. ctx (fillRect 0 0 (* cell-size cols) (* cell-size rows)))

    (let [piece (:piece app)
          pos (:position app)]
      (when (and piece pos)
        (let [places (state-places app)]
          (set! (.. ctx -fillStyle) "#555")
          (set! (.. ctx -strokeStyle) "#AAA")
          (doseq [[i {:keys [piece position]}] (map-indexed vector (take places states))]
            (let [x 7]
              (set! (.. ctx -globalAlpha) (/ (max 0 (min x (- i (- places x)))) x 5)))
            (draw-piece! ctx piece position))
            )
        (set! (.. ctx -globalAlpha) 1)
        (set! (.. ctx -fillStyle) dark-purple)
        (set! (.. ctx -strokeStyle) light-purple)
        (draw-piece! ctx piece pos)))
    ))

(defcomponent canvas
  [app owner]
  (did-mount [_]
    (let [canvas (om/get-node owner "canvas")]
      (set! (.. canvas -width) (* cols cell-size))
      (set! (.. canvas -height) (* rows cell-size))
      (draw-canvas! app (om/get-node owner "canvas"))
      ))
  (did-update [_ _ _]
    (draw-canvas! app (om/get-node owner "canvas"))
    )
  (render [_]
    (html
      [:div.canvas-2a4d7
       [:canvas
        {:ref "canvas"
         :style {:position "relative"}
         }
        ]])))

(defcomponent slide
  [app owner]
  (render
    [_]
    (html
      [:div
       [:h1 "6. Store the state."]
       (om/build code app)
       (om/build canvas app)])))

(defn init
  [id]
  (om/root
    slide
    app-state
    {:target (. js/document (getElementById id))}))

(defn resume
  []
  )

(defn stop
  []
  )
