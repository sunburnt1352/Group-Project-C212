import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public final class gameMode2 extends gameModes {

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
//            System.out.println(res);
            return res;
        }
        catch (IOException ex) { throw new RuntimeException("Could not find dictionary.txt. Check your file structure!"); }
    }

    /**
     * Writes the final score to a file
     */
    @Override
    public void writeScore() {
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
            throw new RuntimeException("An error occurred writing your score file: "+ex);     // throw exception if error occurs
        }
    }

    /**
     * Getter for the score
     * @return score
     */
    @Override
    public int getScore() {
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
    @Override
    public int getMistakes() {
        return this.MISTAKES;
    }

    /**
     * Setter for the mistakes
     */
    @Override
    public void setMistakes(int n) {
        this.MISTAKES = n;
    }

    /**
     * Gets an ImageIcon from a given String
     * @param url file name
     * @return corresponding ImageIcon
     */
    ImageIcon getLetter(String url) {
        return new ImageIcon(getClass().getResource(url));
    }

    /**
     * Takes the current word and gets a list of file names for its letters
     * @return list of file names
     */
    List<String> getLetterFiles() {
        String word = this.getCurrWord();
        List<String> res = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast("asl-"+word.charAt(i)+".png");
        }
        return res;
    }
}
