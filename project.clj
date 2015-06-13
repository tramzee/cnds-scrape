(def version "1.0.0")
(defproject cnds-scrape version
  :description "Temporary lib until cnscrape has all the scrapers"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]

                 [circleci/clj-yaml "0.5.3"]
                 [cnscrape "1.0.0"]]

  :plugins [[lein-ancient "0.4.4"]
            [s3-wagon-private "1.1.2" :exclusions [commons-codec commons-logging]]]

  :repositories [["cnds" {:url "s3p://clojars.cnds.io/cnds/releases"}]]
  :manifest {"Implementation-Version" ~version})
