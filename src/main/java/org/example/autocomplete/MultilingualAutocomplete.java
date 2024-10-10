package org.example.autocomplete;

import java.util.*;

public class MultilingualAutocomplete {
    private static final Map<String, List<String>> suggestionsMap = new HashMap<>();

    static {
        suggestionsMap.put("en", Arrays.asList("apple", "apricot", "banana", "blueberry"));
        suggestionsMap.put("es", Arrays.asList("manzana", "albaricoque", "plátano", "mora"));
        suggestionsMap.put("fr", Arrays.asList("pomme", "abricot", "banane", "myrtille"));
    }

    private static String detectLanguage(String input) {
        if (input.matches("[a-zA-Z]+")) {
            return "en"; // English
        } else if (input.matches("[áéíóúñ]+|[A-Za-z]+")) {
            return "es"; // Spanish
        } else if (input.matches("[àâçéèêëîôû]+|[A-Za-z]+")) {
            return "fr"; // French
        }
        return "en";
    }

    public static List<String> getSuggestions(String input) {
        String language = detectLanguage(input);
        List<String> allSuggestions = suggestionsMap.get(language);
        List<String> filteredSuggestions = new ArrayList<>();

        for (String suggestion : allSuggestions) {
            if (suggestion.startsWith(input)) {
                filteredSuggestions.add(suggestion);
            }
        }
        return filteredSuggestions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type a query: ");
        String userInput = scanner.nextLine();

        List<String> suggestions = getSuggestions(userInput);
        System.out.println("Suggestions: " + suggestions);

        scanner.close();
    }
}
