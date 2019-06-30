package com.sl;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.io.*;

public class PhrasesTest {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Needed first arg: Phrases output file");
            return;
        }

        try {
            Random random = new Random();
            int min = 0;
            int max = 26;

            File file = new File(args[0]);
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < 1000; ++i) { // 1000 Phrases
                StringBuilder generatedString = new StringBuilder();

                int stringLength = random.nextInt(250 - 1 + 1) + 1;   // of varying lengths [1..250]
                for (int j = 0; j < stringLength; ++j) {
                    int generatedNum = random.nextInt(max - min + 1) + min;
                    char generatedChar = Node.intToChar(generatedNum);
                    generatedString.append(generatedChar);
                }

//            System.err.println("Generated string: " + generatedString.toString());
                fileWriter.write(generatedString.toString());
            }
        } catch(Throwable t) {
            System.err.println("Exception: " + t.getMessage());
            t.printStackTrace(System.err);
        }
    }
}
