import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public static void main(String[] args) {
        String text = "Яблоко, яблока, яблоко и еще одно яблоко";
        Map<String, Integer> result = countWords(text);
        System.out.println(result);
    }

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();
        if (text == null || text.isEmpty()) {
            return wordCount;
        }
        String[] words = text.split("[^\\p{L}]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                String normalizedWord = word.toLowerCase().replaceAll("[^\\p{L}]", "");
                wordCount.put(normalizedWord, wordCount.getOrDefault(normalizedWord, 0) + 1);
            }
        }
        return wordCount;
    }

}