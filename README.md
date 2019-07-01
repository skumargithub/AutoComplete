# Auto Complete
This is a Auto complete engine I implemented using a Trie

## Requirements Build
Functional maven and java (>= 8)

## Compile, Test and Build
mvn clean compile test package

## Run (using the built in Phrases.txt file)
java -cp target/auto-complete-1.0-SNAPSHOT-jar-with-dependencies.jar com.sl.AutoComplete Phrases.txt

## To generate a phrases file (for testing)
java -cp target/auto-complete-1.0-SNAPSHOT-jar-with-dependencies.jar com.sl.util.GeneratePhrases <PhrasesOutputFile>

## Description of the solution
Prefix problems are classically implemented using the Trie data structure. I followed that approach.

The only complication was the requirement to return the most repeated phrase as a priority.
For that I decided to keep a count in each node, to indicate how many phrases ended there.

A request to get all phrases matching a certain prefix
1. Finds the deepest node matching the prefix.
2. Get all phrases from that node.
3. Prepends the prefix to all the phrases from that node.
4. Return only the requested number of phrases.

A weakness of this approach is that I have to get all the phrases first, before applying the count filter.
It *might* be possible to not do this (i.e. somehow only get the phrase with the highest count)
but I suspect the implementation will get pretty fancy.
