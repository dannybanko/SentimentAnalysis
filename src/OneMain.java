import java.io.FileNotFoundException;

public class OneMain {

    public static void main(String[] args) throws FileNotFoundException {
        WordBank wordbank = new WordBank();
        wordbank.loadWords();

        Review single = new Review(wordbank, "kolya is one of the richest films i've seen in some time . \n" +
                "zdenek sverak plays a confirmed old bachelor ( who's likely to remain so ) , who finds his life as a czech cellist increasingly impacted by the five-year old boy that he's taking care of . \n" +
                "though it ends rather abruptly-- and i'm whining , 'cause i wanted to spend more time with these characters-- the acting , writing , and production values are as high as , if not higher than , comparable american dramas . \n" +
                "this father-and-son delight-- sverak also wrote the script , while his son , jan , directed-- won a golden globe for best foreign language film and , a couple days after i saw it , walked away an oscar . \n" +
                "in czech and russian , with english subtitles . not good");
        single.processScore();

        String review = single.getReview();
        System.out.println(review);
        System.out.println("Review score is: " + single.getScore());
        System.out.println("Positive Words:");
        System.out.println(single.getPositiveWords());
        System.out.println("Negative Words:");
        System.out.println(single.getNegativeWords());

    }
}
