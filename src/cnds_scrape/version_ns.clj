(ns cnds-scrape.version-ns
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def ^:private pom-file "META-INF/maven/cnds-scrape/cnds-scrape/pom.properties")

(defn get-version []
  (let [re #"^version="]
    (some->
     (some->> (io/resource pom-file)
              io/reader
              line-seq
              (filter (partial re-find re))
              first)
     (str/replace re ""))))
