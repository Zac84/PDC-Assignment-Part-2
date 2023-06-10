/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.Scanner;

/**
 *
 * @author zdtuc
 */
public class BattleShips {

    public static void main(String[] args) {
        PrinterClass printer = new PrinterClass();
        BoardManager Board = new BoardManager();
        InputChecker IC = new InputChecker();

        Scanner scan = new Scanner(System.in);

        String TempInput = "";
        boolean won = false;

        //TODO Replace with gui title screen
        printer.printTitleScreen();

        //TODO replace this stuff with gui and derby version
        //prints the log in screen.
        //change this from checking if they want to login or create a new and just ask them to insert a username
        //if there is file with that username it can use the file management log in and if not it can use create new
        
        LoginFrame login = new LoginFrame();
        
        String[] usernames;
        
        while(!login.getButtonPressed()) { 
            usernames = login.getUsernames();
        }
        
        login.dispose();
        
        printer.LogInScreen(Board.Player1, Board.Player2);
        System.out.println("Player 1: (L / C) ");
        TempInput = IC.check("L C , l c", true);
        Board.fillUsers(Board.Player1, TempInput);

        System.out.println("Player 2: (L / C)");
        TempInput = IC.check("L C , l c", true);
        Board.fillUsers(Board.Player2, TempInput);

        //Board manager place boats
        //GUI elements and l
        Board.placeBoats(Board.Player1);
        printer.Clear();
        Board.placeBoats(Board.Player2);

        printer.Clear();
        System.out.println("Key:"
                + "\nO segments are alive boat segments"
                + "\nX are hit boat segments"
                + "\nM are misses\n\n");

        //starts up the gui window
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

            //CODE SMELL FIX UP
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

            printer.printDoubleBoard(tempPlayer1Board, tempPlayer1Board2, player1.getUserName(), player2.getUserName());
            printer.shootingScreen(player1.getUserName());

            frame.updateButtons(tempPlayer1Board, tempPlayer1Board2);
            frame.changeEnabledState(true);
            frame.updateLabels(player1.getUserName(), player2.getUserName());
            //makes the button disappear if they have used the button
            if (!Board.shotAvalible(player1)) {
                frame.allowBoomButton(false);
            } else {
                frame.allowBoomButton(true);
            }

            //this needs it own method and classes
            while (desiredPos == null) {
                desiredPos = frame.getCoordinates();
                //CODE SMELLY
                if (desiredPos != null) {
                    if (tempPlayer1Board2.getBoardSpaceString(desiredPos[0], desiredPos[1]).equals("M") || tempPlayer1Board2.getBoardSpaceString(desiredPos[0], desiredPos[1]).equals("X")) {
                        frame.showPopUpMessage("Incorrect", "Please pick a location you haven't already shot");
                        frame.changeEnabledState(true);
                        desiredPos = null;
                    }
                }
                //stops it from continuing until courdinates are found.
            }

            int desiredXPos = desiredPos[0];
            int desiredYPos = desiredPos[1];

            System.out.println("desired pos: " + desiredXPos + ", " + desiredYPos);
            System.out.println("Desired pos2: " + desiredPos[2]);

            //fix this shit up now that the boom button disapperas when you press it.
            //CODE SMELL FIX UP
            if (Board.shotAvalible(player1)) {
                if (desiredPos[2] == 1) {
                    Board.useShot(desiredXPos, desiredYPos, player1);
                } else {
                    if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals(tempPlayer2Board.getFiller())) { //miss
                        tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "M"); // sets shooting board space to M for miss
                    } else if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals("O")) { //HIT
                        tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "X"); // Sets X on both player 1s shooting screen and player 2's board
                        tempPlayer2Board.setSpace(desiredXPos, desiredYPos, "X");
                    }
                }
            } else {
                if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals(tempPlayer2Board.getFiller())) { //miss
                    tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "M"); // sets shooting board space to M for miss
                } else if (tempPlayer2Board.getBoardSpaceString(desiredXPos, desiredYPos).equals("O")) { //HIT
                    tempPlayer1Board2.setSpace(desiredXPos, desiredYPos, "X"); // Sets X on both player 1s shooting screen and player 2's board
                    tempPlayer2Board.setSpace(desiredXPos, desiredYPos, "X");
                }
            }

            //reset desiredPos so that it doesn't break
            desiredPos = null;

            //should update the gui afterwards
            //should pass in the tempPlayer1Board for printing there there own board
            //should pass in the tempPlayer1Board2 for printing the board they are attacking
            frame.updateButtons(tempPlayer1Board, tempPlayer1Board2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            printer.Clear();

            if (Board.checkWin(player1.getID())) {
                frame.showPopUpMessage("", player1.getUserName() + " Has one! Congratulations!\n" + player1.getUserName() + " Has " + Board.fm.addWin(player1) + " Wins!");
                System.out.println();
                won = true;
            } else if (Board.checkWin(player2.getID())) {
                frame.showPopUpMessage("", player1.getUserName() + " Has one! Congratulations!\n" + player2.getUserName() + " Has " + Board.fm.addWin(player2) + " Wins!");
                won = true;
            } else {
                counter++;
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        frame.dispose();
    }

}
