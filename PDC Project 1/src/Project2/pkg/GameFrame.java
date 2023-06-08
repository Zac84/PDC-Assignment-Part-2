/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import Project2.pkg.BoardManager;
import Project2.pkg.BoardWrapper;

/**
 *
 * @author zdtuc
 */
public class GameFrame extends JFrame {

    //the button layout for home grid to display there own ships
    JButtonWrapper PlayerButtonGrid1;

    //the button board for the attacking grid to actually press buttons and stuff.
    JButtonWrapper PlayerButtonGrid2;


    private int desiredXPos;
    private int desiredYPos;

    /*
    TODO
    Make everything work with the gui stuff at first without changing any of the actual stuff
    Seperate stuff into classes and make them all that dandy shit
    Add derby support to the login stuff
    make the actual shooting part of the game work with gui - DONE
    clean up the shooting part of the gui
     */
    public static void main(String[] args) {
//        BFrame frame = new BFrame();
    }

    public GameFrame(User player1, User player2) {

        //this can be changed sorta thing
        this.PlayerButtonGrid1 = new JButtonWrapper(new JButton[10][10], player1.getID());
        this.PlayerButtonGrid2 = new JButtonWrapper(new JButton[10][10], player1.getID());

        JPanel Mainpanel = new JPanel();
        Mainpanel.setLayout(new BorderLayout());
        this.getContentPane().add(Mainpanel);

        JPanel topJPanel = new JPanel();
        topJPanel.setLayout(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, horizontal gap of 10 pixels
        Mainpanel.add(topJPanel, BorderLayout.NORTH);

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
        topJPanel.add(boardPanel1);

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
        topJPanel.add(boardPanel2);

        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.orange);
        Mainpanel.add(bottomPanel, BorderLayout.CENTER);

        topJPanel.setPreferredSize(new Dimension(800, 400));
        bottomPanel.setPreferredSize(new Dimension(400, 150));

        desiredXPos = -1;
        desiredYPos = -1;
        
        this.pack();
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    //a method for getting the current coordinates
    public int[] getCoordinates() {
        //this is to stop it from returning the last clicked coordinates
        if(desiredXPos < 0 || desiredYPos < 0) {
            return null;
        }
        
        int[] temp = new int[2];
        temp[0] = desiredXPos;
        temp[1] = desiredYPos;
        
        //this is to stop it from returning the last clicked coordinates
        desiredXPos = -1;
        desiredYPos = -1;
        
        return temp;
    }

    //a method for re-enabling all the buttons
    public void changeEnabledState(boolean state) {
        this.PlayerButtonGrid1.changeEnabledState(state);
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
        this.PlayerButtonGrid1.changeEnabledState(false);
        this.PlayerButtonGrid2.changeEnabledState(false);
        System.out.println("Button clicked pos: " + this.desiredXPos + " " + this.desiredYPos);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == button) {
//            System.out.println("worked");
//            button.setEnabled(false);
//            label.setVisible(true);
//        }
//    }
    //this works but isn't really useful
//                    button.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent E) {
//                        System.out.println("ButtonPressed");
//                    }
//                });
}

//        label = new JLabel("Click");
//        label.setBounds(500, 500, 100, 50);
//        label.setVisible(false);
//        button = new JButton();
//        //the reason its 650 is because you have to take into account the heigh and width of the actual button so its just the place you want - the size
//        button.setBounds(500, 550, 100, 50); // the location and size can be set like this 
//        button.addActionListener(this);
//        //e -> System.out.println("Worked")
//        button.setText("FIRE");
//        button.setFocusable(false);
////        button.setIcon(icon);//can be used to put image on button
//        button.setBackground(Color.red);
//        button.setForeground(Color.BLACK);//changes colour of text
////        button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); //borders 
