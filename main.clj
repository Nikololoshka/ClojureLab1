;; ========================================================
;; Напишите функцию, выбирающую из списка
;; только элементы, встречающиеся более n-раз.


(defn selector-imp [arr m n]
    (when-not (empty? arr)
        (let [k (first arr)]
            (if (contains? m k)
                (let [v (get m k)]
                    (if (> v n)
                        (recur (rest arr) m n)
                        (if (= v n)
                            (cons k (selector-imp (rest arr) (assoc m k (inc v)) n))
                            (recur (rest arr) (assoc m k (inc v)) n)
                        )
                    )
                )
                (recur (rest arr) (assoc m k 1) n)
            )
        )
    )
)

(defn selector [arr n]
    (selector-imp arr {} n)
)

(selector '(1 1 2 22 2 5 3 0 9 -3 -3 -3 2 5 9 5) 2)
