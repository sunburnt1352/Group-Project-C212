import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class gameMode2 {

    /**
     * Pulls a random word from the dictionary
     * @return list of file names composing the word
     */
    List<String> randomWord() {
        int n = (int) (Math.random() * 100);    // random line selection
        // Dictionary size MUST stay at 100
        List<String> res = new LinkedList<>();
        Scanner scanner = new Scanner(new File("dictionary.txt"));  // TODO: fix file path exception
        for (int i = 0; i < n; i++) {
            scanner.nextLine();
        }
        String extracted = scanner.nextLine();
        for (int i = 0; i < extracted.length(); i++) {
            res.add("asl_" + extracted.charAt(i) + ".jpg");    // TODO: check file type
        }
        return res;
    }
}
