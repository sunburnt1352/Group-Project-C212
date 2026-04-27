import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class gameMenu {
    int boardWidth = 800;
    int boardHeight = 700; //50 for the text panel on top

    JFrame frame = new JFrame("Learn Sign Language!");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    /**
     * Constructor for the starting menu; creates the frame
     */
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

        JButton button1 = new JButton("Letter Mode");
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

        JButton button2 = new JButton("Word Mode");
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


    /**
     * Runs gameType1
     */
    void gameType1() {


        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Letter Mode");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);


        JButton menu = new JButton("Return to Menu");
        menu.setBounds(20, 20, (boardHeight / 4), (boardWidth / 10));
        menu.setBackground(Color.ORANGE);
        menu.setOpaque(true);
        //TODO: Bring menu to front of textLabel

        gameMode1 gameInstance = new gameMode1();

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInstance.writeScore();
                frame.getContentPane().removeAll();
                menuMode();
            }
        });
        frame.add(menu);

        ImageIcon currLetter = gameInstance.getRandomLetter();
        JButton letter = new JButton();
        letter.setIcon(currLetter);
        letter.setBounds(boardWidth/2 - (int) (0.5*currLetter.getIconWidth()),
                (boardHeight/2 - currLetter.getIconHeight()),
                currLetter.getIconWidth(), currLetter.getIconHeight());
        letter.addKeyListener(new KeyListener() {

            // https://www.geeksforgeeks.org/java/java-keylistener-in-awt/

            /**
             * Check whether a typed key was correct; change images; increment score
             *    and mistakes; end game
             * @param e keystroke
             */
            @Override
            public void keyTyped(KeyEvent e) {
//                System.out.println(e.getKeyChar()+ " | "+gameInstance.getCurrLetter());
                //System.out.println(gameInstance.getMistakes());
                if (Character.toLowerCase(e.getKeyChar()) == gameInstance.getCurrLetter()) {
                    gameInstance.SCORE += 10;
                }
                else {
//                    System.out.println("had a mistake");
                    gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                }

                if (gameInstance.getMistakes() >= 3) {
                    // Write the score to a file
                    gameInstance.writeScore();
                    frame.getContentPane().removeAll();
                    menuMode();
                }
                ImageIcon currLetter = gameInstance.getRandomLetter();
                letter.setBounds(boardWidth/2 - (int) (0.5*currLetter.getIconWidth()),
                        (boardHeight/2 - currLetter.getIconHeight()),
                        currLetter.getIconWidth(), currLetter.getIconHeight());
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


    /**
     * Runs gameType 2
     */
    void gameType2() {
        frame.repaint();

        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Word Mode");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);

        JButton menu = new JButton("Return to Menu");
        menu.setBounds(0, 0, (boardHeight / 6), (boardWidth / 10));
        menu.setBackground(Color.ORANGE);
        //TODO: Bring menu to front of textLabel

        gameMode2 gameInstance = new gameMode2();

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInstance.writeScore();
                frame.getContentPane().removeAll();
                menuMode();
            }
        });

        frame.add(menu);

    }
}
