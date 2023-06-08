/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

/**
 *
 * @author zdtuc
 */
import javax.swing.*;
import java.awt.*;

public class PlaceBoatsFrame extends JFrame {

    JButton[][] buttonGrid;
    BoardWrapper Board;

    public static void main(String[] args) {
        PlaceBoatsFrame frame = new PlaceBoatsFrame();
    }

    public PlaceBoatsFrame() {

        this.intructionsMessage();

        this.Board = new BoardWrapper(10, 10, -1, " ");
        this.buttonGrid = new JButton[10][10];
//        this.Board = board;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.buttonGrid[i][j] = new JButton();
            }
        }

        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        this.getContentPane().add(mainJPanel);

        //top panel
        JPanel topJPanel = new JPanel();
        topJPanel.setLayout(new GridLayout(1, 11)); //1x10 grid 
        topJPanel.setBackground(Color.red);
        mainJPanel.add(topJPanel, BorderLayout.NORTH);
        //TODO needs to be filled with pictures of letters hopefully or labels
        topJPanel.add(new JLabel(""));
        for (char c = 'A'; c <= 'J'; c++) {
            topJPanel.add(new JLabel(Character.toString(c), SwingConstants.CENTER));
        }

        //left panel
        JPanel leftJPanel = new JPanel();
        leftJPanel.setLayout(new GridLayout(10, 1)); //10x1 grid 
        leftJPanel.setBackground(Color.green);
        mainJPanel.add(leftJPanel, BorderLayout.WEST);
        //TODO needs to be filled with pictures of numbers hopefully or labels
        for (int i = 0; i < 10; i++) {
            leftJPanel.add(new JLabel(i + "", SwingConstants.CENTER));
        }

        //central panel
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new GridLayout(10, 10));
        mainJPanel.add(centrePanel, BorderLayout.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.buttonGrid[j][i].setEnabled(false);
                centrePanel.add(this.buttonGrid[j][i]);
            }
        }
        //needs to be filled with buttons

        //bottom panel
        JPanel bottomJPanel = new JPanel();
        mainJPanel.add(bottomJPanel, BorderLayout.SOUTH);
        bottomJPanel.setLayout(null);
        
        JButton intructions = new JButton();
        intructions.addActionListener(e -> {
            this.intructionsMessage();
        });
        
        intructions.setBounds(375, 35, 150, 40);
        intructions.setText("Instructions");
        intructions.setBackground(new Color(90, 163, 78));
        intructions.setForeground(Color.WHITE);
        intructions.setFocusable(false);
        bottomJPanel.add(intructions);
        /*
        TODO
        Needs a button for instructions that opens pop up
        A label with the current boat name
        A label with current boat length
        A text input box with orientaion
        A text input box with coordinates
        A button to place, which deletes the stuff for the text box's and put the ships on the board, should also make sure that the coordinates are correct
        A button for done which should just set that value to d
         */

        topJPanel.setPreferredSize(new Dimension(750, 50));
        leftJPanel.setPreferredSize(new Dimension(50, 600));
        bottomJPanel.setPreferredSize(new Dimension(575, 200));

        this.updateButtons();

        this.pack();
        this.setResizable(false);
        this.setSize(575, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showPopUpMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public void intructionsMessage() {
        this.showPopUpMessage("Intructions", "To place a boat, input the orientation (N, E, S, W), this is the direction the boat will face \nand input the coordinates x,y in the form (letter number) (a 1)");
    }

    //should just update all the buttons depending on the Board
    public void updateButtons() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                //updates the home grid of buttons
                if (this.Board.getBoardSpaceString(j, i).equals("O")) { //if there is a ship, colour it gray
                    this.buttonGrid[j][i].setBackground(Color.GRAY);
                } else {
                    this.buttonGrid[j][i].setBackground(Color.CYAN); //if its black set it sea coloured
                }

            }
        }
        repaint();
    }

    public void setBoard(BoardWrapper board) {
        this.Board = board;
    }

}
