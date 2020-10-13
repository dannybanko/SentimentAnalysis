import java.io.*;
import java.util.*;

public class WordBank {
    private final Set<String> positiveWords = new HashSet<>();
    private final Set<String> negativeWords = new HashSet<>();

    public void loadWords() throws FileNotFoundException {
        Scanner pos = new Scanner(new File("src/positive-words.txt"));
        while (pos.hasNext()) {
            String word = pos.next();
            positiveWords.add(word);
        }

        Scanner neg = new Scanner(new File("src/negative-words.txt"));
        while (neg.hasNext()) {
            String word = neg.next();
            negativeWords.add(word);
        }
    }

    public int getWordScore(String word) {
        if (positiveWords.contains(word)) {
            return 1;
        } else if (negativeWords.contains(word)) {
            return -1;
        } else {
            return 0;
        }
    }
}
