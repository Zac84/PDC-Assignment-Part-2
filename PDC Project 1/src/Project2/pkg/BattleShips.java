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
        printer.LogInScreen(Board.Player1, Board.Player2);
        System.out.println("Player 1: (L / C) ");
        TempInput = IC.check("L C , l c", true);
        Board.fillUsers(Board.Player1, TempInput);

        System.out.println("Player 2: (L / C)");
        TempInput = IC.check("L C , l c", true);
        Board.fillUsers(Board.Player2, TempInput);

//        BFrame frame = new BFrame(Board.getPlayer1(), Board.getPlayer2());

        //Board manager place boats
        //see board manager placeBoats for TODO
        Board.placeBoats(Board.Player1);
        printer.Clear();
        Board.placeBoats(Board.Player2);

        printer.Clear();
        System.out.println("Key:"
                + "\nO segments are alive boat segments"
                + "\nX are hit boat segments"
                + "\nM are misses\n\n");

        //starts up the gui window
        GameFrame frame = new GameFrame(Board.getPlayer1(), Board.getPlayer2());
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

            printer.printDoubleBoard(tempPlayer1Board, tempPlayer1Board2, player1.getUserName(), player2.getUserName());
            printer.shootingScreen(player1.getUserName());

            frame.updateButtons(tempPlayer1Board, tempPlayer1Board2);
            frame.changeEnabledState(true);

            //this needs to be chaned to something like GuiClass.Getcourdinates or something.
            while (desiredPos == null) {
                desiredPos = frame.getCoordinates();
                //stops it from continuing until courdinates are found.
            }


            int desiredXPos = desiredPos[0];
            int desiredYPos = desiredPos[1];

            System.out.println("desired pos: " + desiredXPos + ", " + desiredYPos);
            
            //this normal stuff can happen and then it needs to be relayed to the gui
            
            //only display the gui element for the boom if its avalible.
            if(!Board.shotAvalible(player1)) {
                //if shot isn't avalible, dont show the button
                //implement
            }
                
                 
            if (desiredPos.length == 3) {
                Board.useShot(desiredXPos, desiredYPos, player1);
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
                System.out.println(player1.getUserName() + " Has one! Congratulations!");
                System.out.println(player1.getUserName() + " Has " + Board.fm.addWin(player1) + " Wins!");
                won = true;
            } else if (Board.checkWin(player2.getID())) {
                System.out.println(player2.getUserName() + " Has one! Congratulations!");
                System.out.println(player2.getUserName() + " Has " + Board.fm.addWin(player2) + " Wins!");
                won = true;
            } else {
                counter++;
            }
        }

    }

}