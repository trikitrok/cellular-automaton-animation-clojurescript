(ns cellular-animation.evolution)

(defn- extract-neighborhoods [state]
  (partition 3 1 (repeat 0) (cons 0 state)))

(defn- evolve-once [rule state]
  (mapv rule (extract-neighborhoods state)))

(defn- pad-with-zeroes [state]
  (vec (cons 0 (conj state 0))))

(defn- next-state [rule states]
  (->> (last states)
       pad-with-zeroes
       (evolve-once rule)))

(defn evolve [rule states]
  (->> (next-state rule states)
       (conj (mapv pad-with-zeroes states))
       vec))
