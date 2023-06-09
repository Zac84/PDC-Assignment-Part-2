/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import javax.swing.JButton;

/**
 *
 * @author zdtuc
 */
public class JButtonWrapper {

    JButton[][] buttons;
    int playerID;

    public JButtonWrapper(JButton[][] buttons) {
        this.buttons = buttons;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
            }
        }

    }

    public void changeEnabledState(boolean enable) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(enable);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public JButton getButton(int row, int coloum) {
        return this.buttons[row][coloum];
    }

}
