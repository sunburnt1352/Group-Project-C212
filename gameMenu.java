import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class gameMenu {
    int boardWidth = 800;
    int boardHeight = 800; //50 for the text panel on top

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

        menuMode();
        //gameType1();

        frame.setVisible(true);
    }


    void menuMode(){
        frame.repaint();
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
        button2.setBounds(((boardWidth/2)-(boardWidth/10) ), ( (boardHeight-(3*(boardHeight/5)))), boardWidth/5, boardHeight/10);
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





    void gameType1(){


        textLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Game Mode 1");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight/10);
        frame.add(textPanel);



        JButton menu= new JButton("Return to Menu");
        menu.setBounds(0,0,(boardHeight/6),(boardWidth/10));
        menu.setBackground(Color.ORANGE);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                menuMode();
            }
        });
        frame.add(menu);




        gameMode1 gameInstance= new gameMode1();

        ImageIcon currLetter= gameInstance.getRandomLetter();
        JButton letter = new JButton();
        letter.setBounds(((boardWidth/2)-260), ( (boardHeight/2)-236), 260, 236); // max letter dimensions are 260x236
        letter.setIcon(currLetter);
        letter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon currLetter= gameInstance.getRandomLetter();
                letter.setBounds(((boardWidth/2)-260), ( (boardHeight/2)-236), 260, 236);
                letter.setIcon(currLetter);

            }
        });
        frame.add(letter);

        // https://math.hws.edu/eck/cs124/javanotes3/c6/s5.html





        frame.repaint();
        frame.setVisible(true);

        //could turn the start game code into an interface thing
        while(gameInstance.getMistakes()!=3){
            gameInstance.setMistakes(gameInstance.getMistakes()+1);

        }



    }


    void gameType2(){
        frame.repaint();


        textLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Game Mode 2");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBounds(0, 0, boardWidth, boardHeight/10);
        frame.add(textPanel);

        JButton menu= new JButton("Return to Menu");
        menu.setBounds(0,0,(boardHeight/6),(boardWidth/10));
        menu.setBackground(Color.ORANGE);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                menuMode();
            }
        });

        frame.add(menu);

    }

}
