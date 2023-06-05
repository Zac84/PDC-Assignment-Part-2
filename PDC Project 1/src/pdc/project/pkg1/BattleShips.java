/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

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

        printer.printTitleScreen();

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

        Board.placeBoats(Board.Player1);
        printer.Clear();
        Board.placeBoats(Board.Player2);

        printer.Clear();
        System.out.println("Key:"
                + "\nO segments are alive boat segments"
                + "\nX are hit boat segments"
                + "\nM are misses\n\n");

        String[] desiredPos;

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
            desiredPos = IC.checkCoordinates().split(" ");

            int desiredXPos = (Integer.parseInt(desiredPos[0]) - 1);
            int desiredYPos = (Integer.parseInt(desiredPos[1]) - 1);

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
