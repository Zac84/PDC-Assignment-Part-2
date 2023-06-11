/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zdtuc
 */
public class BattleShips {

    public static void main(String[] args) {
        BoardManager Board = new BoardManager();

        Scanner scan = new Scanner(System.in);

        String TempInput = "";
        boolean won = false;
        
        LoginFrame login = new LoginFrame();

        String[] usernames = null;

        while (!login.getButtonPressed()) {
        }

        usernames = login.getUsernames();

        login.dispose();

        Board.fillUsers(Board.Player1, usernames[0]);

        Board.fillUsers(Board.Player2, usernames[1]);

        Board.placeBoats(Board.Player1);

        Board.placeBoats(Board.Player2);
        
        GameFrame frame = new GameFrame();
        int[] desiredPos = null;

        //temp References
        BoardWrapper tempPlayer1Board;
        BoardWrapper tempPlayer1Board2;
        BoardWrapper tempPlayer2Board;
        User player1;
        User player2;

        int counter = 2; // alternating for turns
        while (!won) {

            if (counter % 2 == 0) { //sets tempboard to the reference to the users board
                player1 = Board.Player1;
                player2 = Board.Player2;
                tempPlayer1Board = Board.Player1Board;
                tempPlayer1Board2 = Board.Player1Board2;
                tempPlayer2Board = Board.Player2Board;
            } else {
                player1 = Board.Player2;
                player2 = Board.Player1;
                tempPlayer1Board = Board.Player2Board;
                tempPlayer1Board2 = Board.Player2Board2;
                tempPlayer2Board = Board.Player1Board;
            }

            frame.updateButtons(tempPlayer1Board, tempPlayer1Board2);
            frame.changeEnabledState(true);
            frame.updateLabels(player1.getUserName(), player2.getUserName());
            
            //makes the button disappear if they have used the button
            if (!Board.shotAvalible(player1)) { //if board isn't avalible don't show boom button
                frame.allowBoomButton(false);
            } else {
                frame.allowBoomButton(true);
            }

            while (desiredPos == null) {
                desiredPos = frame.getCoordinates();
                if (desiredPos != null) {
                    if (!tempPlayer1Board2.notMissOrShip(desiredPos[0], desiredPos[1]) || !tempPlayer1Board2.notMissOrShip(desiredPos[0], desiredPos[1])) {
                        frame.showPopUpMessage("Incorrect", "Please pick a location you haven't already shot");
                        frame.changeEnabledState(true);
                        desiredPos = null;
                    }
                }
                //this is because the while loop is too fast for the program to keep up with
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleShips.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //takes coordinates
            int desiredXPos = desiredPos[0];
            int desiredYPos = desiredPos[1];

            if (Board.shotAvalible(player1)) { //if the shot is avalible
                if (desiredPos[2] == 1) { //if they use the shot
                    Board.useShot(desiredXPos, desiredYPos, player1); //use shot
                } else { //if they don't use the shot, just shoot normally
                    if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals(tempPlayer2Board.getFiller())) { //miss
                        tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "M"); // sets shooting board space to M for miss
                    } else if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals("O")) { //HIT
                        tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "X"); // Sets X on both player 1s shooting screen and player 2's board
                        tempPlayer2Board.setSpace(desiredXPos, desiredYPos, "X");
                    }
                }
            } else { //if they don't have the shot avalible, shoot normally.
                if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals(tempPlayer2Board.getFiller())) { //miss
                    tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "M"); // sets shooting board space to M for miss
                } else if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals("O")) { //HIT
                    tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "X"); // Sets X on both player 1s shooting screen and player 2's board
                    tempPlayer2Board.setSpace(desiredXPos, desiredYPos, "X");
                }
            }

            //reset desiredPos so that it doesn't break
            desiredPos = null;

            //displays the new information in the gui
            frame.updateButtons(tempPlayer1Board, tempPlayer1Board2);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
            }


            //to check if they have won or not
            if (Board.checkWin(player1.getID())) {
                UserManagement um = new UserManagement(player1, player1.getUserName(), Board.DB.getConnection(), "pdc");
                um.increaseWins(player1);
                frame.showPopUpMessage("", player1.getUserName() + " Has one! Congratulations!\n" + player1.getUserName() + " Has " + player1.getNumberOfWins() + " Wins!");
                System.out.println();
                won = true;
            } else if (Board.checkWin(player2.getID())) {
                UserManagement um = new UserManagement(player1, player1.getUserName(), Board.DB.getConnection(), "pdc");
                um.increaseWins(player1);
                frame.showPopUpMessage("", player1.getUserName() + " Has one! Congratulations!\n" + player2.getUserName() + " Has " + player2.getNumberOfWins() + " Wins!");
                won = true;
            } else {
                counter++;
            }
        }
        Board.DB.closeConnections();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        frame.dispose();
    }

}
