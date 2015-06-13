(ns cnds-scrape.core
  (:require [clj-yaml.core :as yaml]
            [cnds-scrape.version-ns :as v]
            [cnscrape.core :as cns]))

(defn scrape-map
  "Scrape a URL"
  [url]
  (try
    (assoc (cns/scrape-map url) :_scraper_version (v/get-version))

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
