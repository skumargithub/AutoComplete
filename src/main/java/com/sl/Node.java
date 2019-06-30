package com.sl;

import java.util.*;
import org.apache.commons.lang3.tuple.*;

public class Node {
    public static Set<Character> POSSIBLE_VALUES = new TreeSet<>();
    public static String POSSIBLE_VALUES_AS_STRING = "abcdefghijklmnopqrstuvwxyz ";
    static {
        for(int i = 0; i < POSSIBLE_VALUES_AS_STRING.length(); ++i) {
            POSSIBLE_VALUES.add(POSSIBLE_VALUES_AS_STRING.charAt(i));
        }
    }

    public static char intToChar(int input) {
        return POSSIBLE_VALUES_AS_STRING.charAt(input);
    }

    private Optional<Character> value;
    public int isEndingCount;

    public Node(Optional<Character> value, int isEndingCount) {
        this.value = value;
        this.isEndingCount = isEndingCount;
    }

    // Use treemap to get an ordering on the keys
    public TreeMap<Character, Node> children = new TreeMap<>();

    public void toString(StringBuilder sb) {
//        System.err.println("parent: " + parent);
//        System.err.println("keySet: " + children.keySet());

        sb.append("(" + value + "," + isEndingCount + ")");
        sb.append("->");
        sb.append(children.keySet());
        sb.append(" ");
    }

    public Set<Pair<String, Integer>> getStrings() {
        Set<Pair<String, Integer>> result = new TreeSet<>();

        // Leaf node, it must be a proper string
        if(this.children.entrySet().isEmpty()) {
//            System.err.println("No children, only return node value: " + Character.toString(this.value));
            result.add(new ImmutablePair(getValueAsString(), this.isEndingCount));

            return result;
        }

        for(Map.Entry<Character, Node> entry : this.children.entrySet()) {
//            System.err.println("we have children for node with value: " + Character.toString(this.value));
            Node node = entry.getValue();

            Set<Pair<String, Integer>> childStringAndCounts = node.getStrings();
            for (Pair<String, Integer> childStringAndCount : childStringAndCounts) {
                String toAdd = getValueAsString() + childStringAndCount.getLeft();
                int toAddCount = childStringAndCount.getRight();

                result.add(new ImmutablePair<>(toAdd, toAddCount));
//                System.err.println("Adding : " + toAdd);
            }

            // If the node is ending, add an extra entry
            if(this.isEndingCount > 0) {
//                System.err.println("Node is ending with value: " + Character.toString(this.value));
                String toAdd = Character.toString(this.value.get());
                result.add(new ImmutablePair(toAdd, this.isEndingCount));
//                System.err.println("Adding : " + toAdd);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toString(sb);

        return sb.toString();
    }

    public void add(String s) {
//        System.err.println("Trying at add: " + s);
        char c0 = s.toLowerCase().charAt(0);

        children.putIfAbsent(c0, new Node(Optional.of(c0), 0));
        Node child = children.get(c0);
        if(s.length() == 1) {
            child.isEndingCount++;      // The string ends here, so increment the word ending count
        } else {
            String ss = s.substring(1);
//            System.err.println("Adding " + ss + " to: " + c0);
            child.add(ss);
        }
    }

    public String getValueAsString() {
        return this.value.isPresent() ? Character.toString(this.value.get()) : "";
    }
}
