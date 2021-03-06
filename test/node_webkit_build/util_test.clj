(ns node-webkit-build.util-test
  (:require [clojure.test :refer :all]
            [node-webkit-build.util :refer :all]))

(deftest test-map-values
  (testing "maps the values and keep the keys"
    (is (= {:a 2 :b 4 :c 6}
           (map-values (partial * 2) {:a 1 :b 2 :c 3})))))

(deftest t-make-plist-entry-value
  (is (= (make-plist-entry-value "a")
         [:string "a"]))

  (is (= (make-plist-entry-value true)
         [:true]))

  (is (= (make-plist-entry-value false)
         [:false]))

  (is (= (make-plist-entry-value ["a" "b"])
         [:array [:string "a"] [:string "b"]]))

  (is (= (make-plist-entry-value {"a" "b"})
         [:dict [:key "a"] [:string "b"]])))

(deftest t-make-plist-entry
  (is (= (make-plist-pairs {"a" "b"})
         [[:key "a"] [:string "b"]]))

  (is (= (make-plist-pairs {:a "b"})
         [[:key "a"] [:string "b"]])))

(deftest t-make-plist
  (is (= [:plist {:version "1.0"} [:dict]]
         (make-plist {})))
  (is (= [:plist {:version "1.0"}
          [:dict
           [:key "LSHandlerRank"]
           [:string "Owner"]]]
         (make-plist {"LSHandlerRank" "Owner"}))))

(deftest t-make-plist-xml-str
  (is (= (make-plist-xml-str {"LSHandlerRank" "Owner"})
         "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">
<plist version=\"1.0\">
  <dict>
    <key>LSHandlerRank</key>
    <string>Owner</string>
  </dict>
</plist>
")))
