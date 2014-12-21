(ns centipair.core.utilities.ajax
  (:require [ajax.core :refer [GET POST json-request-format edn-request-format json-response-format]]
            [centipair.core.utilities.dom :as dom])
  (:use [centipair.core.components.notifier :only [notify]]
        [centipair.core.components.input :only [append-error]]))


(defn error-handler [response]
  (let [status (:status response)]
    (case status
      404 (notify 404)
      500 (notify 500)
      422 (notify 422 "The submitted data is not valid"))))

(defn success-handler [function-handler response]
  (notify 200)
  (function-handler response))

(defn post [url params function-handler]
  (do
    (notify 102 "Loading")
    (POST url
          :params params
          :handler (partial success-handler function-handler)
          :error-handler error-handler
          :format (json-request-format)
          :headers {:X-CSRF-Token (dom/get-value "__anti-forgery-token")}
          :response-format (json-response-format {:keywords? true}))))


(defn to-key
  "each is an atom"
  [previous each]
  (assoc previous (keyword (:id @each)) (:value @each)))


(defn handle-form-error [form-state form response]
  (notify 422 "The submitted data is not valid")
  (let [combined (conj form form-state)]
    (doseq [each combined]
      (append-error (:errors (:response response)) each))))

(defn form-error-handler [form-state form response]
  (let [status (:status response)]
    (case status
      404 (notify 404)
      500 (notify 500)
      422 (handle-form-error form-state form response))))


(defn form-success-handler []
  
  )


(defn form-post [form-state url form function-handler]
  (do
    (notify 102 "Loading")
    (POST url
          :params (reduce to-key {} form)
          :handler (partial success-handler function-handler)
          :error-handler (partial form-error-handler form-state form)
          :format (json-request-format)
          :headers {:X-CSRF-Token (dom/get-value "__anti-forgery-token")}
          :response-format (json-response-format {:keywords? true}))))

