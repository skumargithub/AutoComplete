package com.sl;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Trie {
    private Node root = new Node(Optional.empty(), 0);

    public void add(String s) {
        this.root.add(s);
    }

    public Node get(String prefix) {
        Node currentNode = this.root;
        while(!currentNode.children.isEmpty() && prefix.length() != 0) {
            Character c = prefix.charAt(0);
//            System.err.println("Getting on: " + c);
            if(!currentNode.children.containsKey(c)) {
                return null;
            }

            currentNode = currentNode.children.get(c);

            prefix = prefix.substring(1);
        }

        return currentNode;
    }

    public Set<String> getStrings(String prefix, int count) {
        Set<String> result = new TreeSet<String>();

        Node node = get(prefix);
        if(node == null) return result;

        List<Pair<String, Integer>> allStrings = node.getStrings().stream().collect(Collectors.toList());
        Collections.sort(allStrings, (o1, o2) -> o2.getRight().compareTo(o1.getRight()));
//        System.err.println("sorted result: " + allStrings);
        for(Pair<String, Integer> str : allStrings) {
            result.add(prefix.substring(0, prefix.length() - 1) + str.getLeft());
            count--;
            if(count == 0) break;
        }

        return result;
    }

    @Override
    public String toString() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        StringBuilder sb = new StringBuilder();

        Node lastChild = this.root;
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            node.toString(sb);

            if(node == lastChild) {
//                System.err.println("Hit last child");
                sb.append("\n");
            }

            for (Map.Entry<Character, Node> pair : node.children.entrySet()) {
                queue.add(pair.getValue());
                lastChild = pair.getValue();
//                if(!pair.getValue().children.isEmpty()) {
//                    System.err.println("Setting last child to: " + pair.getValue());
//                }
            }
        }

        return sb.toString();
    }

    // Sample driver program
    public static void main(String[] args) {
        try {
            System.err.println("Hello Trie!");

            Trie trie = new Trie();
            trie.add("a");
            trie.add(" ");
            trie.add("b");
            trie.add("c");
            trie.add("d");
            trie.add("z");

            trie.add("ab");
            trie.add("ac");
            trie.add("ad");
            trie.add("ae");
            trie.add("af");
            trie.add("ag");
            trie.add("a ");

            trie.add("bb");
            trie.add("bc");
            trie.add("bd");
            trie.add("be");
            trie.add("bf");
            trie.add("bg");
            trie.add("b ");

            System.err.println(trie);

            Node result = trie.get("a");
            System.err.println("Result: " + result);
            System.err.println("ResultStrings: " + result.getStrings());
        } catch(Throwable t) {
            System.err.println(t.getMessage());
            t.printStackTrace(System.err);
        }
    }
}
