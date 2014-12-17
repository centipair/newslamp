(ns centipair.core.components.input
  (:require [reagent.core :as reagent :refer [atom]]
            [centipair.core.style :as style]))



(defn update-value [field value]
  (reset! field (assoc @field :value value)))


(defn button-state [form-fields button]
  
  )


(defn text
  [field]
  [:div {:class (if (nil? (:class-name @field)) style/input-container-class (:class-name @field))}
   [:label {:for (:id @field)} (:label @field)]
   [:input {:type (:type @field) :id (:id @field)
            :placeholder
            (if (nil? (:placeholder @field))
              ""
              (:placeholder @field))
            :value (:value @field)
            :on-change #(update-value field (-> % .-target .-value) )
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


(defn perform-action [form action form-fields]
  (if (valid-form? form-fields)
    (do
      (swap! form assoc :error "")
      (action))
    (swap! form assoc :error "Form error!")))

(defn button
  [form form-fields action-button]
  [:a {:class style/primary-button-class
            :on-click #(perform-action form (:on-click @action-button) form-fields)
            :disabled ""
            } 
   (:label @action-button)])

(defn checkbox
  [field]
  [:div {:class (if (nil? (:class-name @field)) style/input-container-control (:class-name @field))}
   [:label {:for (:id @field)}
    [:input {:type (:type @field) :id (:id @field)
             :value (:value @field)
             :on-change #(update-value field (-> % .-target .-value) )
             }] (:label @field)] 
   [:span (if (nil? (:message @field))
             ""
             (:message @field))]])

(defn radio
  [field]
  
  )

(defn input-field [field]
  (case (:type @field)
    "text" (text field)
    "email" (text field)
    "password" (text field)
    "checkbox" (checkbox field)
    "radio" (radio field)))


(defn form-aligned [form form-fields action-button]
  [:form {:class "pure-form pure-form-aligned"}
   [:fieldset
    [:legend [:h3 (:title @form)] [:span {:class "form-error"} (:error @form)]]
    (doall (map input-field form-fields))
    [:div {:class "pure-controls"} (button form form-fields action-button)]]])

