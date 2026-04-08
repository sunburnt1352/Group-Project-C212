
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


    Random random = new Random();

    int score = 0;

    gameMenu() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight/10);
        frame.add(textPanel);

        JButton button1 = new JButton("Game Mode 1");
        //button1.setAlignmentY(40); button1.setAlignmentX(40);
        button1.setBounds(((boardWidth/2)-(boardWidth/10) ), ( (boardHeight-(4*(boardHeight/5)))), boardWidth/5, boardHeight/10);
        button1.setBackground(Color.green);
        frame.add(button1);

        JButton button2 = new JButton("Game Mode 2");
        button2.setBounds(((boardWidth/2)-(boardWidth/10) ), ( (boardHeight-(3*(boardHeight/5)))), boardWidth/5, boardHeight/10);
        button2.setBackground(Color.blue);
        frame.add(button2);




        




        frame.setVisible(true);
    }
}


