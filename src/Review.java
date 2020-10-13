import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Review implements Comparable<Review> {
    private final WordBank wordbank;
    private final String review;
    private int score = 0;
    private Map<String, Integer> positiveWords = new HashMap<>();
    private Map<String, Integer> negativeWords = new HashMap<>();

    Review(WordBank wordbank, String review) {
        this.wordbank = wordbank;
        this.review = review;
    }

    public int getScore() {
        return this.score;
    }

    public String getReview() {
        return this.review;
    }

    public Map<String, Integer> getPositiveWords() {
        return this.positiveWords;
    }

    public Map<String, Integer> getNegativeWords() {
        return this.negativeWords;
    }

    public void processScore() {
        Scanner sc = new Scanner(this.review);
        int reviewScore = 0;
        String current = " ";
        String prev;

        while (sc.hasNext()) {
            prev = current;
            current = sc.next();

            if (prev.equals("not") || prev.contains("n't")) {
                int originalVal = this.wordbank.getWordScore(current);
                originalVal *= -1;

                if (originalVal == -1) {
                    negativeWords.put(prev + " " + current, 1);
                    reviewScore--;
                } else if (originalVal == 1) {
                    positiveWords.put(prev + " " + current, 1);
                    reviewScore++;
                }
                reviewScore += originalVal;
            } else if (this.wordbank.getWordScore(current) != this.wordbank.getWordScore(prev) && this.wordbank.getWordScore(prev) != 0) {
                if (this.wordbank.getWordScore(prev) == -1 && this.wordbank.getWordScore(current) == 1) {
                    reviewScore += 2;
                    positiveWords.put(current, 1);
                } else if (this.wordbank.getWordScore(prev) == 1 && this.wordbank.getWordScore(current) == -1) {
                    reviewScore -= 2;
                    negativeWords.put(current, 1);
                }
            } else {
                int value = this.wordbank.getWordScore(current);
                reviewScore += value;
                if (positiveWords.containsKey(current)) {
                    positiveWords.put(current, positiveWords.get(current) + 1);
                } else if (negativeWords.containsKey(current)) {
                    negativeWords.put(current, negativeWords.get(current) + 1);
                } else if (wordbank.getWordScore(current) == 1) {
                    positiveWords.put(current, 1);
                } else if (wordbank.getWordScore(current) == -1) {
                    negativeWords.put(current, 1);
                }
            }
        }
        this.score = reviewScore;
    }

    public int compareTo(Review other) {
        return this.score - other.score;
    }
}
