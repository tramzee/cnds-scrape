(ns cnds-scrape.core
  (:require [clj-yaml.core :as yaml]
            [cnds-scrape.version-ns :as v]
            [cnscrape.core :as cns]
            [ice-clj.core :as ice]))

(defn scrape-map
  "Scrape a URL"
  [url]
  (try
    (assoc (case (clojure.string/replace url #"^.*[/\.]([^\.]+)\.com/.*" "$1")
             ("allure"
              "architecturaldigest"
              "arstechnica"
              "bonappetit"
              "brides"
              "cntraveler"
              "details"
              "glamour"
              "golfdigest"
              "gq"
              "newyorker"
              "self"
              "style"
              "teenvogue"
              "vanityfair"
              "vogue"
              "wired"
              "wmagazine") (cns/scrape-map url)
             (let [raw (ice/scrape-url url)
                   doc (first (yaml/parse-string raw))]
               doc))
      :_scraper_version (v/get-version))

    (catch java.io.FileNotFoundException e
      (println "\ncould not find URL" url))
    (catch java.io.IOException e
      (println "\nIOException for URL" url))
    (catch java.lang.NoClassDefFoundError e
      (println "\nfailed to parse" url))
    (catch org.yaml.snakeyaml.reader.ReaderException re
      (println "yaml error while scraping " url))
    (catch Exception e
      (println "\nCaught exception" e "for URL" url))))
