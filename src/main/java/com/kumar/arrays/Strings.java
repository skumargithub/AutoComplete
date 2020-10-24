package com.kumar.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Strings {
    public static void main(String[] args) {
        System.err.println("Hello World!");

        System.err.println(uniqueWithSort("hi there".toCharArray()));
        System.err.println(uniqueWithHashMap("hi there".toCharArray()));

        System.err.println(uniqueWithSort("abcdef".toCharArray()));
        System.err.println(uniqueWithHashMap("abcdef".toCharArray()));

        System.err.println(reverse("abc".toCharArray()));
        System.err.println(reverse("abcdef".toCharArray()));

        System.err.println(isPermutation("abcdef".toCharArray(), "fedcba".toCharArray()));

        System.err.println(htmlWhiteSpace("Hi There  ".toCharArray()));
        System.err.println(htmlWhiteSpace("Mr John Smith    ".toCharArray()));

        System.err.println(compressionByCount("abc".toCharArray()));
        System.err.println(compressionByCount("aabcccccaaa".toCharArray()));
    }

    public static boolean uniqueWithSort(char[] cs) {
        Arrays.sort(cs);

        char prev = cs[0];
        for(int i = 1; i < cs.length; ++i) {
            if(prev == cs[i]) return false;

            prev = cs[i];
        }

        return true;
    }

    public static boolean uniqueWithHashMap(char[] cs) {
        HashMap<Character, Integer> hashes = new HashMap<>();

        for(int i = 0; i < cs.length; ++i) {
            Character key = cs[i];
            if(!hashes.containsKey(key)) {
                hashes.put(key, new Integer(0));
            }

            Integer value = hashes.get(key);
            if(value >= 1) return false;

            hashes.put(key, value + 1);
        }

        return true;
    }

    public static char[] reverse(char[] cs) {
        int i = 0;
        int j = cs.length - 1;
        while(i < j) {
            char tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
            i++;
            j--;
        }

        return cs;
    }

    public static boolean isPermutation(char[] cs1, char[] cs2) {
        Arrays.sort(cs1);
        Arrays.sort(cs2);

        return Arrays.equals(cs1, cs2);
    }

    public static String htmlWhiteSpace(char[] cs) {
        int lastCharIndex = cs.length - 1;
        for(; lastCharIndex >= 0; --lastCharIndex) {
            if(cs[lastCharIndex] != ' ') {
                break;
            }
        }
//        System.err.println("lastCharIndex: " + lastCharIndex);

        int currentIndex = cs.length - 1;
        while(lastCharIndex >= 0) {
            if(cs[lastCharIndex] == ' ') {
                cs[currentIndex--] = '0';
                cs[currentIndex--] = '2';
                cs[currentIndex--] = '%';
                lastCharIndex--;
            } else {
                cs[currentIndex--] = cs[lastCharIndex--];
            }
        }

        return new String(cs);
    }

    public static String compressionByCount(char[] cs) {
        char[] result = new char[cs.length * 2];
        int currentResultIndex = 0;

        for(int i = 0; i < cs.length;) {
            char currentChar = cs[i++];
            result[currentResultIndex++] = currentChar;

            int currentCharCount = 1;
            while(i < cs.length && cs[i] == currentChar) {
                currentCharCount++;
                i++;
            }

            char[] currentCharCountAsChars = new Integer(currentCharCount).toString().toCharArray();
            for(int j = 0; j < currentCharCountAsChars.length; ++j) {
                result[currentResultIndex++] = currentCharCountAsChars[j];
            }
        }

        String resultStr = new String(result);

        return currentResultIndex >= cs.length ? new String(cs) : resultStr;
    }
}
