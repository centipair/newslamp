(ns centipair.core.components.input
  (:require [reagent.core :as reagent :refer [atom]]))


(def input-container-class "pure-control-group")


(defn update-value [field value]
  (reset! field (assoc @field :value value)))

(defn text
  [field]
  [:div {:class input-container-class} 
   [:label {:for (:id @field)} (:label @field)]
   [:input {:type (:type @field) :id (:id @field)
            :placeholder
            (if (nil? (:placeholder @field))
              ""
              (:placeholder @field))
            :value (:value @field)
            :on-change #(update-value  field (-> % .-target .-value) )
             }]
   [:label (if (nil? (:message @field))
             ""
             (:message @field))]])

(defn checkbox
  [field]
  )

(defn radio
  [field]
  
  )

(defn input-field [field]
  (case (:type @field)
    "text" (text field)
    "email" (text field)
    "checkbox" (checkbox field)
    "radio" (radio field)
    
    )
  )


(defn form-aligned [title form-fields]
  [:form {:class "pure-form pure-form-aligned"}
   [:fieldset
    [:legend [:h3 title]]
    (doall (map input-field form-fields))
    ]
   ]
  )
