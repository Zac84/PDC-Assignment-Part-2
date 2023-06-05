/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Version;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author zdtuc
 */
public class Panel extends JPanel implements ActionListener{

    public int counter;
    public JButton button;
    public JButton button2;
    public JLabel label;

    public Panel() {
        //this is like the main method for this class, when you run it does all these things
        //plus its also just the normal constructor

        counter = 0;
        button = new JButton("click");
        ActionListener listener = new MyActionListener();
        button.addActionListener(this);
        
        label = new JLabel("Bro somehting happening");
        this.add(label);
        this.add(button);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
        label.setText("Number of clicks " + this.counter);
        repaint();
    }
    

    

}
