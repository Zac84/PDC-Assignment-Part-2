/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author zdtuc
 */
public class LoginFrame extends JFrame {

    public JTextField username1Input;
    public JTextField username2Input;
    public JButton play;
    public JTextArea label;

    String username1;
    String username2;

    private boolean buttonPressed = false;

    public static void main(String[] args) {
        new LoginFrame();

    }

    public LoginFrame() {
        
        label = new JTextArea();
        label.setEditable(false);
//        label.setLineWrap(true);
        Font monospacedFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        label.setFont(monospacedFont);
        label.setText(""
                + "______       _   _   _        _____ _     _           \n"
                + "| ___ \\     | | | | | |      /  ___| |   (_)          \n"
                + "| |_/ / __ _| |_| |_| | ___  \\ `--.| |__  _ _ __  ___ \n"
                + "| ___ \\/ _` | __| __| |/ _ \\  `--. \\ '_ \\| | '_ \\/ __|\n"
                + "| |_/ / (_| | |_| |_| |  __/ /\\__/ / | | | | |_) \\__ \\\n"
                + "\\____/ \\__,_|\\__|\\__|_|\\___| \\____/|_| |_|_| .__/|___/\n"
                + "                                           | |        \n"
                + "                                           |_|        \n");
        label.setBounds(100, 100, 380, 140);
        this.add(label);

        username1Input = new JTextField();
        username2Input = new JTextField();

        username1Input.setBounds(200, 400, 150, 30);
        username2Input.setBounds(200, 480, 150, 30);

        this.add(username1Input);
        this.add(username2Input);

        JLabel playerLabel1 = new JLabel("Player 1 Username");
        JLabel playerLabel2 = new JLabel("Player 2 Username");

        playerLabel1.setBounds(200, 370, 150, 30);
        playerLabel2.setBounds(200, 450, 150, 30);

        this.add(playerLabel1);
        this.add(playerLabel2);

        this.play = new JButton();
        play.addActionListener(e -> {
            this.buttonPress();
        });
        this.play.setBounds(400, 430, 100, 50);
        this.play.setBackground(Color.red);
        this.play.setFocusable(false);
        this.play.setText("PLAY");
        this.add(this.play);

        this.setLayout(null);
        this.setResizable(false);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setAlwaysOnTop(false);
    }

    public void buttonPress() {
        this.username1 = username1Input.getText();
        this.username2 = username2Input.getText();
        System.out.println(this.username1);
        System.out.println(this.username2);
        this.buttonPressed = true;
    }

    public void showPopUpMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public boolean getButtonPressed () {
        return this.buttonPressed;
    }
    
    public String[] getUsernames() {
        if (!buttonPressed) {
            return null;
        }

        String[] usernames = {this.username1, this.username2};
        
        return usernames;
    }

}
