package com.sl;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import org.junit.Test;

import org.apache.commons.io.*;

public class TrieTest {
    @Test(/* Test the coding example */)
    public void testExample1() {
        Trie trie = new Trie();
        trie.add("bat");
        trie.add("bat");
        trie.add("bark");
        trie.add("bar");

        System.err.println(trie);

        Set<String> result = trie.getStrings("ba", 1);
        System.err.println(result);

        Set<String> expectedResult = new TreeSet<>();
        expectedResult.add("bat");

        assert(result.equals(expectedResult));

        result = trie.getStrings("ba", 2);
        System.err.println(result);

        expectedResult.clear();
        expectedResult.add("bat");
        expectedResult.add("bar");

        result = trie.getStrings("be", 1);
//        System.err.println(result);
        assert(result.isEmpty());
    }

    @Test(/* Test the coding example */)
    public void testExample2() {
        Trie trie = new Trie();
        trie.add("bat");
        trie.add("Bat");
        trie.add("bark");
        trie.add("bar");

        System.err.println(trie);

        Set<String> result = trie.getStrings("ba", 1);
        System.err.println(result);

        Set<String> expectedResult = new TreeSet<>();
        expectedResult.add("bat");

        assert(result.equals(expectedResult));

        result = trie.getStrings("ba", 2);
        System.err.println(result);

        expectedResult.clear();
        expectedResult.add("bat");
        expectedResult.add("bar");

        result = trie.getStrings("be", 1);
//        System.err.println(result);
        assert(result.isEmpty());

        result = trie.getStrings("b", 0);
        System.err.println(result);
        expectedResult.clear();
        expectedResult.add("bar");
        expectedResult.add("bark");
        expectedResult.add("bat");
        assert(result.equals(expectedResult));
    }

    @Test(/* ONE char test on the generated Phrases */)
    public void testGeneratedPhrasesTextOneChar() throws IOException {
        List<String> phrases = FileUtils.readLines(new File("Phrases.txt"), (String) null);
        Trie trie = new Trie();
        for(String phrase : phrases) {
            trie.add(phrase);
        }

        String prefix = "a";
        Set<String> results = trie.getStrings(prefix, 1);
        System.err.println(results);
        Set<String> expectedResults = new TreeSet<>();
        expectedResults.add("a");

        assert(results.size() == 1);
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 2);
//        System.err.println(results);
        expectedResults = new TreeSet<>();
        expectedResults.add("a");
        expectedResults.add("adphyz rgogg");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 3);
//        System.err.println(results);
        expectedResults = new TreeSet<>();
        expectedResults.add("a");
        expectedResults.add("adphyz rgogg");
        expectedResults.add("afogp cu z");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 4);
//        System.err.println(results);
        expectedResults = new TreeSet<>();
        expectedResults.add("a");
        expectedResults.add("adphyz rgogg");
        expectedResults.add("afogp cu z");
        expectedResults.add("a hsuo");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 0);
//        System.err.println(results);
        expectedResults = new TreeSet<>(phrases.stream().filter(p -> p.startsWith(prefix)).collect(Collectors.toList()));

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));
    }

    @Test(/* TWO char test on the generated Phrases */)
    public void testGeneratedPhrasesTextTwoChar() throws IOException {
        List<String> phrases = FileUtils.readLines(new File("Phrases.txt"), (String) null);
        Trie trie = new Trie();
        for(String phrase : phrases) {
            trie.add(phrase);
        }

        String prefix = "a ";
        Set<String> results = trie.getStrings(prefix, 1);
//        System.err.println(results);

        Set<String> expectedResults = new TreeSet<>();
        expectedResults.add("a hsuo");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 2);
//        System.err.println(results);

        expectedResults = new TreeSet<>();
        expectedResults.add("a hsuo");
        expectedResults.add("a jqbpku ttds z hbffpx d zx");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 3);
        System.err.println(results);

        expectedResults = new TreeSet<>();
        expectedResults.add("a hsuo");
        expectedResults.add("a jqbpku ttds z hbffpx d zx");
        expectedResults.add("a kfw oagrb hz p nel xvku m exq");
        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 4);
//        System.err.println(results);

        expectedResults = new TreeSet<>();
        expectedResults.add("a hsuo");
        expectedResults.add("a jqbpku ttds z hbffpx d zx");
        expectedResults.add("a kfw oagrb hz p nel xvku m exq");
        expectedResults.add("a kqf pgdu rxspal sdfwxa");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 0);
//        System.err.println(results);

        expectedResults = new TreeSet<>(phrases.stream().filter(p -> p.startsWith(prefix)).collect(Collectors.toList()));

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));
    }

    @Test(/* THREE char test on the generated Phrases */)
    public void testGeneratedPhrasesTextThreeChar() throws IOException {
        List<String> phrases = FileUtils.readLines(new File("Phrases.txt"), (String) null);
        Trie trie = new Trie();
        for(String phrase : phrases) {
            trie.add(phrase);
        }

        String prefix = "ap ";

        Set<String> results = trie.getStrings(prefix, 1);
//        System.err.println(results);

        Set<String> expectedResults = new TreeSet<>();
        expectedResults.add("ap kq w oomy bktr he box");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 2);
//        System.err.println(results);

        expectedResults = new TreeSet<>();
        expectedResults.add("ap kq w oomy bktr he box");
        expectedResults.add("ap vxi");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 3);
//        System.err.println(results);

        expectedResults = new TreeSet<>();
        expectedResults.add("ap kq w oomy bktr he box");
        expectedResults.add("ap vxi");
        expectedResults.add("ap igua");

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        // Same test as above but requesting 4 (only 3 available in the Phrases.txt file)
        results = trie.getStrings(prefix, 4);
//        System.err.println(results);

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));

        results = trie.getStrings(prefix, 0);
//        System.err.println(results);

        expectedResults = new TreeSet<>(phrases.stream().filter(p -> p.startsWith(prefix)).collect(Collectors.toList()));

        assert(results.size() == expectedResults.size());
        assert(results.equals(expectedResults));
    }
}
