(ns centipair.core.utilities.validators)


(defn matches-regex?
  "Returns true if the string matches the given regular expression"
  [v regex]
  (boolean (re-matches regex v)))


(defn has-value?
  "Returns true if v is truthy and not an empty string."
  [v]
  (and v (not= v "")))


(defn not-nil?
  "Returns true if v is not nil"
  [v]
  (boolean (or v (false? v))))


(defn is-email?
  "Returns true if v is an email address"
  [v]
  (if (nil? v)
    false
    (matches-regex? v #"(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")))


(defn min-length?
  "Returns true if v is greater than or equal to the given len"
  [v len]
  (>= (count v) len))


;;error message for required field 
(def required-field-error "This field is required")


;;error message for email field
(def email-field-error "Not a valid email address")


;;error message for password length
(def password-length-error "Minimum 6 characters required")


(defn validation-error [message]
  {:message message :valid false})


(defn validation-success []
  {:valid true})


(defn required [value]
  (if (has-value? value)
    (validation-success)
    (validation-error required-field-error)))
