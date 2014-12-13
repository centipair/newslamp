(ns centipair.core.components.input
  (:require [reagent.core :as reagent :refer [atom]]))


(def input-container-class "pure-control-group")
(def input-container-class-error "pure-control-group validation-error")

(def disabled-primary-button-class "pure-button pure-button-primary pure-button-disabled")
(def primary-button-class "pure-button pure-button-primary")

(def form-error (atom ""))

(defn update-value [form-fields field value]
  (reset! field (assoc @field :value value)))


(defn button-state [form-fields button]
  
  )


(defn text
  [form-fields field]
  [:div {:class (if (nil? (:class-name @field)) input-container-class (:class-name @field))}
   [:label {:for (:id @field)} (:label @field)]
   [:input {:type (:type @field) :id (:id @field)
            :placeholder
            (if (nil? (:placeholder @field))
              ""
              (:placeholder @field))
            :value (:value @field)
            :on-change #(update-value  form-fields field (-> % .-target .-value) )
            }]
   [:label (if (nil? (:message @field))
             ""
             (:message @field))]])


(defn valid-field? [field]
  (if (nil? (:validator @field))
    true
    (let [result ((:validator @field) (:value field))]
      (if (:valid result)
        true
        (do
          (swap! field assoc :message (:message result) :class-name input-container-class-error)
          false
          )
        )
      )))


(defn valid-form? [form-fields]
  (apply = true (map valid-field? form-fields)))


(defn perform-action [action form-fields]
  (if (valid-form? form-fields)
    (action)
    (reset! form-error "Form error!")))

(defn button
  [form-fields action-button]
  [:button {:class primary-button-class
            :on-click #(perform-action (:on-click @action-button) form-fields)
            :disabled ""
            } 
   (:label @action-button)])

(defn checkbox
  [form-fields field]
  )

(defn radio
  [form-fields field]
  
  )

(defn input-field [form-fields field]
  (case (:type @field)
    "text" (text form-fields field)
    "email" (text form-fields field)
    "checkbox" (checkbox form-fields field)
    "radio" (radio form-fields field)))


(defn form-aligned [title form-fields action-button]
  [:form {:class "pure-form pure-form-aligned"}
   [:fieldset
    [:legend [:h3 title] [:span @form-error]]
    (doall (map (partial input-field form-fields) form-fields))
    [:div {:class "pure-controls"} (button form-fields action-button)]]])

