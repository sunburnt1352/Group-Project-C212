import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class gameMenu {
    int boardWidth = 800;
    int boardHeight = 700; //50 for the text panel on top

    JFrame frame = new JFrame("Learn Sign Language!");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    Collection<JComponent> constantItems= new ArrayList<>();
    Collection<JComponent> nonConstantItems= new ArrayList<>();
    JButton returnToMenu= new JButton("Return to Menu");

    /**
     * Constructor for the starting menu; creates the frame
     */
    gameMenu() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);



        //JButton returnToMenu = new JButton("Return to Menu");
        returnToMenu.setBounds(20, 20, (boardHeight / 4), (boardWidth / 10));
        returnToMenu.setBackground(Color.ORANGE);
        returnToMenu.setOpaque(true);
        nonConstantItems.add(returnToMenu);

        menuMode();

        frame.setVisible(true);
    }


    void menuMode() {
        //frame.repaint();
        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds((boardWidth/2)-(boardWidth/10), 0, (boardWidth/5), boardHeight / 10);
        frame.add(textPanel);
        constantItems.add(textPanel);

        JLabel copyright = new JLabel();
        copyright.setFont(new Font("Bradley Hand ITC", Font.PLAIN,14));
        copyright.setHorizontalAlignment(JLabel.CENTER);
        copyright.setText("Copyright 2026 by Charrah McNider");
        copyright.setOpaque(true);
        JPanel copyPanel = new JPanel();
        copyPanel.setLayout(new BorderLayout());
        copyPanel.add(copyright);
        copyPanel.setBounds(0,(int) (boardHeight*0.85),boardWidth,(int) (boardHeight/10.0));
        frame.add(copyPanel);
        constantItems.add(copyPanel);

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

        nonConstantItems.add(button1);
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
        nonConstantItems.add(button2);
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
        //textPanel.setBounds((boardWidth/2)-(boardWidth/10), 0, (boardWidth/5), boardHeight / 10);
        frame.add(textPanel);


        gameMode1 gameInstance = new gameMode1();

        addReturnToMenu(gameInstance);

        runGame1(gameInstance);
        // https://math.hws.edu/eck/cs124/javanotes3/c6/s5.html

        frame.repaint();
        frame.setVisible(true);

    }


    void runGame1(gameMode1 gameInstance){
        ImageIcon currLetter = gameInstance.getRandomLetter();
        JButton letter = new JButton("Begin");
        letter.setBounds(( (boardWidth/2)-(boardWidth/10)),  ( (boardHeight/3)-(boardHeight/20)), boardWidth/5, boardHeight/10);

        nonConstantItems.add(letter);

        //TODO: not the prettiest code, but it works, need to figure out how to make it look better
        letter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(letter.hasFocus()) {
                    //System.out.println("has focus");
                    letter.setIcon(currLetter);
                    //TODO: Fix annoying blue bit left of the icon
                    letter.setBounds(boardWidth/2 - (int) (0.5*currLetter.getIconWidth()),
                            (boardHeight/2 - currLetter.getIconHeight()),
                            currLetter.getIconWidth(), currLetter.getIconHeight());

                    letter.addKeyListener(new KeyListener() {

                        // https://www.geeksforgeeks.org/java/java-keylistener-in-awt/

                        /**
                         * Check whether a typed key was correct; change images; increment score
                         * and mistakes; end game
                         *
                         * @param e keystroke
                         */
                        @Override
                        public void keyTyped(KeyEvent e) {

                            //if it is the correct letter
                            if (Character.toLowerCase(e.getKeyChar()) == gameInstance.getCurrLetter()) {
                                gameInstance.SCORE += 10;
                            }

                            //if it is not the correct letter, adds a mistake
                            else {
                                gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                            }

                            //if it is 3 mistakes, run game over code
                            if (gameInstance.getMistakes() >= 3) {
                                gameOver(gameInstance);
                            }

                            ImageIcon currLetter = gameInstance.getRandomLetter();
                            letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                                    (boardHeight / 2 - currLetter.getIconHeight()),
                                    currLetter.getIconWidth(), currLetter.getIconHeight());
                            letter.setIcon(currLetter);
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                        }
                    });
                }
            }
        });
        frame.add(letter);
        frame.setFocusable(true);
    }

    void gameOver(gameModes gameInstance){
        //System.out.println(nonConstantItems);
        nonConstantItems.forEach(x -> frame.remove(x));

        frame.repaint();
        if(gameInstance.getMistakes()!=0) {
            gameInstance.writeScore();
            gameOverScreen(gameInstance);
        }
        else {
            menuMode();
        }
    }

    void gameOverScreen(gameModes gameInstance){

//        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
//        textLabel.setHorizontalAlignment(JLabel.CENTER);
//        textLabel.setText("Menu");
//        textLabel.setOpaque(true);
//
//        textPanel.setLayout(new BorderLayout());
//        textPanel.add(textLabel);
//        textPanel.setBounds((boardWidth/2)-(boardWidth/10), 0, (boardWidth/5), boardHeight / 10);
//        frame.add(textPanel);
//        constantItems.add(textPanel);

        Collection<JComponent> gameOverParts= new ArrayList<>();
        JPanel gameOverStats= new JPanel();
        JLabel gameOverMsg= new JLabel("Game Over");
        gameOverParts.add(gameOverStats);
        gameOverParts.add(gameOverMsg);

        gameOverMsg.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        gameOverMsg.setHorizontalAlignment(JLabel.CENTER);
        gameOverMsg.setText("Game Over");
        //gameOverStats.setBackground(Color.red);
        gameOverMsg.setOpaque(true);

        gameOverStats.setLayout(new BorderLayout());
        gameOverStats.add(gameOverMsg);
        gameOverStats.setBounds((boardWidth/2)-(boardWidth/10), (boardHeight/5), (boardWidth/5), boardHeight / 10);
        frame.add(gameOverStats);

        JButton menu= new JButton("Menu");
        menu.setBounds(boardWidth/3, boardHeight/2, (boardWidth/10),(boardHeight/10));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverParts.forEach(x -> frame.remove(x));
                frame.repaint();
                menuMode();
            }
        });


        JButton playAgain= new JButton("Play Again?");
        playAgain.setBounds(boardWidth/2, boardHeight/2, (boardWidth/10),(boardHeight/10));
        gameOverParts.add(menu);
        gameOverParts.add(playAgain);

        nonConstantItems.addAll(gameOverParts);
        gameOverParts.forEach(x -> frame.add(x));
        
    }

    void addReturnToMenu(gameModes gameInstance){
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver(gameInstance);
            }
        });
        frame.add(returnToMenu);
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
        //textPanel.setBounds(0, 0, boardWidth, boardHeight / 10);
        frame.add(textPanel);

        gameMode2 gameInstance = new gameMode2();

        addReturnToMenu(gameInstance);

        java.util.List<String> word = gameInstance.randomWord();
        java.util.List<Character> letters = new ArrayList<>();
        JButton letter = new JButton();
        for (String s : word) {
            letters.add(Character.toUpperCase(s.charAt(4)));
            ImageIcon currLetter = new ImageIcon(getClass().getResource(s));
            letter.setIcon(currLetter);
            letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                    (boardHeight / 2 - currLetter.getIconHeight()),
                    currLetter.getIconWidth(), currLetter.getIconHeight());
            //TODO: Figure out why icon is not updating
            try {
                Thread.sleep(500);          // wait 0.5 seconds between letters
//                System.out.println("waited");
            } catch (InterruptedException ex) {
//                System.out.println("interrupted");
                Thread.currentThread().interrupt();
            }
        }
        ImageIcon question = new ImageIcon(getClass().getResource("question.png"));
        letter.setIcon(question);
        letter.setBounds(boardWidth / 2 - (int) (0.5 * question.getIconWidth()),
                (boardHeight / 2 - question.getIconHeight()),
                question.getIconWidth(), question.getIconHeight());
        java.util.List<Character> typed = new LinkedList<>();
        letter.addKeyListener(new KeyListener() {

            /**
             * Check whether a typed key was correct; change images; increment score
             *    and mistakes; end game
             * @param e keystroke
             */
            @Override
            public void keyTyped(KeyEvent e) {
                typed.add(e.getKeyChar());
                //System.out.println(gameInstance.getMistakes());
                if (typed.equals(letters)) {
                    gameInstance.SCORE += 10 * letters.size();
                    typed.clear();
                }
                else if (typed.size() > 5) {
//                    System.out.println("had a mistake");
                    gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                    typed.clear();
                }

                if (gameInstance.getMistakes() >= 3) {
                    gameOver(gameInstance);
                }
                java.util.List<String> word = gameInstance.randomWord();
                java.util.List<Character> letters = new ArrayList<>();
                for (String s : word) {
                    letters.add(Character.toUpperCase(s.charAt(4)));
                    ImageIcon currLetter = new ImageIcon(getClass().getResource(s));
                    letter.setIcon(currLetter);
                    letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                            (boardHeight / 2 - currLetter.getIconHeight()),
                            currLetter.getIconWidth(), currLetter.getIconHeight());
                    //TODO: Figure out why icon is not changing
                    try {
                        Thread.sleep(500);          // wait 0.5 seconds between letters
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                letter.setIcon(question);
                letter.setBounds(boardWidth / 2 - (int) (0.5 * question.getIconWidth()),
                        (boardHeight / 2 - question.getIconHeight()),
                        question.getIconWidth(), question.getIconHeight());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

        });

        frame.add(letter);
        frame.setFocusable(true);
        frame.repaint();
        frame.setVisible(true);

    }
}
