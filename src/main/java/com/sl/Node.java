package com.sl;

import java.util.*;

public class Node {
    public static Set<Character> POSSIBLE_VALUES = new TreeSet<>();
    static {
        String values = " abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < values.length(); ++i) {
            POSSIBLE_VALUES.add(values.charAt(i));
        }
    }

    public Optional<Character> value;
    public boolean isEnding;

    /*public Node(Optional<Character> value) {
        this.value = value;
        this.isEnding = false;
    }*/

    public Node(Optional<Character> value, boolean isEnding) {
        this.value = value;
        this.isEnding = isEnding;
    }

    // Use treemap to get an ordering on the keys
    public TreeMap<Character, Node> children = new TreeMap<>();

    public void toString(StringBuilder sb) {
//        System.err.println("parent: " + parent);
//        System.err.println("keySet: " + children.keySet());

        sb.append("(" + value + "," + isEnding + ")");
        sb.append("->");
        sb.append(children.keySet());
        sb.append(" ");
    }

    public Set<String> getStrings() {
        Set<String> result = new TreeSet<>();

        // Leaf node, it must be a proper string
        if(this.children.entrySet().isEmpty()) {
//            System.err.println("No children, only return node value: " + Character.toString(this.value));
            result.add(Character.toString(this.value.get()));

            return result;
        }

        for(Map.Entry<Character, Node> entry : this.children.entrySet()) {
//            System.err.println("we have children for node with value: " + Character.toString(this.value));
            Node node = entry.getValue();

            Set<String> childStrings = node.getStrings();
            for (String childString : childStrings) {
                String toAdd = Character.toString(this.value.get()) + childString;
                result.add(toAdd);
//                System.err.println("Adding : " + toAdd);
            }

            // If the node is ending, add an extra entry
            if(this.isEnding) {
//                System.err.println("Node is ending with value: " + Character.toString(this.value));
                String toAdd = Character.toString(this.value.get());
                result.add(toAdd);
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

        children.putIfAbsent(c0, new Node(Optional.of(c0), false));
        Node child = children.get(c0);
        if(s.length() == 1) {
            child.isEnding = true;  // The string ends here, so mark it as a valid ending
        } else {
            String ss = s.substring(1);
//            System.err.println("Adding " + ss + " to: " + c0);
            child.add(ss);
        }
    }
}
