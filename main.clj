;; ; Дерево из вложенных ассоциативных массивов
;; (def myTree {
;;     "a" 1
;;     "b" "t"
;;     "noarray" {
;;         "a" true
;;         "no" true
;;         "abc" {
;;             :1 "a"
;;             "y" "f"
;;         } 
;;         "y" "f"
;;     }
;;     "to" "f"
;;     nil -100
;;     "array" [1 4 9 16 25 36]
;;     "treechild" {
;;         "x" 3
;;         "y" "f"
;;         "z" 1
;;     }
;;     "1" {
;;         "list" '(1 2 3)
;;     }
;;     false true
;; })


;; (defn treeFinder [parent subtree k v]
;;     (when-not (empty? subtree)
;;         (let [entry (first subtree) ek (first entry) ev (last entry)] 
;;             (println ek)
;;             (if (and (= ek k) (= ev v))
;;                 (cons parent 
;;                     (lazy-seq 
;;                         (if (map? ev)
;;                             (treeFinder ek ev k v)
;;                             (treeFinder parent (rest subtree) k v)
;;                         )
;;                     )
;;                 )
;;                 (if (map? ev)
;;                     (let [res (treeFinder ek ev k v)]
;;                         (if (nil? res)
;;                             (recur parent (rest subtree) k v)
;;                             res
;;                         )
;;                     )
;;                     (recur parent (rest subtree) k v)
;;                 )
;;             )
;;         )
;;     )
;; )

;; ;(treeFinder "myTree" myTree "y" "f")

;; (defn find-in [tree parent x y]
;;     (some
;;         (fn [[k v]]
;;             (cond 
;;                 (and (= v y) (= k x)) 
;;                     (do
;;                         [parent]
;;                     )
;;                 (map? v) 
;;                     (let [r (find-in v k x y)]
;;                         (when-not (nil? r)
;;                             (into [parent] r)
;;                         )
;;                     )
;;             )
;;         )
;;         tree
;;     )
;; )

;; (find-in myTree "myTree" "y" "f")

;; ;(doseq [[k v] myTree]
;; ;    (println k v)
;; ;)

; Поиск пары в дереве
; tree - дерево, k - ключ, v - значение
;; (defn treeFinder [tree k v]
;;     (if-not (empty? tree)
;;         ; Дерево не пусто
;;         (let [entry (first tree) ek (first entry) ev (last entry)] 
;;             ; Проверка на нужную пару
;;             (if (and (= ek k) (= ev v))
;;                 [k]
;;                 ; Проверка на вложенное дерево
;;                 (if (map? ev)
;;                     (let [res (treeFinder ev k v)]
;;                         ; Если результат nil 
;;                         (if (false? res)
;;                             (recur (rest tree) k v)
;;                             (let [t (treeFinder (rest tree) k v)]
;;                                 (if (false? t)
;;                                     [ek res]
;;                                     [[ek res] t]
;;                                 )
;;                             )
;;                         )
;;                     )
;;                     ; Если нет вложенного дерева, то идем дальше по текущему
;;                     (recur (rest tree) k v)
;;                 )
;;             )
;;         )
;;         ; Пустое дерево
;;         false
;;     )
;; )

;; (defn finder [tree k v]
;;     (let [result (treeFinder tree k v)]
;;         (if (false? result)
;;             (println "Pair not found")
;;             (println "Pair path:" result)
;;         )
;;     ) 
;; )

;; (finder myTree "y" "f")
;; (finder myTree false true)
;; (finder myTree "no" true)

;; (myTree '["noarray" "abc"])

;; ========================================================
;; Напишите функцию, выбирающую из списка
;; только элементы, встречающиеся более n-раз.

(defn selector [arr n]
    (let [size (count arr), el (first arr)]
        (loop [i 0] 
            (if (>= i size)
                (if (>= i n)
                    [el]
                    []
                )
                (if (= (nth arr i) el)
                    (recur (inc i))
                    (if (>= i n)
                        (cons el (lazy-seq (selector (drop i arr) n)))
                        (selector (drop i arr) n)
                    )
                )
            ) 
        )
    )
)

(selector '(1 1 2 22 2 5 3 0 9 -3 -3 -3 2 5 5) 2)
