package com.sl;

import java.io.File;
import java.util.*;

import org.apache.commons.io.*;

public class AutoComplete {
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
            System.err.println("Read lines: " + lines.size());
            Trie trie = new Trie();
            for(String line : lines) {
//                System.err.println("Read line: " + line);
                trie.add(line);
            }

            Scanner in = new Scanner(System.in);
            while(in.hasNext()) {
                String input = in.toString();
                System.err.println("input: " + input);
            }

        } catch(Throwable t) {
            System.err.println("Exception: " + t.getMessage());
            t.printStackTrace(System.err);
        }
    }
}
