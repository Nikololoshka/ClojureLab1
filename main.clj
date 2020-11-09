;; ========================================================
;; Напишите функцию, выбирающую из списка
;; только элементы, встречающиеся более n-раз.


(defn counter [arr m]
    (let [size (count arr)]
        (if (= size 0)
            m
            (let [e (first arr)]
                (recur 
                    (rest arr) 
                    (assoc m e 
                        (if (contains? m e) 
                            (inc (get m e))
                            1
                        ) 
                    ) 
                )
            )
        )
    )
)

(defn selector-by [m n]
    (loop [mm m]
        (when-not (empty? mm)
            (let [e (first mm) k (first e) v (last e)]
                (if (> v n)
                    (cons k (selector-by (rest mm) n))
                    (recur (rest mm))
                )
            )
        )
    )
)

(defn selector [arr n]
    (selector-by (counter arr {}) n)
)

(selector '(1 1 2 22 2 5 3 0 9 -3 -3 -3 2 5 9 5) 2)
