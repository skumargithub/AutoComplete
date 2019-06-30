# SL

## Build
mvn package

## Run (using the built in Phrases.txt file)
java -cp target/auto-complete-1.0-SNAPSHOT-jar-with-dependencies.jar com.sl.AutoComplete Phrases.txt

## To generate a phrases file (for testing)
java -cp target/auto-complete-1.0-SNAPSHOT-jar-with-dependencies.jar com.sl.util.GeneratePhrases <PhrasesOutputFile>
