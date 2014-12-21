(ns centipair.core.components.input
  (:require [reagent.core :as reagent :refer [atom]]
            [centipair.core.style :as style]))



(defn update-value [field value]
  (reset! field (assoc @field :value value)))

(defn update-check [field checked?]
  (if checked?
    (reset! field (assoc @field :checked "checked"))
    (reset! field (assoc @field :checked ""))))



(defn text
  [field]
  [:div {:class (if (nil? (:class-name @field)) style/bootstrap-input-container-class (:class-name @field))}
   [:label {:for (:id @field) :class "col-sm-2 control-label"} (:label @field)]
   [:div {:class "col-sm-6"}
    [:input {:class "form-control"
             :type (:type @field) :id (:id @field)
             :placeholder
             (if (nil? (:placeholder @field))
               ""
               (:placeholder @field))
             :value (:value @field)
             :on-change #(update-value field (-> % .-target .-value) )
             }]]
   [:label {:class "col-sm-4 message-label"} (if (nil? (:message @field))
             ""
             (:message @field))]])


(defn make-valid
  [field]
  (swap! field assoc :message "" :class-name style/bootstrap-input-container-class)
  true)


(defn valid-field? [field]
  (if (nil? (:validator @field))
    true
    (let [result ((:validator @field) @field)]
      (if (:valid result)
        (make-valid field)
        (do
          (swap! field assoc
                 :message (:message result)
                 :class-name style/bootstrap-input-container-class-error)
          false)))))


(defn append-error [errors field]
  (let [key (keyword (:id @field))]
    (if (not (nil? (key errors)))
      (swap! field assoc
             :message (first (key errors))
             :class-name style/bootstrap-input-container-class-error))))


(defn valid-form? [form-fields]
  (apply = true (doall (map valid-field? form-fields))))


(defn perform-action [form action form-fields]
  (if (valid-form? form-fields)
    (do
      (swap! form assoc :error "")
      (action))
    (swap! form assoc :error "Form error!")))

(defn button
  [form form-fields action-button]
  [:div {:class style/bootstrap-input-container-class}
   [:div {:class "col-sm-offset-2 col-sm-10"}
    [:a {:class style/bootstrap-primary-button-class
         :on-click #(perform-action form (:on-click @action-button) form-fields)
         :disabled ""
         } 
     (:label @action-button)]]])

(defn checkbox
  [field]
  [:div {:class (if (nil? (:class-name @field)) style/bootstrap-input-container-class (:class-name @field))}
   [:div {:class "col-sm-offset-2 col-sm-6"}
    [:div {:class "checkbox"}
     [:label
      [:input {:type (:type @field) :id (:id @field)
               :value (:value @field)
               :on-change #(update-check field (-> % .-target .-checked) )
               :checked (:checked @field)
               }] (:label @field)]]]
   [:label {:class "col-sm-4 message-label"} (if (nil? (:message @field))
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
  [:form {:class "form-horizontal"}

    [:legend [:h3 (:title @form)] [:span {:class "form-error"} (:error @form)]]
    (doall (map input-field form-fields))
    [:div {:class "pure-controls"} (button form form-fields action-button)]])

