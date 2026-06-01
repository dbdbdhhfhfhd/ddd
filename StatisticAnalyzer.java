import java.util.HashMap;
import java.util.Map;

public class StatisticAnalyzer {
    public int findKey(String encryptedText) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : encryptedText.toCharArray()) {
            if (Alphabet.CHARS.indexOf(ch) != -1) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }

        char mostFrequentChar = ' ';
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentChar = entry.getKey();
            }
        }

        int encryptedSpaceIndex = Alphabet.CHARS.indexOf(mostFrequentChar);
        int realSpaceIndex = Alphabet.CHARS.indexOf(' ');
        int len = Alphabet.CHARS.length();

        return (encryptedSpaceIndex - realSpaceIndex + len) % len;
    }
}
