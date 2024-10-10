package org.example.autocomplete;

import java.util.*;

public class RankingFilteringAutocomplete {
    private static final Map<String, Integer> suggestionsMap = new HashMap<>();

    static {
        suggestionsMap.put("apple", 100);
        suggestionsMap.put("apricot", 50);
        suggestionsMap.put("banana", 80);
        suggestionsMap.put("blueberry", 90);
        suggestionsMap.put("mango", 60);
        suggestionsMap.put("melon", 70);
        suggestionsMap.put("kiwi", 40);
    }

    public static List<String> getRankedSuggestions(String input) {
        List<Map.Entry<String, Integer>> suggestionList = new ArrayList<>(suggestionsMap.entrySet());
        suggestionList.removeIf(entry -> !entry.getKey().startsWith(input));
        suggestionList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> rankedSuggestions = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : suggestionList) {
            rankedSuggestions.add(entry.getKey());
        }
        return rankedSuggestions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type a query: ");
        String userInput = scanner.nextLine();

        List<String> suggestions = getRankedSuggestions(userInput);
        System.out.println("Ranked Suggestions: " + suggestions);

        scanner.close();
    }
}
