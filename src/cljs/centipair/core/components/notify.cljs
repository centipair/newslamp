(ns centipair.core.components.notify)


(def notifier-state (atom {:class "notify" :text ""}))


(defn notify [code & [message]]
  (let [text (if (and (= code 102) (nil? message)) "Loading.." message)
        invalid-data-message (if (and (= code 422) (nil? message)) "Invalid data" message)]
    (case code
      200 (reset! notifier-state {:class "notify" :text ""})
      102 (reset! notifier-state {:class "notify notify-loading" :text text})
      404 (reset! notifier-state {:class "notify notify-error" :text "Not found"})
      500 (reset! notifier-state {:class "notify notify-error" :text "Internal server error"})
      422 (reset! notifier-state {:class "notify notify-error" :text invalid-data-message})
      (reset! notifier-state {:class "notify" :text ""}))))
