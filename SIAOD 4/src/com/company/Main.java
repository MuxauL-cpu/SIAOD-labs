package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String preparation(String text) {
        return text.toLowerCase().trim().replaceAll("\\s+","");
    }

    private static String Sort(String text) {
        char[] chars = preparation(text).toCharArray();

        Arrays.sort(chars);
        return new String(chars);
    }

    public static boolean isValid(String text, String anagram) {
        text = preparation(text);
        anagram = preparation(anagram);

        String sortedText = Sort(text);
        String sortedAnagram = Sort(anagram);

        return sortedText.equals(sortedAnagram);
    }

    public static String inputStr() {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        return num;
    }

    public static void main(String[] args) {
	String text = inputStr();
	String anagram = inputStr();
	isValid(text, anagram);
    }
}
