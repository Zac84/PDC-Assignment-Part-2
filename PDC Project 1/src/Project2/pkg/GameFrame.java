/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import javax.swing.*;
import java.awt.*;
import Project2.pkg.BoardWrapper;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 *
 * @author zdtuc
 */
public class GameFrame extends JFrame {

    JButtonWrapper PlayerButtonGrid1;//the button layout for home grid to display there own ships
    JButtonWrapper PlayerButtonGrid2;//the button board for the attacking grid to actually press buttons and stuff.
    private int desiredXPos;
    private int desiredYPos;
    JButton boomButton;
    private int shot;
    private JTextField boomPos;
    HashMap<String, String> map = new HashMap<String, String>();
    User user;
    JLabel[] labels;

    public GameFrame() {

        //doesn't need to be a jbutton wrapper i dont think
        this.PlayerButtonGrid1 = new JButtonWrapper(new JButton[10][10]);
        this.PlayerButtonGrid2 = new JButtonWrapper(new JButton[10][10]);
        desiredXPos = -1;
        desiredYPos = -1;
        this.boomPos = new JTextField(3);
        this.shot = 0;

        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "4");
        map.put("E", "5");
        map.put("F", "6");
        map.put("G", "7");
        map.put("H", "8");
        map.put("I", "9");
        map.put("J", "10");

        //main panel
        JPanel Mainpanel = new JPanel();
        Mainpanel.setLayout(new BorderLayout());
        this.getContentPane().add(Mainpanel);

        //top panel
        JPanel topJPanel = new JPanel();
        topJPanel.setLayout(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, horizontal gap of 10 pixels
        Mainpanel.add(topJPanel, BorderLayout.NORTH);

        //left grid layout manager
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BorderLayout());
        topJPanel.add(topLeftPanel);

        //top letters
        JPanel topRowPanel1 = new JPanel();
        topRowPanel1.setLayout(new GridLayout(1, 10));
        topRowPanel1.setBackground(Color.white);
        topLeftPanel.add(topRowPanel1, BorderLayout.NORTH);
        for (char c = 'A'; c <= 'J'; c++) {
            topRowPanel1.add(new JLabel(Character.toString(c), SwingConstants.CENTER));
        }

        //left numbers
        JPanel leftColoumPanel1 = new JPanel();
        leftColoumPanel1.setLayout(new GridLayout(10, 1));
        leftColoumPanel1.setBackground(Color.WHITE);
        topLeftPanel.add(leftColoumPanel1, BorderLayout.WEST);
        for (int i = 1; i < 11; i++) {
            leftColoumPanel1.add(new JLabel(i + "", SwingConstants.CENTER));
        }
        topLeftPanel.setPreferredSize(new Dimension(0, 50));

        //Panel for the first grid / board
        JPanel boardPanel1 = new JPanel();
        boardPanel1.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                PlayerButtonGrid1.getButton(j, i).setEnabled(false);
                boardPanel1.add(PlayerButtonGrid1.getButton(j, i));
            }
        }
        this.setButtons(this.PlayerButtonGrid1.getButtons(), new BoardWrapper(10, 10, 1, " "));
        topLeftPanel.add(boardPanel1, BorderLayout.CENTER);

        //chanes
        //right grid layout manager
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new BorderLayout());
        topJPanel.add(topRightPanel);

        //top letters
        JPanel topRowPanel2 = new JPanel();
        topRowPanel2.setLayout(new GridLayout(1, 10));
        topRowPanel2.setBackground(Color.white);
        topRightPanel.add(topRowPanel2, BorderLayout.NORTH);
        for (char c = 'A'; c <= 'J'; c++) {
            topRowPanel2.add(new JLabel(Character.toString(c), SwingConstants.CENTER));
        }

        //left numbers
        JPanel leftColoumPanel2 = new JPanel();
        leftColoumPanel2.setLayout(new GridLayout(10, 1));
        leftColoumPanel2.setBackground(Color.WHITE);
        topRightPanel.add(leftColoumPanel2, BorderLayout.WEST);
        for (int i = 1; i < 11; i++) {
            leftColoumPanel2.add(new JLabel(i + "", SwingConstants.CENTER));
        }
        topRightPanel.setPreferredSize(new Dimension(0, 50));

        //Panel for the second grid / board
        JPanel boardPanel2 = new JPanel();
        boardPanel2.setLayout(new GridLayout(10, 10));
        //add stuff to panel
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.PlayerButtonGrid2.getButton(j, i).setActionCommand(j + "_" + i);
                this.PlayerButtonGrid2.getButton(j, i).addActionListener(e -> handleButtonClick(e));
                boardPanel2.add(PlayerButtonGrid2.getButton(j, i));
            }
        }
        this.setButtons(this.PlayerButtonGrid2.getButtons(), new BoardWrapper(10, 10, 1, " "));
        topRightPanel.add(boardPanel2, BorderLayout.CENTER);

        //bottom panel
        JPanel bottomPanel = new JPanel();
        Mainpanel.add(bottomPanel, BorderLayout.CENTER);
        bottomPanel.setLayout(null);

        this.boomPos.setBounds(540, 80, 50, 50);
        this.boomPos.setHorizontalAlignment(JTextField.CENTER);
        bottomPanel.add(boomPos);

        boomButton = new JButton();
        boomButton.setBackground(Color.red);
        boomButton.setForeground(Color.BLACK);
        boomButton.setText("BOOM");
        boomButton.setBounds(600, 80, 150, 50);
        boomButton.setFocusable(false);
        bottomPanel.add(boomButton);

        boomButton.addActionListener(e -> {
            //do boom work
            this.boomButtonPress();
            System.out.println("boom pressed");

        });

        JButton intructions = new JButton();
        intructions.addActionListener(e -> {
            this.intructionsMessage();
        });

        intructions.setBounds(600, 20, 150, 50);
        intructions.setText("Instructions");
        intructions.setBackground(new Color(90, 163, 78));
        intructions.setForeground(Color.WHITE);
        intructions.setFocusable(false);
        bottomPanel.add(intructions);

        labels = new JLabel[3];
        labels[0] = new JLabel("'s turn");
        labels[1] = new JLabel("'s Board");
        labels[2] = new JLabel("'s Board");

        labels[0].setBounds(400, 100, 200, 50);
        labels[1].setBounds(100, 10, 200, 25);
        labels[2].setBounds(500, 10, 200, 25);
        
        bottomPanel.add(labels[0]);
        bottomPanel.add(labels[1]);
        bottomPanel.add(labels[2]);

        //panel sizes
        topJPanel.setPreferredSize(new Dimension(800, 400));
        bottomPanel.setPreferredSize(new Dimension(800, 160));

        this.pack();
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.intructionsMessage();

    }

    public void updateLabels(String player1, String player2) {
        this.labels[0].setText(player1 + "'s turn");
        this.labels[1].setText(player1 + "'s Board");
        this.labels[2].setText(player2 + "'s Board");
        this.repaint();
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void boomButtonPress() {
        String inputText = boomPos.getText();

        if (!this.checkCoordinates(inputText)) {
            return;
        }
        String[] arrayInput = inputText.split(" ");

        this.desiredXPos = Integer.parseInt(map.get((arrayInput[0]).toUpperCase()))-1;
        this.desiredYPos = Integer.parseInt(arrayInput[1])-1;
        this.shot = 1;
    }

    public boolean checkCoordinates(String input) {
        String[] coordinates = input.split(" ");
        
        if (!coordinates[0].matches("[a-jA-J]")) {
            return false;
        }

        if (!coordinates[1].matches("[1-9]|10")) {
            return false;
        }
        return true;
    }

    //a method for getting the current coordinates
    public int[] getCoordinates() {
        //this is to stop it from returning the last clicked coordinates
        if (desiredXPos < 0 || desiredYPos < 0) {
            return null;
        }

        int[] temp = new int[3];

        temp[0] = desiredXPos;
        temp[1] = desiredYPos;
        temp[2] = shot == 1 ? 1 : 0;
        System.out.println("Desired when getCoordinates is called: " + temp[0] + ", " + temp[1]);
        System.out.println("shot when getCoordinates is called: " + temp[2]);

        //this is to stop it from returning the last clicked coordinates
        desiredXPos = -1;
        desiredYPos = -1;
        shot = 0;

        return temp;
    }

    //a method for re-enabling all the buttons
    public void changeEnabledState(boolean state) {
        this.PlayerButtonGrid2.changeEnabledState(state);
    }

    //this should update the buttons if the normal board is changed.
    public void setButtons(JButton[][] buttons, BoardWrapper normalBoard) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (normalBoard.getBoardSpaceString(i, j).equals("O")) { //if there is a ship, colour it gray
                    buttons[i][j].setBackground(Color.GRAY);
                } else if (normalBoard.getBoardSpaceString(i, j).equals("X")) {//if there is a hit ship, colour it red
                    buttons[i][j].setBackground(Color.RED);
                } else if (normalBoard.getBoardSpaceString(i, j).equals("M")) {//if it was a miss, colour it yellow
                    buttons[i][j].setBackground(Color.YELLOW);
                } else {
                    buttons[i][j].setBackground(Color.CYAN); //if its black set it sea coloured
                }
            }
        }
        repaint();
    }

    //player 1 board is the own board
    //player 2 board is the attacking board
    public void updateButtons(BoardWrapper player1Board, BoardWrapper player2Board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                //updates the home grid of buttons
                if (player1Board.getBoardSpaceString(j, i).equals("O")) { //if there is a ship, colour it gray
                    PlayerButtonGrid1.getButton(j, i).setBackground(Color.GRAY);
                } else if (player1Board.getBoardSpaceString(j, i).equals("X")) {//if there is a hit ship, colour it red
                    PlayerButtonGrid1.getButton(j, i).setBackground(Color.RED);
                } else {
                    PlayerButtonGrid1.getButton(j, i).setBackground(Color.CYAN); //if its black set it sea coloured
                }

                //updates attacking board
                if (player2Board.getBoardSpaceString(j, i).equals("X")) {//if there is a hit ship, colour it red
                    PlayerButtonGrid2.getButton(j, i).setBackground(Color.RED);
                } else if (player2Board.getBoardSpaceString(j, i).equals("M") || player1Board.getBoardSpaceString(i, j).equals("M")) {//if it was a miss, colour it yellow
                    PlayerButtonGrid2.getButton(j, i).setBackground(Color.YELLOW);
                } else {
                    PlayerButtonGrid2.getButton(j, i).setBackground(Color.CYAN); //if its black set it sea coloured
                }

            }
        }
        repaint();
    }

    public void handleButtonClick(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        String[] coordinates = actionCommand.split("_");
        this.desiredXPos = Integer.parseInt(coordinates[0]);
        this.desiredYPos = Integer.parseInt(coordinates[1]);
        this.PlayerButtonGrid2.changeEnabledState(false);
        System.out.println("Button clicked pos: " + this.desiredXPos + " " + this.desiredYPos);
    }

    public void allowBoomButton(boolean allowed) {
        this.boomButton.setEnabled(allowed);
        this.boomButton.setVisible(allowed);
        this.boomPos.setEnabled(allowed);
        this.boomPos.setVisible(allowed);
        this.repaint();
    }

    public void showPopUpMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public void intructionsMessage() {
        this.showPopUpMessage("Intructions", "Please press the square that you want to fire on, if the square turns red, its a hit, yellow is a miss"
                + "\nif your carrier ship is alive you are able to use one large shot that covers a 3x3 area, to use that \nplease input a location 1 away from all the walls"
                + "and in the form (letter number) e.g (a 1) and press \nBOOM!. Once all the oposing players ships have been sunk you will win!\n"
                + "Have fun! :)");
    }

}