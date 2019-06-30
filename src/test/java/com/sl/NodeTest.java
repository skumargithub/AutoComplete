package com.sl;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.*;
import org.junit.Test;

public class NodeTest {
    @Test(/* Can we represent the root node? */)
    public void rootNode() {
        Node node = new Node(Optional.empty(), 0);

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("", 0));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with no children? */)
    public void nodeWithNoChild() {
        Node node = new Node(Optional.of('a'), 1);

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("a", 1));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child? */)
    public void nodeWithOneImmediateChild() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("b");

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("ab", 1));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with TWO immediate children? */)
    public void nodeWithTwoImmediateChildren() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("b");
        node.add("c");

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("ab", 1));
        expectedNodeStrings.add(new ImmutablePair<>("ac", 1));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with TWO immediate children? */)
    public void nodeWithTwoImmediateRepeatChildren() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("b");
        node.add("c");

        node.add("b");
        node.add("c");

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("ab", 2));
        expectedNodeStrings.add(new ImmutablePair<>("ac", 2));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child who has an immediate child? */)
    public void nodeWithImmediateChildWhoHasImmediateChild() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("bc");

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("abc", 1));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with intermediate ends (aa and aac) ? */)
    public void nodeWithIntermediteEndingChild() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("a");   // aa
        node.add("ac");  // aac

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("aa", 1));
        expectedNodeStrings.add(new ImmutablePair<>("aac", 1));

        assert(nodeStrings.equals(expectedNodeStrings));
    }

    @Test(/* Can we represent a node with ONE immediate child who has TWO immediate children? */)
    public void nodeWithImmediateChildWhoHasTwoImmediateChildren() {
        Node node = new Node(Optional.of('a'), 0);
        node.add("bc");
        node.add("bd");

        Set<Pair<String, Integer>> nodeStrings = node.getStrings();

        Set<Pair<String, Integer>> expectedNodeStrings = new HashSet<>();
        expectedNodeStrings.add(new ImmutablePair<>("abc", 1));
        expectedNodeStrings.add(new ImmutablePair<>("abd", 1));

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
            Node node = new Node(Optional.of(startChar), startIsEnding ? 1 : 0);

            Set<Pair<String, Integer>> expectedNodeStrings = new TreeSet<>();
            if (startIsEnding) expectedNodeStrings.add(new ImmutablePair<>(Character.toString(startChar), 1));
            for (int i = 0; i < 1000; ++i) { // Add 1000 strings to this node
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
                String toAdd = Character.toString(startChar) + generatedString;
                Predicate<Pair<String, Integer>> predicate = p-> p.getLeft().equals(toAdd);

                // If we already have a previous entry, increment it's count
                List<Pair<String, Integer>> oldItems = expectedNodeStrings.stream().filter(p -> p.getLeft().equals(toAdd)).collect(Collectors.toList());
                if(!oldItems.isEmpty()) {
//                    System.err.println("Found old item: " + oldItems);
                    Pair<String, Integer> oldItem = oldItems.get(0);
                    expectedNodeStrings.remove(oldItem);

                    Pair<String, Integer> newItem = new ImmutablePair<>(toAdd, oldItem.getRight() + 1);
//                    System.err.println("Replacing with: " + newItem);

                    expectedNodeStrings.add(newItem);
                } else {
                    expectedNodeStrings.add(new ImmutablePair<>(toAdd, 1));
                }

                node.add(generatedString.toString());
            }

            Set<Pair<String, Integer>> nodeStrings = node.getStrings();
//        System.err.println(nodeStrings);
//        System.err.println(expectedNodeStrings);

            assert (expectedNodeStrings.equals(nodeStrings));
        }
    }
}
