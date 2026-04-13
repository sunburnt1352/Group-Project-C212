import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class gameMode2 {

    private int SCORE;

    gameMode2() { this.SCORE = 0; }

    /**
     * Pulls a random word from the dictionary
     * @return list of file names composing the word
     */
    List<String> randomWord() {
        int n = (int) (Math.random() * 100);    // random line selection
        // Dictionary size must stay at 100
        List<String> res = new LinkedList<>();


        Scanner scanner = new Scanner(new File("dictionary.txt").getAbsolutePath());
        for (int i = 0; i < n; i++) {
            scanner.nextLine();
        }
        String extracted = scanner.nextLine();
        scanner.close();
        for (int i = 0; i < extracted.length(); i++) {
            res.add("asl-" + extracted.charAt(i) + ".png");
        }
        return res;
    }

    /**
     * Writes the final score to a file
     */
    void writeScore() {
        String currTime = LocalDateTime.now().toString();
        try (FileWriter fw = new FileWriter(currTime + ".out")) {
            fw.write(currTime);
            fw.write(" - Game Mode 2 Score: " + this.SCORE);    // TODO: rename the game mode
        } catch (IOException ex) {
            throw new RuntimeException(ex);     // throw exception if error occurs
        }
    }
}
