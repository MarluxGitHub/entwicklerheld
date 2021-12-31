package de.entwicklerheld.anagram;

import java.util.Arrays;

public class Anagram {

    public static boolean isAnagram(String firstWord, String secondWord) {
        firstWord = firstWord.replaceAll("\\s", "");
        secondWord = secondWord.replaceAll("\\s", "");

        // Check if both length matches
        if(firstWord.length() != secondWord.length())
            return false;
        else
        {
            // Convert both Strings into lower case and into Character Array
            char[] arr1 = firstWord.toLowerCase().toCharArray();
            char[] arr2 = secondWord.toLowerCase().toCharArray();

            // Sort both Character Array
            Arrays.sort(arr1);
            Arrays.sort(arr2);

            // Check if both arrays are equal
            return (Arrays.equals(arr1, arr2));
        }
    }
}