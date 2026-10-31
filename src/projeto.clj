(ns projeto)
;; R1: Municípios da 14ª Regional contidos num vetor de mapas
(def municipios-14-regional
  [{:nome "Baía da Traição" :populacao 9648 :area 102.756 :regiao "Litoral Norte"}
   {:nome "Capim" :populacao 7020 :area 79.876 :regiao "Litoral Norte"}
   {:nome "Cuité de Mamanguape" :populacao 6450 :area 107.680 :regiao "Litoral Norte"}
   {:nome "Curral de Cima" :populacao 5340 :area 86.428 :regiao "Litoral Norte"}
   {:nome "Itapororoca" :populacao 18610 :area 145.807 :regiao "Litoral Norte"}
   {:nome "Jacaraú" :populacao 14969 :area 256.845 :regiao "Litoral Norte"}
   {:nome "Mamanguape" :populacao 46966 :area 337.434 :regiao "Litoral Norte"}
   {:nome "Marcação" :populacao 9180 :area 122.700 :regiao "Litoral Norte"}
   {:nome "Mataraca" :populacao 8390 :area 182.439 :regiao "Litoral Norte"}
   {:nome "Pedro Régis" :populacao 6021 :area 71.500 :regiao "Litoral Norte"}
   {:nome "Rio Tinto" :populacao 25394 :area 466.984 :regiao "Litoral Norte"}])

;; R2: 5 Funções puras

;; 1ª Função: Calcula a densidade populacional
(defn calcular-densidade-populacional [lista]
  (map (fn [c]
         {:nome (:nome c)
          :densidade (format "%.2f" (double (/ (:populacao c) (:area c))))})
       lista))

;; 2ª Função: Ordena os municípios da maior para a menor área
(defn ordenar-por-maior-area [cidades]
  (sort-by :area > cidades))

;; 3ª Função: Soma a população total
(defn somar-populacao [lista]
  (reduce + (map :populacao lista)))

;; 4ª Função: Filtra por campo dentro do mapa de cidades
(defn filtrar-por-campo [campo limite lista]
  (filter #(> (campo %) limite) lista))

;; 5ª Exibe a cidade mais populosa da 14ª Regional
(def cidades-mais-populosas (partial filtrar-por-campo :populacao 40000))

;; 6ª Função: Descobre a colocação de uma cidade e encerra a busca (R2 e R5)
(defn posicao-municipio [nome-alvo lista]
  (loop [cidades lista
         pos 1]
    (cond
      (empty? cidades) nil
      (= (:nome (first cidades)) nome-alvo) pos
      :else (recur (rest cidades) (inc pos)))))

(defn -main []
  (println (calcular-densidade-populacional municipios-14-regional))
  (println)
  (println (ordenar-por-maior-area municipios-14-regional))
  (println)
  (println (cidades-mais-populosas municipios-14-regional))
  (println)
  (println (str "A soma total da população dos municípios da 14ª regional é " (somar-populacao municipios-14-regional)))
  (println)
  (println (filtrar-por-campo :populacao 40000 municipios-14-regional))
  (println)
  (println (str "Baia da Traição é o "
                (posicao-municipio "Baía da Traição" (ordenar-por-maior-area municipios-14-regional))
                "º maior município em termos de área territorial.")))
