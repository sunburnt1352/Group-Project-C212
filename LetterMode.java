import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public final class LetterMode implements IGameInstance {

    private int SCORE;
    private char currLetter;
    private int MISTAKES;

    LetterMode() {
        this.SCORE = 0;
        this.MISTAKES = 0;
    }

    /**
     * Pulls a random finger sign
     * @return file name
     */
    String randomLetter() {
        int n = (int) (Math.random() * 26);
        List<Character> alphabet = Stream.of(
                'a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z').toList();
        this.currLetter=(alphabet.get(n));
        return "resources/asl-" + alphabet.get(n) + ".png";
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
                    + " Letter Mode Score - " + this.SCORE);
        } catch (IOException ex) {
            throw new RuntimeException("An error occurred writing your score file: "+ex);     // throw exception if error occurs
        }
    }

    /**
     * Gets a random ImageIcon
     * @return ImageIcon
     */
    ImageIcon getRandomLetter(){
        URL imagePath = getClass().getResource(randomLetter());
        if (imagePath == null) { throw new RuntimeException("Could not find a resource"); }
        return new ImageIcon(imagePath);
    }

    /**
     * Getter for the score
     * @return score
     */
    @Override
    public int getScore() {
        return this.SCORE;
    }

    /**
     * Sets the score
     * @param score score to be set
     */
    @Override
    public void setScore(int score) {
        this.SCORE = score;
    }

    /**
     * Getter for the current character
     * @return the current character
     */
    public char getCurrLetter() {
        return this.currLetter;
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
}
