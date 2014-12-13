(ns centipair.core.components.input
  (:require [reagent.core :as reagent :refer [atom]]
            [centipair.core.style :as style]))



(def form-error (atom ""))

(defn update-value [form-fields field value]
  (reset! field (assoc @field :value value)))


(defn button-state [form-fields button]
  
  )


(defn text
  [form-fields field]
  [:div {:class (if (nil? (:class-name @field)) style/input-container-class (:class-name @field))}
   [:label {:for (:id @field)} (:label @field)]
   [:input {:type (:type @field) :id (:id @field)
            :placeholder
            (if (nil? (:placeholder @field))
              ""
              (:placeholder @field))
            :value (:value @field)
            :on-change #(update-value  form-fields field (-> % .-target .-value) )
            }]
   [:span (if (nil? (:message @field))
             ""
             (:message @field))]])


(defn make-valid
  [field]
  (swap! field assoc :message "" :class-name style/input-container-class)
  true)


(defn valid-field? [field]
  (if (nil? (:validator @field))
    true
    (let [result ((:validator @field) (:value @field))]
      (if (:valid result)
        (make-valid field)
        (do
          (swap! field assoc :message (:message result) :class-name style/input-container-class-error)
          false)))))


(defn valid-form? [form-fields]
  (apply = true (map valid-field? form-fields)))


(defn perform-action [action form-fields]
  (if (valid-form? form-fields)
    (action)
    (reset! form-error "Form error!")))

(defn button
  [form-fields action-button]
  [:a {:class style/primary-button-class
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

