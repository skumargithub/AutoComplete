package com.sl;

import java.util.*;
import org.junit.Test;

public class NodeTest {
    @Test(/* Can we represent a node with no children? */)
    public void nodeWithNoChild() {
        Node node = new Node(Optional.of('a'), true);

        Set<String> nodeStrings = node.getStrings();

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("a");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child? */)
    public void nodeWithOneImmediateChild() {
        Node node = new Node(Optional.of('a'), false);
        node.add("b");

        Set<String> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("ab");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with TWO immediate children? */)
    public void nodeWithTwoImmediateChildren() {
        Node node = new Node(Optional.of('a'), false);
        node.add("b");
        node.add("c");

        Set<String> nodeStrings = node.getStrings();

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("ab");
        expectedNodeStrings.add("ac");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child who has an immediate child? */)
    public void nodeWithImmediateChildWhoHasImmediateChild() {
        Node node = new Node(Optional.of('a'), false);
        node.add("bc");

        Set<String> nodeStrings = node.getStrings();

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("abc");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with intermediate ends (aa and aac) ? */)
    public void nodeWithIntermediteEndingChild() {
        Node node = new Node(Optional.of('a'), false);
        node.add("a");   // aa
        node.add("ac");  // aac

        Set<String> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("aa");
        expectedNodeStrings.add("aac");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child who has TWO immediate children? */)
    public void nodeWithImmediateChildWhoHasTwoImmediateChildren() {
        Node node = new Node(Optional.of('a'), false);
        node.add("bc");
        node.add("bd");

        Set<String> nodeStrings = node.getStrings();

        Set<String> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add("abc");
        expectedNodeStrings.add("abd");

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    private char intToChar(int input) {
        switch(input) {
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            case 4: return 'e';
            case 5: return 'f';
            case 6: return 'g';
            case 7: return 'h';
            case 8: return 'i';
            case 9: return 'j';
            case 10: return 'k';
            case 11: return 'l';
            case 12: return 'm';
            case 13: return 'n';
            case 14: return 'o';
            case 15: return 'p';
            case 16: return 'q';
            case 17: return 'r';
            case 18: return 's';
            case 19: return 't';
            case 20: return 'u';
            case 21: return 'v';
            case 22: return 'w';
            case 23: return 'x';
            case 24: return 'y';
            case 25: return 'z';
            default : return ' ';
        }
    }

    @Test(/* Can we represent a complicated node? */)
    public void nodeWithArbitraryStuff() {
        Random random = new Random();
        int min = 0;
        int max = 26;

        // Use all characters as the start node
        for(char startChar : Node.POSSIBLE_VALUES) {
            boolean startIsEnding = random.nextBoolean(); // which may or may not be an ending string
            Node node = new Node(Optional.of(startChar), startIsEnding);

            Set<String> expectedNodeStrings = new TreeSet<>();
            if (startIsEnding) expectedNodeStrings.add(Character.toString(startChar));
            for (int i = 0; i < 100; ++i) { // Add 100 strings to this node
                StringBuilder generatedString = new StringBuilder();
                generatedString.append(startChar);

                int stringLength = random.nextInt(50 - 1 + 1) + 1;   // of varying lengths [1..50]
                for (int j = 0; j < stringLength; ++j) {
                    int generatedNum = random.nextInt(max - min + 1) + min;
                    char generatedChar = intToChar(generatedNum);
                    generatedString.append(generatedChar);
//               System.err.println("Generated " + Character.toString(generatedChar) + " for " + generatedNum);
                }

//            System.err.println("Generated string: " + generatedString.toString());
                expectedNodeStrings.add(Character.toString(startChar) + generatedString);
                node.add(generatedString.toString());
            }

            Set<String> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);
//        System.err.println(expectedNodeStrings);

            assert (expectedNodeStrings.equals(nodeStrings));
        }
    }
}
