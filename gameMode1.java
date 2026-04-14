import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class gameMode1 {

    private int SCORE;
    private int mistakes;

    gameMode1() {
        this.SCORE = 0;
        this.mistakes=0;
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
        return "asl-" + alphabet.get(n) + ".png";
    }

    /**
     * Writes the final score to a file
     */
    void writeScore() {
        String currTime = LocalDateTime.now().toString();
        try (FileWriter fw = new FileWriter(currTime + ".out")) {
            fw.write(currTime);
            fw.write(" - Game Mode 1 Score: " + this.SCORE);    // TODO: rename the game mode
        } catch (IOException ex) {
            throw new RuntimeException(ex);     // throw exception if error occurs
        }
    }

    ImageIcon getRandomLetter(){

        URL imagePath = (getClass().getResource(randomLetter()));
        System.out.println(imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        //BufferedImage myPicture = ImageIO.read(imagePath);

        return icon;

    }


    public int getSCORE() {
        return SCORE;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistake){
        this.mistakes=mistake;
    }
}
