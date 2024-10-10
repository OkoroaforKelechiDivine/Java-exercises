package org.example.autocomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutocompleteSystem {
    private final List<String> dictionary;

    public AutocompleteSystem() {
        this.dictionary = Arrays.asList("apple", "app", "apricot", "banana", "berry", "blackberry", "blueberry", "grape", "orange");
    }

    public int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int x = 0; x <= a.length(); x++) {
            for (int y = 0; y <= b.length(); y++) {
                if (x == 0) {
                    dp[x][y] = y; // If a is empty
                } else if (y == 0) {
                    dp[x][y] = x; // If b is empty
                } else if (a.charAt(x - 1) == b.charAt(y - 1)) {
                    dp[x][y] = dp[x - 1][y - 1];
                } else {
                    dp[x][y] = 1 + Math.min(dp[x - 1][y], Math.min(dp[x][y - 1], dp[x - 1][y - 1])); // Minimum of delete, insert, or replace
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    public List<String> getSuggestions(String input) {
        List<String> suggestions = new ArrayList<>();
        for (String word : dictionary) {
            if (levenshteinDistance(input, word) <= 2) { // Allow a distance of 2 for suggestions
                suggestions.add(word);
            }
        }
        return suggestions;
    }

    public static void main(String[] args) {
        AutocompleteSystem autocomplete = new AutocompleteSystem();
        String userInput = "app"; // Sample user input
        List<String> suggestions = autocomplete.getSuggestions(userInput);

        System.out.println("Suggestions for '" + userInput + "': " + suggestions);
    }
}
