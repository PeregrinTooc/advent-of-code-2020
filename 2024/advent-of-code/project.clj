(defproject advent-of-code "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
                      :url  "https://www.eclipse.org/legal/epl-2.0/"}
            :dependencies [[org.clojure/clojure "1.11.1"]]
            :main ^:skip-aot advent-of-code.core
            :target-path "target/%s"
            :resource-paths ["resources"]
            :profiles {:uberjar {:aot      :all
                                 :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
