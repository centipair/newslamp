(defproject newslamp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :source-paths ["src/clj" "src/cljs", "src/cljs-mobile"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                                  [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [reagent "0.5.0-alpha"]
                 [reagent-utils "0.1.0"]
                 [secretary "1.2.0"]
                 [lib-noir "0.9.4"]
                 [ring-server "0.3.1"]
                 [selmer "0.7.5"]
                 [com.taoensso/timbre "3.3.1"]
                 [com.taoensso/tower "3.0.2"]
                 [markdown-clj "0.9.58"
                  :exclusions [com.keminglabs/cljx]]
                 [environ "1.0.0"]
                 [im.chit/cronj "1.4.3"]
                 [noir-exception "0.2.3"]
                 [prone "0.6.0"]
                 [clojurewerkz/cassaforte "2.0.0-rc2"]
                 [org.immutant/immutant "2.0.0-beta1"]
                 [com.draines/postal "1.11.1"]
                 [liberator "0.12.2"]
                 [com.novemberain/validateur "2.3.1"]
                 [cheshire "5.3.1"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [cljs-ajax "0.3.2"]
                 [secretary "1.2.1"]
                 [enfocus "2.1.1"]]

  :repl-options {
                 :init-ns centipair.repl
                 :init (centipair.repl/start-server)}
  :main centipair.core.run
  :jvm-opts ["-server"]
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]
            [lein-ancient "0.5.5"]
            [lein-cljsbuild "1.0.3"]
            [lein-asset-minifier "0.2.0"]]
  :ring {:handler centipair.handler/app
         :init    centipair.handler/init
         :destroy centipair.handler/destroy}
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
             :aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.3.1"]
                        [pjstadig/humane-test-output "0.6.0"]]
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]
         :env {:dev true}}}
  :cljsbuild {:builds
               [{;; CLJS source code path
                 :id "release"
                 :source-paths ["src/cljs"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "resources/public/js/main.js"
                            
                            ;; minimal JS optimization directive
                            ;;:optimizations :whitespace
                            :optimizations :advanced
                            ;; generated JS code prettyfication
                            ;;:pretty-print true
                            :pretty-print false
                            :preamble ["reagent/react.js"]
                            ;;:externs ["react/externs/react.js"]
                            }}
                {;; CLJS source code path
                 :id "dev"
                 :source-paths ["src/cljs"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "resources/public/js/main.js"
                            
                            ;; minimal JS optimization directive
                            :optimizations :whitespace
                            ;; generated JS code prettyfication
                            :pretty-print true
                            :preamble ["reagent/react.js"]
                            
                            }}
                {;; CLJS source code path
                 :id "release-admin"
                 :source-paths ["src/cljs-admin"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "resources/public/js/admin-main.js"
                            ;; minimal JS optimization directive
                            :optimizations :advanced
                            ;; generated JS code prettyfication
                            ;;:pretty-print true
                            :pretty-print false
                            :preamble ["reagent/react.js"]
                            :externs ["react/externs/react.js"]
                            }}
                {;; CLJS source code path
                 :id "dev-admin"
                 :source-paths ["src/cljs-admin"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "resources/public/js/admin-main.js"
                            
                            ;; minimal JS optimization directive
                            :optimizations :whitespace
                            ;; generated JS code prettyfication
                            :pretty-print true
                            :preamble ["reagent/react.js"]
                            
                            }}

                {;; CLJS source code path
                 :id "release-mobile"
                 :source-paths ["src/cljs-mobile"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "mobile/www/js/main.js"
                            
                            ;; minimal JS optimization directive
                            ;;:optimizations :whitespace
                            :optimizations :advanced
                            ;; generated JS code prettyfication
                            ;;:pretty-print true
                            :pretty-print false
                            :preamble ["react/react.min.js"]
                            :externs ["react/externs/react.js"]
                            }}
                {;; CLJS source code path
                 :id "dev-mobile"
                 :source-paths ["src/cljs-mobile"]
                 
                 ;; Google Closure (CLS) options configuration
                 :compiler {;; CLS generated JS script filename
                            :output-to "mobile/www/js/main.js"
                            
                            ;; minimal JS optimization directive
                            :optimizations :whitespace
                            ;; generated JS code prettyfication
                            :pretty-print true
                            
                            
                            }}]}
   
  :min-lein-version "2.5.0"
  :minify-assets
  {:assets
    {"resources/public/css/style.css" ["resources/public/css/pure-min.css"
                                       "resources/public/css/style/grids-responsive-min.css"
                                       "resources/public/css/style/side-menu.css"
                                       "resources/public/css/custom.css"]
     "resources/public/css/ie.css" ["resources/public/css/pure-min.css"
                                    "resources/public/css/ie/grids-responsive-old-ie-min.css"
                                    "resources/public/css/ie/side-menu-old-ie.css"
                                    "resources/public/css/custom.css"]}})
