import java.util.List;
import java.util.stream.Stream;

public class gameMode1 {

    /**
     * Pulls a random finger sign
     * @return file name
     */
    String randomLetter() {
        int n = (int) (Math.random() * 100);
        List<Character> alphabet = Stream.of(
                'a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z').toList();
        return "asl-" + alphabet.get(n) + ".png";
    }
}
