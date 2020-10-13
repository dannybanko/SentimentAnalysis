import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BatchMain {

    public static void main(String[] args) throws FileNotFoundException {
        List<Review> posReviews = new ArrayList<>();
        List<Review> negReviews = new ArrayList<>();

        WordBank wordbank = new WordBank();
        wordbank.loadWords();

        File folder = new File("src/review_polarity/txt_sentoken/pos");
        for (File file : folder.listFiles()) {
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();

            while (scanner.hasNext()) {
                String word = scanner.next();
                sb.append(word + " ");
            }

            Review review = new Review(wordbank, sb.toString());
            review.processScore();
            posReviews.add(review);
        }

        File folder2 = new File("src/review_polarity/txt_sentoken/neg");
        for (File file : folder2.listFiles()) {
            Scanner scanner2 = new Scanner(file);
            StringBuilder sb2 = new StringBuilder();

            while (scanner2.hasNext()) {
                String word = scanner2.next();
                sb2.append(word + " ");
            }

            Review review = new Review(wordbank, sb2.toString());
            review.processScore();
            negReviews.add(review);
        }

        Collections.sort(posReviews);
        Collections.sort(negReviews);

        int posScore = 0;
        int negScore = 0;
        int neutScore = 0;

        for (Review review : posReviews) {
            if (review.getScore() > 0) {
               posScore++;
            } else if (review.getScore() == 0) {
                neutScore++;
            }
        }

        for (Review review2 : negReviews) {
            if (review2.getScore() < 0) {
                negScore++;
            } else if (review2.getScore() == 0) {
                neutScore++;
            }
        }

        System.out.println("Positive: " + posScore + " " + (posScore / 1000.0) * 100 + "%");
        System.out.println("Negative: " + negScore + " " + (negScore / 1000.0) * 100 + "%");
        System.out.println("Neutral: " + neutScore + " " + (neutScore / (1000.0) * 100 + "%"));
        System.out.println();
        System.out.println("Here are the 3 worst reviews");

        Review worst = negReviews.get(0);
        Review secWorst = negReviews.get(1);
        Review thirdWorst = negReviews.get(2);

        System.out.println("Score: " + worst.getScore());
        System.out.println("Review: \n" + worst.getReview());
        System.out.println();
        System.out.println("Score: " + secWorst.getScore());
        System.out.println("Review : \n" + secWorst.getReview());
        System.out.println();
        System.out.println("Score: " + thirdWorst.getScore());
        System.out.println("Review: \n" + thirdWorst.getReview());
        System.out.println();

        System.out.println("Here are the 3 best reviews");

        Review best = posReviews.get(posReviews.size() - 1);
        Review secBest = posReviews.get(posReviews.size() - 2);
        Review thirdBest = posReviews.get(posReviews.size() - 3);

        System.out.println("Score: " + best.getScore());
        System.out.println("Review: \n" + best.getReview());
        System.out.println();
        System.out.println("Score: " + secBest.getScore());
        System.out.println("Review : \n" + secBest.getReview());
        System.out.println();
        System.out.println("Score: " + thirdBest.getScore());
        System.out.println("Review: \n" + thirdBest.getReview());
    }
}
