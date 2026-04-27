import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class gameMode2 {

    int SCORE;
    private String currWord;
    private int MISTAKES;

    gameMode2() { this.SCORE = 0; this.MISTAKES = 0; }

    /**
     * Pulls a random word from the dictionary
     * @return list of file names composing the word
     */
    List<String> randomWord() {
        int n = (int) (Math.random() * 100);    // random line selection
        // Dictionary size must stay at 100
        List<String> res = new LinkedList<>();
//        System.out.println(n);
        try (BufferedReader br = new BufferedReader(new FileReader(
                "dictionary.txt"))) {
            int i = 0;
            while (i < n) {
                br.readLine();
                i++;
            }
            String extracted = br.readLine();
            currWord = extracted;
            for (int j = 0; j < extracted.length(); j++) {
                res.add("asl-" + extracted.charAt(j) + ".png");
            }
            return res;
        }
        catch (IOException ex) { throw new RuntimeException(ex); }

//        Scanner scanner = new Scanner(new File("dictionary.txt").getAbsolutePath());
//        for (int i = 0; i < n; i++) {
//            System.out.println("scanning");
//            scanner.nextLine();
//        }
    }

    /**
     * Writes the final score to a file
     */
    void writeScore() {
        List<String> lines = new LinkedList<>();
        try {                                       // check if any data already exist
            lines = Files.readAllLines(Path.of(
                    "ASL_Trainer_" + LocalDate.now() + ".out"));
        } catch (IOException ex) {}
        try (FileWriter fw = new FileWriter("ASL_Trainer_" + LocalDate.now() + ".out", true)) {
            if (!lines.isEmpty()) { fw.write("\n"); }   // write new line if there is already data
            fw.write(LocalDate.now() + " " +
                    LocalTime.now().toString().substring(0, 8)
                    + " Word Mode Score - " + this.SCORE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);     // throw exception if error occurs
        }
    }

    /**
     * Getter for the score
     * @return score
     */
    public int getSCORE() {
        return SCORE;
    }

    /**
     * Getter for the current word
     * @return the current word
     */
    public String getCurrWord() {
        return currWord;
    }

    /**
     * Getter for the mistakes
     * @return number of mistakes made
     */
    public int getMistakes() {
        return this.MISTAKES;
    }

    /**
     * Setter for the mistakes
     */
    public void setMistakes(int n) {
        this.MISTAKES = n;
    }
}
