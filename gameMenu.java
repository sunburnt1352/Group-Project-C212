
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class gameMenu {
    int boardWidth = 600;
    int boardHeight = 650; //50 for the text panel on top

    JFrame frame = new JFrame("Sign Language Trainer");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[] board = new JButton[9];


    Random random = new Random();

    int score = 0;

    gameMenu() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //boardPanel.setLayout(new GridLayout(3, 3));
        // boardPanel.setBackground(Color.black);
        frame.add(boardPanel);



        JButton tile = new JButton();
        tile.setAlignmentY(50);
        tile.setAlignmentX(50);
        




        frame.setVisible(true);
    }
}


