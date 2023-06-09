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
    JLabel[] labels;
    private final JTextField orientation;
    private final JTextField coordinates;
    private String orientationAndCoordinates;

//    public static void main(String[] args) {
//        PlaceBoatsFrame frame = new PlaceBoatsFrame();
//    }
    public PlaceBoatsFrame() {

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
        topJPanel.setBackground(Color.WHITE);
        mainJPanel.add(topJPanel, BorderLayout.NORTH);
        topJPanel.add(new JLabel(""));
        for (char c = 'A'; c <= 'J'; c++) {
            topJPanel.add(new JLabel(Character.toString(c), SwingConstants.CENTER));
        }

        //left panel
        JPanel leftJPanel = new JPanel();
        leftJPanel.setLayout(new GridLayout(10, 1)); //10x1 grid 
        leftJPanel.setBackground(Color.WHITE);
        mainJPanel.add(leftJPanel, BorderLayout.WEST);
        for (int i = 1; i < 11; i++) {
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

        labels = new JLabel[3];
        labels[0] = new JLabel("Player: ");
        labels[1] = new JLabel("Boat Name: ");
        labels[2] = new JLabel("Boat Length: ");

        labels[0].setBounds(50, 25, 200, 25);
        labels[1].setBounds(50, 50, 200, 25);
        labels[2].setBounds(50, 75, 200, 25);

        bottomJPanel.add(labels[0]);
        bottomJPanel.add(labels[1]);
        bottomJPanel.add(labels[2]);

        orientation = new JTextField(1);
        coordinates = new JTextField(3);
        orientation.setBounds(50, 120, 50, 30);
        coordinates.setBounds(110, 120, 100, 30);

        bottomJPanel.add(orientation);
        bottomJPanel.add(coordinates);

        JButton place = new JButton();
        place.setBounds(220, 120, 100, 30);
        place.setBackground(Color.white);
        place.setFocusable(false);
        place.setText("PLACE");
        place.addActionListener(e -> {
            if (checkFields()) {
                this.takeTextFields();
            } else {
                this.showPopUpMessage("Incorrect", "Please make sure you input the correct fields");
            }
        });
        bottomJPanel.add(place);

        topJPanel.setPreferredSize(new Dimension(750, 50));
        leftJPanel.setPreferredSize(new Dimension(50, 600));
        bottomJPanel.setPreferredSize(new Dimension(575, 200));

        this.updateButtons();

        this.pack();
        this.setResizable(false);
        this.setSize(575, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.intructionsMessage();
        this.setAlwaysOnTop(false);
    }

    public boolean checkFields() {
        boolean temp = true;

        if (!this.correctCharacters(this.orientation.getText(), "n e s w")) {
            this.orientation.setText("");
            temp = false;
        }

        if (!this.checkCoordinates(this.coordinates.getText())) {
            this.coordinates.setText("");
            temp = false;
        }

        return temp;
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

    //should be in another class but here for now
    public boolean correctCharacters(String input, String correctCharacters) {
        String[] chars = correctCharacters.split(" ");

        if (input.length() != chars[0].length()) { //if the length of the input isn't the same as the desired lenght
            return false; //return false
        }

        if (!correctCharacters.toUpperCase().contains(input.toUpperCase())) {
            return false;
        }

        return true;
    }

    public void takeTextFields() {
        String input1 = orientation.getText();
        String input2 = coordinates.getText();
        this.orientationAndCoordinates = input1 + " " + input2;

        orientation.setText("");
        coordinates.setText("");
        System.out.println(this.orientationAndCoordinates);
    }

    public String getCoordinates() {

        if (this.orientationAndCoordinates == null) {
            return null;
        }
        System.out.println("went through, its " + orientationAndCoordinates);
        String temp = "" + orientationAndCoordinates;
        orientationAndCoordinates = null;
        return temp;
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

    public void setLabels(String player, String boatName, String boatLength) {
        this.labels[0].setText("Player: " + player);
        this.labels[1].setText("Boat Name: " + boatName);
        this.labels[2].setText("Boat Length: " + boatLength);
    }

}
