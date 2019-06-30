package com.sl;

import java.io.File;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.lang3.tuple.*;

public class AutoComplete {
    private static Optional<Pair<String, Integer>> parseInput(String input) {
        try {
            String[] inputs = input.split(",");
            if (inputs.length < 3) return Optional.empty();

            if (!"complete".equals(inputs[0].toLowerCase())) return Optional.empty();

            String prefix = inputs[1];
            int maxMatchCount = Integer.parseInt(inputs[2]);
            if(maxMatchCount < 0) maxMatchCount = 0;

            return Optional.of(new ImmutablePair(prefix, maxMatchCount));
        } catch(Throwable t) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.err.println("Insufficient args: args[0] = file of phrases");
                return;
            }

            File file = new File(args[0]);
            if (file.exists() && !file.canRead() && !file.isFile()) {
                System.err.println("File does not exist or unreadable");
                return;
            }

            List<String> lines = FileUtils.readLines(file, (String) null);
            System.out.println("Read lines: " + lines.size() + " from phrases file!");
            Trie trie = new Trie();
            for(String line : lines) {
//                System.err.println("Read line: " + line);
                trie.add(line);
            }

            System.out.println("Enter query: complete,prefixString,maxMatchCount");
            System.out.println("eg: complete,a,1");

            Scanner in = new Scanner(System.in);
            while(in.hasNextLine()) {
                String input = in.nextLine();
//                System.err.println("input: " + input);

                Optional<Pair<String, Integer>> cmd = parseInput(input);
                if(cmd.isPresent()) {
                    Pair<String, Integer> command = cmd.get();
                    String prefix = command.getLeft();
                    Integer maxCount = command.getRight();
//                    System.err.println("prfeix: " + prefix + ", maxCount: " + maxCount);

                    Set<String> matchStrings = trie.getStrings(prefix, maxCount);
                    StringBuilder sb = new StringBuilder();
                    for(String matchString : matchStrings) {
                        sb.append(matchString + ",");
                    }

                    // Remove that pesky comma at the end (if needed)
                    if(!matchStrings.isEmpty()) {
                        sb.deleteCharAt(sb.length() - 1);
                    }

                    System.out.println(sb);

                } else {
                    System.out.println("Invalid input!, Try again...");
                }
            }
        } catch(Throwable t) {
            System.err.println("Exception: " + t.getMessage());
            t.printStackTrace(System.err);
        }
    }
}
