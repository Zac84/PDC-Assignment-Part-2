/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zdtuc
 */
//utility class, so doesn't need to have multiple objects created
public class ManuallyPlaceBoats {

    ArrayList<Boat> Boats;
    BoardWrapper board;
    PrinterClass printer = new PrinterClass();
    Colisions colisions = new Colisions();
    User user;

    //inputs the arraylist of boats so that for every player you can make another.
    public ManuallyPlaceBoats(ArrayList boats, BoardWrapper board, User user) {
        this.Boats = boats;
        this.board = board;
        this.user = user;
    }

    public BoardWrapper placeBoats() {

        InputChecker IC = new InputChecker();

        String desiredOrientation = "";
        String[] desiredPos;

        System.out.println("To place a boat, select a orientation (N, E, S, W), this is the direction the boat will face");
        System.out.println(" and pick the coridinates x,y in the form (letter number)");
        

        for (Boat boat : Boats) {
            printer.printBoard(board.getBoard(), board.getRow(), board.getColoum());
            System.out.println("Please place you boat: " + boat.getName() + " Which is " + boat.getInitialSize() + " Long");
            System.out.println("Orientation: (N E S W): ");
            desiredOrientation = IC.check("N E S W , n e s w", true);
            System.out.println("Location: (x y): ");
            desiredPos = IC.checkCoordinates().split(" ");

            boolean correctPos = false;

            while (!correctPos) {
                int desiredXPos = (Integer.parseInt(desiredPos[0]) - 1);
                int desiredYPos = (Integer.parseInt(desiredPos[1]) - 1);

                colisions.setNewInfo(board, desiredOrientation, desiredXPos, desiredYPos, boat.getInitialSize());
                if (colisions.doesntColideWith()) {
                    boat.setxPostion(desiredXPos);
                    boat.setyPostion(desiredYPos);
                    boat.setOrientaion(desiredOrientation);
                    //draw onto board
                    this.drawOntoBoard(boat);
                    correctPos = true;
                } else {
                    //on the last one, it breaks and goes through anyway
                    System.out.println("Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                    desiredPos = IC.checkCoordinates().split(" ");
                }
            }
        }
        printer.printBoard(board.getBoard(), board.getRow(), board.getColoum());

        return board;
    }

    public void drawOntoBoard(Boat boat) {

        int tempX = boat.getXPostion();
        int tempY = boat.getYPostion();
        int carrierCounter = 0;

        for (int i = 0; i < boat.getInitialSize(); i++) {

            switch (boat.getOrientaion()) {
                case "n":
                case "N":
                    tempY = boat.getYPostion() - i;
                    break;
                case "e":
                case "E":
                    tempX = boat.getXPostion() + i;
                    break;
                case "s":
                case "S":
                    tempY = boat.getYPostion() + i;
                    break;
                case "w":
                case "W":
                    tempX = boat.getXPostion() - i;
                    break;
                default:
                    throw new AssertionError();
            }
            board.setSpace(tempX, tempY, boat.visual[i]);

            if (boat.getName() == "Carrier") {
                user.setCarrierLocation(carrierCounter++, tempX);
                user.setCarrierLocation(carrierCounter++, tempY);
            }
        }

    }

}
