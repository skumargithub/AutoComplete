package com.sl;

import java.util.*;
import org.junit.Test;

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
}
