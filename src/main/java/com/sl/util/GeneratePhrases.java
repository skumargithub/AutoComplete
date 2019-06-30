package com.sl.util;

import java.util.*;
import java.io.*;

import com.sl.Node;

public class GeneratePhrases {
    public static String generateWord(int maxLength) {
        Random random = new Random();
        int min = 0;
        int max = 25;

        int wordLength = random.nextInt(maxLength - 1 + 1) + 1;   // Each word is [1..maxLength] long

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < wordLength; ++i) {
            int generatedNum = random.nextInt(max - min + 1) + min;
            char generatedChar = Node.intToChar(generatedNum);

            sb.append(generatedChar);
        }

        return sb.toString();
    }

    public static String generatePhrase(int maxWords) {
        Random random = new Random();
        int minWords = 1;

        // How many words in this phrase?
        int numberOfWords = random.nextInt(maxWords - minWords + 1) + minWords;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfWords; ++i) {
            String generatedWord = generateWord(6);         // Each word is at most 6 long
//            System.err.println("Generated word: " + generatedWord);
            sb.append(generatedWord + " ");
        }

        // Get rid of the last space
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void writePhrases(List<String> phrases, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);

        for (String phrase : phrases) {
            fileWriter.write(phrase + "\n");
        }

        fileWriter.flush();
        fileWriter.close();
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Needed first arg: Phrases output file");
            return;
        }

        try {
            List<String> phrases = new ArrayList<>();

            Random random = new Random();
            for (int i = 0; i < 1000; ++i) { // 1000 Phrases
                int maxNumberOfWords = random.nextInt(10 - 1 + 1) + 1;   // with varying number of words [1..10]
                String phrase = generatePhrase(maxNumberOfWords);
//                System.err.println("Genrated phrase: " + phrase);

                int repeatCount = random.nextInt(4 - 1 + 1) + 1;       // Each phrase is repeated [1..4] times
                for(int count = 0; count < repeatCount; ++count) {
                    phrases.add(phrase);
                }
            }

            Collections.sort(phrases);
            writePhrases(phrases, args[0]);
        } catch(Throwable t) {
            System.err.println("Exception: " + t.getMessage());
            t.printStackTrace(System.err);
        }
    }
}
