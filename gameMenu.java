import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class gameMenu {
    int boardWidth = 800;
    int boardHeight = 800; //50 for the text panel on top

    JFrame frame = new JFrame("Learn Sign Language!");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    private int score = 0;  // unnecessary?

    gameMenu() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        menuMode();
        //gameType1();

        frame.setVisible(true);
    }


    void menuMode() {
        frame.repaint();
        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);

        this.score = 0;

        JButton button1 = new JButton("Game Mode 1");
        //button1.setAlignmentY(40); button1.setAlignmentX(40);
        button1.setBounds(((boardWidth / 2) - (boardWidth / 10)), ((boardHeight - (4 * (boardHeight / 5)))), boardWidth / 5, boardHeight / 10);
        button1.setBackground(Color.pink);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                gameType1();
            }
        });

        frame.add(button1);

        JButton button2 = new JButton("Game Mode 2");
        button2.setBounds(((boardWidth / 2) - (boardWidth / 10)), ((boardHeight - (3 * (boardHeight / 5)))), boardWidth / 5, boardHeight / 10);
        button2.setBackground(Color.orange);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                gameType2();
            }
        });

        frame.add(button2);
    }


    void gameType1() {


        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Game Mode 1");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);


        JButton menu = new JButton("Return to Menu");
        menu.setBounds(0, 0, (boardHeight / 6), (boardWidth / 10));
        menu.setBackground(Color.ORANGE);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                menuMode();
            }
        });
        frame.add(menu);


        gameMode1 gameInstance = new gameMode1();

        ImageIcon currLetter = gameInstance.getRandomLetter();
        JButton letter = new JButton();
        letter.setBounds(((boardWidth / 2) - 260), ((boardHeight / 2) - 236), 260, 236);
        letter.setIcon(currLetter);
        letter.addKeyListener(new KeyListener() {

            // https://www.geeksforgeeks.org/java/java-keylistener-in-awt/

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar()+ " | "+gameInstance.getCurrLetter());
                //System.out.println(gameInstance.getMistakes());
                if (Character.toLowerCase(e.getKeyChar()) ==gameInstance.getCurrLetter()) {
//                if (Character.toLowerCase(e.getKeyChar()) ==
//                        currLetter.toString().charAt(currLetter.toString().length()-5)) {
                    gameInstance.SCORE += 10;
                }
                else {
                    System.out.println("had a mistake");
                    gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                }

                if (gameInstance.getMistakes() >= 3) {
                    // Write the score to a file
                    frame.getContentPane().removeAll();
                    gameInstance.writeScore();
                    menuMode();
                    // Need to close the icon
                }
                ImageIcon currLetter = gameInstance.getRandomLetter();
                letter.setBounds(((boardWidth / 2) - 260), ((boardHeight / 2) - 236), 260, 236);
                letter.setIcon(currLetter);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

        });
        frame.add(letter);
        frame.setFocusable(true);

        // https://math.hws.edu/eck/cs124/javanotes3/c6/s5.html

        frame.repaint();
        frame.setVisible(true);

    }


    void gameType2() {
        frame.repaint();


        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Game Mode 2");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);

        JButton menu = new JButton("Return to Menu");
        menu.setBounds(0, 0, (boardHeight / 6), (boardWidth / 10));
        menu.setBackground(Color.ORANGE);

        menu.addActionListener(new ActionListener() {  // what does this do?
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                menuMode();
            }
        });

        frame.add(menu);

    }
}
