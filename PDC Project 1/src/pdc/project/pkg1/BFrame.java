/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

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
import pdc.project.pkg1.BoardManager;
import pdc.project.pkg1.BoardWrapper;

/**
 *
 * @author zdtuc
 */
public class BFrame extends JFrame {

    //the button layout for home grid to display there own ships
    JButton[][] PlayerButtonGrid1;
    BoardWrapper PlayerBoard1;
    //the button board for the attacking grid to actually press buttons and stuff.
    JButton[][] PlayerButtonGrid2;
    BoardWrapper PlayerBoard2;

    public BFrame() {

        JButton[][] buttons1 = new JButton[10][10];
        JButton[][] buttons2 = new JButton[10][10];

        this.setSize(800, 800);

        JPanel Mainpanel = new JPanel();
        Mainpanel.setLayout(new BorderLayout());
        this.getContentPane().add(Mainpanel);

        JPanel topJPanel = new JPanel();
        topJPanel.setLayout(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, horizontal gap of 10 pixels
        Mainpanel.add(topJPanel, BorderLayout.NORTH);

        //Panel for the first grid / board
        JPanel boardPanel1 = new JPanel();
        boardPanel1.setLayout(new GridLayout(10, 10));
        //add stuff to panel
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons1[i][j] = new JButton();
                boardPanel1.add(buttons1[i][j]);
                buttons1[i][j].setEnabled(false);
            }
        }
        this.updateButtons(buttons1, new BoardWrapper(10, 10, 1, " "));
        topJPanel.add(boardPanel1);

        JPanel boardPanel2 = new JPanel();
        boardPanel2.setLayout(new GridLayout(10, 10));
        //add stuff to panel
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons2[i][j] = new JButton();
                boardPanel2.add(buttons2[i][j]);
            }
        }
        this.updateButtons(buttons2, new BoardWrapper(10, 10, 1, " "));
        topJPanel.add(boardPanel2);

        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.orange);
        Mainpanel.add(bottomPanel, BorderLayout.CENTER);

        topJPanel.setPreferredSize(new Dimension(800, 400));
        bottomPanel.setPreferredSize(new Dimension(400, 150));
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        
//
//        //initialize the button array with buttons
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                buttons[i][j] = new JButton();
//                test[i][j] = false;
//                panel.add(buttons[i][j]);
//            }
//        }
//        this.updateButtons(buttons, new BoardWrapper(10, 10, 1, " "));

    }

    public void updateButtons(User user) {
        //needs to take in a user and then set the button stuff to the correct stuff and then update them accordingly
        if(user)
    }
    
    
    //this should update the buttons if the normal board is changed.
    public void updateButtons(JButton[][] buttons, BoardWrapper normalBoard) {
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
