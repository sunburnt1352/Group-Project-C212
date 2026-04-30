import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Menu");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds((boardWidth / 2) - (boardWidth / 10), 0, (boardWidth / 5), boardHeight / 10);
        frame.add(textPanel);
        nonConstantItems.add(textPanel);

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
        button1.setBounds(((boardWidth / 2) - (boardWidth / 10)), ((boardHeight - (4 * (boardHeight / 5)))), boardWidth / 5, boardHeight / 10);
        button1.setBackground(Color.pink);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nonConstantItems.forEach(item -> frame.remove(item));
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
                nonConstantItems.forEach(item -> frame.remove(item));
                gameType2();
            }
        });
        nonConstantItems.add(button2);
        frame.add(button2);
    }


    /**
     * Builds the header and menu button for Letter Mode; calls runGame1()
     */
    void gameType1() {
        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Letter Mode");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);

        LetterMode gameInstance = new LetterMode();

        addReturnToMenu(gameInstance);

        runGame1(gameInstance);
        // https://math.hws.edu/eck/cs124/javanotes3/c6/s5.html

        frame.repaint();
        frame.setVisible(true);

    }

    /**
     * Begin button and mechanics for gameMode1
     * @param gameInstance a game
     */
    void runGame1(LetterMode gameInstance) {

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
                boardHeight / 2,
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

    /**
     * Sets a given JButton to a given icon
     * @param currLetter the icon to be changed to
     * @param letter the JButton to display currLetter
     */
    void changeImage(ImageIcon currLetter, JButton letter) {
        letter.setBounds(boardWidth / 2 - (int) (0.5 * currLetter.getIconWidth()),
                (boardHeight / 2 - currLetter.getIconHeight()),
                currLetter.getIconWidth(), currLetter.getIconHeight());
        letter.setIcon(currLetter);
    }

    /**
     * Starts game mode 2
     */
    void gameType2() {

        textLabel.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Word Mode");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);

        WordMode gameInstance = new WordMode();

        addReturnToMenu(gameInstance);

        JLabel mistakeDialogue= new JLabel("Wrong, word was: "+gameInstance.getCurrWord());
        mistakeDialogue.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 20));
        mistakeDialogue.setHorizontalAlignment(JLabel.CENTER);
        mistakeDialogue.setText("Wrong, word was: "+gameInstance.getCurrWord());
        mistakeDialogue.setOpaque(true);
        mistakeDialogue.setBounds(boardWidth / 3,
                ((int) (boardHeight / 1.5)),
                boardWidth / 3, boardHeight / 10);
        nonConstantItems.add(mistakeDialogue);


        JButton letter = new JButton("Begin");
        letter.setBackground(Color.PINK);
        letter.setBounds(boardWidth / 2 - 50, boardHeight / 2 - 100, 100, 100);

        nonConstantItems.add(letter);

        letter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInstance.randomWord();
                List<String> tempWords= gameInstance.getLetterFiles();
                showWordGM2(gameInstance, letter, tempWords, 0, false, mistakeDialogue);
                letter.setText(null);
            }
        });

        frame.add(letter);
        frame.setFocusable(true);
        frame.repaint();
        frame.setVisible(true);

    }


    /**
     * Recursively cycles through each letter of a word
     * @param gameInstance the game being run
     * @param letter the JButton being updated
     * @param word the word being signed
     * @param index the index of the current letter
     */
    void showWordGM2(WordMode gameInstance, JButton letter, List<String> word, int index, boolean madeMistake, JLabel mistakeDialogue){
        if(index < word.size()){
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(() -> {
                ImageIcon currLetter=gameInstance.getLetter(word.get(index));
                changeImage(currLetter,letter);
                showWordGM2(gameInstance, letter, word, index + 1, madeMistake, mistakeDialogue);

            }, 1, TimeUnit.SECONDS);
        }

        if(index == word.size()){
            if(madeMistake){
                frame.remove(mistakeDialogue);
            }

            if(gameInstance.getMistakes()==3){
                frame.remove(letter);
                gameOverScreen(gameInstance);
            }
            else
                getAnswerForGM2(gameInstance, letter, mistakeDialogue);
        }
    }

    /**
     * Creates a mistake counter, a guess box, and a text field,
     *    and runs the guess box functionality
     * @param gameInstance the game being run
     */
    void getAnswerForGM2(WordMode gameInstance, JButton letter, JLabel mistakeDialogue){

        JLabel mistakeCounter = new JLabel("Mistakes");
        mistakeCounter.setText("Mistakes: " + gameInstance.getMistakes());

        mistakeCounter.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        mistakeCounter.setHorizontalAlignment(JLabel.CENTER);
        mistakeCounter.setOpaque(true);
        mistakeCounter.setBounds((boardWidth - (boardWidth / 3)), 0, (boardWidth / 3), boardHeight / 10);

        nonConstantItems.add(mistakeCounter);

        frame.add(mistakeCounter);

        JButton guess = new JButton("Guess");
        guess.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 25));
        guess.setHorizontalAlignment(JLabel.CENTER);
        guess.setOpaque(true);
        guess.setBackground(new Color(152, 251, 152));
        guess.setBounds((boardWidth/2)-(boardWidth/6),(boardHeight-(boardHeight/2)) ,(boardWidth / 3), boardHeight / 10);
        nonConstantItems.add(guess);
        frame.add(guess);

        JTextField guessingTest = new JTextField();
        guessingTest.setOpaque(true);
        guessingTest.setHorizontalAlignment(JTextField.CENTER);
        guessingTest.setFont(new Font("Arial", Font.PLAIN, 16));
        guessingTest.setBackground(new Color(189, 238, 255));
        guessingTest.setBounds((boardWidth/2)-(boardWidth/6), (boardHeight-(int)(boardHeight/2.5)) ,(boardWidth / 3), boardHeight / 10);
        nonConstantItems.add(guessingTest);
        frame.add(guessingTest);

        guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = guessingTest.getText();
                if(!temp.toLowerCase().equals(gameInstance.getCurrWord())){

                    System.out.println("word: "+gameInstance.getCurrWord());
                    frame.add(mistakeDialogue);
                    frame.remove(guess);
                    frame.remove(guessingTest);

                    mistakeDialogue.setText("Wrong, word was: "+gameInstance.getCurrWord());
                    gameInstance.setMistakes( (gameInstance.getMistakes()+1) );

                    mistakeCounter.setText("Mistakes: " + gameInstance.getMistakes());

                    mistakeCounter.paintImmediately(mistakeCounter.getVisibleRect());

                    frame.invalidate();
                    frame.validate();
                    frame.repaint();

                    showWordGM2(gameInstance,letter ,gameInstance.getLetterFiles(),0, true,mistakeDialogue);
                }
                else {
                    gameInstance.SCORE += temp.length()*10;
                    gameInstance.randomWord();

                    frame.remove(guess);
                    frame.remove(guessingTest);

                    frame.invalidate();
                    frame.validate();
                    frame.repaint();

                    showWordGM2(gameInstance,letter,gameInstance.getLetterFiles(),0, false,mistakeDialogue);
                }

                frame.remove(mistakeCounter);
                guessingTest.setText("");
            }
        });

        //magic, gets the screen to update during runtime
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }


    /**
     * Mechanics for ending any game mode
     * @param gameInstance a game
     */
    void gameOver(gameModes gameInstance) {
        nonConstantItems.forEach(x -> frame.remove(x));

        frame.invalidate();
        frame.validate();
        frame.repaint();

        if (gameInstance.getMistakes() > 0) {
            gameInstance.writeScore();
            gameOverScreen(gameInstance);
        } else {
            menuMode();
        }
    }

    /**
     * Graphics for the game over screen
     * @param gameInstance a game that is ending
     */
    void gameOverScreen(gameModes gameInstance) {
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
                if (gameInstance instanceof LetterMode) {
                    gameType1();
                } else {
                    gameType2();
                }
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
     * @param gameInstance a game which the button will act upon
     */
    void addReturnToMenu(gameModes gameInstance) {
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nonConstantItems.forEach(x -> frame.remove(x));
                gameInstance.writeScore();
                frame.repaint();
                menuMode();
            }
        });
        frame.add(returnToMenu);
    }
}
