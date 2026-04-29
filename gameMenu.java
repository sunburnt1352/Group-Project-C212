import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class gameMenu {
    int boardWidth = 800;
    int boardHeight = 700; //50 for the text panel on top

    JFrame frame = new JFrame("Learn Sign Language!");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    Collection<JComponent> constantItems = new ArrayList<>();
    Collection<JComponent> nonConstantItems = new ArrayList<>();
    JButton returnToMenu = new JButton("Return to Menu");

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


    /**
     * Create the menu with header, copyright, and game mode buttons
     */
    void menuMode() {
        //frame.repaint();
        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds((boardWidth / 2) - (boardWidth / 10), 0, (boardWidth / 5), boardHeight / 10);
        frame.add(textPanel);
        constantItems.add(textPanel);

        JLabel copyright = new JLabel();
        copyright.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 14));
        copyright.setHorizontalAlignment(JLabel.CENTER);
        copyright.setText("Copyright 2026 by Charrah McNider");
        copyright.setOpaque(true);
        JPanel copyPanel = new JPanel();
        copyPanel.setLayout(new BorderLayout());
        copyPanel.add(copyright);
        copyPanel.setBounds(0, (int) (boardHeight * 0.85), boardWidth, (int) (boardHeight / 10.0));
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
     * Builds the header and menu button for gameType1; calls runGame1()
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

    /**
     * Begin button and mechanics for gameMode1
     *
     * @param gameInstance a game
     */
    void runGame1(gameMode1 gameInstance) {

        //System.out.println("ran top");
        JLabel mistakeCounter = new JLabel("Mistakes");
        mistakeCounter.setText("Mistakes: " + gameInstance.getMistakes());
        mistakeCounter.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        mistakeCounter.setHorizontalAlignment(JLabel.CENTER);
        mistakeCounter.setOpaque(true);
        mistakeCounter.setBounds((boardWidth - (boardWidth / 3)), 0, (boardWidth / 3), boardHeight / 10);
        nonConstantItems.add(mistakeCounter);

        frame.add(mistakeCounter);


        JLabel explainMistake = new JLabel();
        explainMistake.setText("");
        explainMistake.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 20));
        explainMistake.setHorizontalAlignment(JLabel.CENTER);
        explainMistake.setOpaque(true);
        explainMistake.setBounds(boardWidth / 4,
                (boardHeight / 2),
                boardWidth / 2, boardHeight / 8);
        nonConstantItems.add(explainMistake);
        frame.add(explainMistake);


        ImageIcon currLetter = gameInstance.getRandomLetter();
        JButton letter = new JButton("Begin");
        letter.setBackground(Color.PINK);
        letter.setBounds(boardWidth / 2 - 50, boardHeight / 2 - 100, 100, 100);

        nonConstantItems.add(letter);


        letter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (letter.hasFocus()) {
//                    letter.setBackground(null);
                    //System.out.println("has focus");
                    letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                            (boardHeight / 2 - currLetter.getIconHeight()),
                            currLetter.getIconWidth(), currLetter.getIconHeight());
                    letter.setIcon(currLetter);
                    letter.setText(null);


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
                                ImageIcon currLetter = gameInstance.getRandomLetter();
                                changeImage(currLetter, letter);
                            }

                            //if it is not the correct letter, adds a mistake
                            else {
                                gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                                mistakeCounter.setText("Mistakes: " + gameInstance.getMistakes());

                                explainMistake.setText("Wrong, letter was: " + Character.toUpperCase(gameInstance.getCurrLetter()));

                                explainMistake.setVisible(true);
                                frame.add(explainMistake);


                                //System.out.println("before");
                                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                                executorService.schedule(() -> {
                                    explainMistake.setText(null);
                                    explainMistake.setVisible(false);

                                    ImageIcon currLetter = gameInstance.getRandomLetter();
                                    changeImage(currLetter, letter);

                                    if (gameInstance.getMistakes() >= 3) {
                                        gameOver(gameInstance);
                                    }

                                }, 3, TimeUnit.SECONDS);

                            }

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


    void changeImage(ImageIcon currLetter, JButton letter) {
        letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                (boardHeight / 2 - currLetter.getIconHeight()),
                currLetter.getIconWidth(), currLetter.getIconHeight());
        letter.setIcon(currLetter);

    }


    void gameType2() {
//        frame.repaint();

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

        JButton letter = new JButton();
        nonConstantItems.add(letter);
        final java.util.List<Character>[] letters = new java.util.List[]{newWord(gameInstance)};
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
//                System.out.println(typed);
//                System.out.println(letters[0]);
//                System.out.println(gameInstance.getMistakes());
                if (typed.equals(letters[0])) {
                    gameInstance.SCORE += 10 * letters[0].size();
                    typed.clear();
                    letters[0] = newWord(gameInstance);
                } else if (!equalSoFar(typed, letters[0])) {
//                    System.out.println("had a mistake");
                    gameInstance.setMistakes(gameInstance.getMistakes() + 1);
                    typed.clear();
                    if (gameInstance.getMistakes() < 3) {
                        letters[0] = newWord(gameInstance);
                    }
                }
                if (gameInstance.getMistakes() >= 3) {
                    gameOver(gameInstance);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        frame.add(letter);
        frame.setFocusable(true);
        frame.repaint();
        frame.setVisible(true);

    }

    /**
     * Flashes a new word on the screen
     *
     * @param gameInstance a game
     *                     //     * @param letter icon being changed
     * @return list of lowercase characters in the word
     */
    java.util.List<Character> newWord(gameMode2 gameInstance) {
        JButton letter = new JButton();
        java.util.List<String> word = gameInstance.randomWord();
        java.util.List<Character> letters = new ArrayList<>();

        for (String s : word) {
            letters.add(s.charAt(4));
            if (getClass().getResource(s) == null) {
                throw new RuntimeException("Could not find " + s + ". Check your file structure!");
            }
//            ImageIcon currLetter = new ImageIcon(getClass().getResource(s));
//            letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
//                    (boardHeight / 2 - currLetter.getIconHeight()),
//                    currLetter.getIconWidth(), currLetter.getIconHeight());
//            letter.setIcon(currLetter);

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(() -> {
                ImageIcon currLetter = new ImageIcon(getClass().getResource(s));
                letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                        (boardHeight / 2 - currLetter.getIconHeight()),
                        currLetter.getIconWidth(), currLetter.getIconHeight());
                letter.setIcon(currLetter);
            }, 500, TimeUnit.MILLISECONDS);

//            System.out.println(letter.getIcon());
//            frame.add(letter);
//            frame.repaint();
            //TODO: Figure out why icons aren't showing
//            try {
//                Thread.sleep(500);          // wait 0.5 seconds between letters
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
        }
        if (getClass().getResource("question.png") == null) {
            throw new RuntimeException("Could not find question.png. Check your file structure!");
        }

        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        executorService2.schedule(() -> {
            ImageIcon question = new ImageIcon(getClass().getResource("question.png"));
            letter.setIcon(question);
            letter.setBounds(boardWidth / 2 - (int) (0.5 * question.getIconWidth()),
                    (boardHeight / 2 - question.getIconHeight()),
                    question.getIconWidth(), question.getIconHeight());
        }, 500, TimeUnit.MILLISECONDS);
        return letters;
    }

    /**
     * Checks whether a smaller list matches the elements of a bigger list so far
     *
     * @param ls1 smaller list
     * @param ls2 bigger or equal list
     * @param <T> any type
     * @return whether all elements of ls1 are in ls2 in order
     */
    <T> boolean equalSoFar(java.util.List<T> ls1, java.util.List<T> ls2) {
        for (int i = 0; i < ls1.size(); i++) {
            if (ls1.get(i) != ls2.get(i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Mechanics for ending any game mode
     *
     * @param gameInstance a game
     */
    void gameOver(gameModes gameInstance) {
        //System.out.println(nonConstantItems);
        nonConstantItems.forEach(x -> frame.remove(x));

        frame.repaint();
        if (gameInstance.getMistakes() > 0) {
            gameInstance.writeScore();
            gameOverScreen(gameInstance);
            //System.out.println("got here");
        } else {
            menuMode();
        }
    }

    /**
     * Graphics for the game over screen
     *
     * @param gameInstance a game that is ending
     */
    void gameOverScreen(gameModes gameInstance) {
        //System.out.println("here");
        Collection<JComponent> gameOverParts = new ArrayList<>();

        JLabel gameOverMsg = new JLabel("Game Over");
        gameOverParts.add(gameOverMsg);

        JLabel stats = new JLabel("Score: " + gameInstance.getScore());
        gameOverParts.add(stats);

        gameOverMsg.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        stats.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 40));

        gameOverMsg.setHorizontalAlignment(JLabel.CENTER);
        stats.setHorizontalAlignment(JLabel.CENTER);

        stats.setText("Score: " + gameInstance.getScore());
        gameOverMsg.setText("Game Over");

        gameOverMsg.setBounds((boardWidth / 2) - (boardWidth / 6), (boardHeight / 5), (boardWidth / 3), boardHeight / 10);
        stats.setBounds((boardWidth / 2) - (boardWidth / 8), (boardHeight / 3), (boardWidth / 4), boardHeight / 10);

        frame.add(gameOverMsg);
        frame.add(stats);

        JButton menu = new JButton("Menu");
        menu.setBounds((int) (boardWidth * 3 / 10.0) - 20, boardHeight / 2, boardWidth / 5, boardHeight / 10);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverParts.forEach(x -> frame.remove(x));
                frame.repaint();
                menuMode();
            }
        });

        JButton playAgain = new JButton("Play Again?");
        playAgain.setBounds(boardWidth / 2 + 20, boardHeight / 2, boardWidth / 5, boardHeight / 10);
        gameOverParts.add(menu);
        gameOverParts.add(playAgain);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverParts.forEach(x -> frame.remove(x));
                frame.repaint();
                if (gameInstance instanceof gameMode1) {
                    gameType1();
                } else {
                    gameType2();
                }
                // TODO: Why does play again button break gameType2?
            }
        });

        nonConstantItems.addAll(gameOverParts);
        gameOverParts.forEach(x -> frame.add(x));

        //magic, gets the screen to update during runtime
        frame.invalidate();
        frame.validate();
        frame.repaint();

    }

    /**
     * Adds the return to menu button
     *
     * @param gameInstance a game which the button will act upon
     */
    void addReturnToMenu(gameModes gameInstance) {
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nonConstantItems.forEach(x -> frame.remove(x));

                frame.repaint();
                menuMode();
            }
        });
        frame.add(returnToMenu);
    }
}
