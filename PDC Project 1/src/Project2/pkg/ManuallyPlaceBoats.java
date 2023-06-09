/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<String, String> map = new HashMap<String, String>();

    PlaceBoatsFrame frame;

    //inputs the arraylist of boats so that for every player you can make another.
    public ManuallyPlaceBoats(ArrayList boats, BoardWrapper board, User user, PlaceBoatsFrame frame) {
        this.Boats = boats;
        this.board = board;
        this.user = user;
        this.frame = frame;

        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "4");
        map.put("E", "5");
        map.put("F", "6");
        map.put("G", "7");
        map.put("H", "8");
        map.put("I", "9");
        map.put("J", "10");
    }

    public void placeBoats() {

        InputChecker IC = new InputChecker();

        String[] placement = null;
        String gotCoords = null;
        String desiredOrientation = "";
        String[] desiredPos;

        //needs to be GUI labels 
        //could be GUI class method that is something like manuallyPlaceBoats and has these in the labels
//        System.out.println("To place a boat, select a orientation (N, E, S, W), this is the direction the boat will face");
//        System.out.println(" and pick the coridinates x,y in the form (letter number)");
        for (Boat boat : Boats) {

            printer.printBoard(board.getBoard(), board.getRow(), board.getColoum());

            frame.setLabels(user.getUserName(), boat.getName(), boat.getInitialSize() + "");

            //this needs to be an input box of somesort that could be done with a GuiClass.getOrientation
//            desiredOrientation = IC.check("N E S W , n e s w", true);
//            //get the coordinates like the shooting thing - see battleships main method
//            desiredPos = IC.checkCoordinates().split(" ");
            boolean correctPos = false;

            while (!correctPos) {

                while (gotCoords == null) {
                    gotCoords = frame.getCoordinates();
                }
                placement = gotCoords.split(" ");

                desiredOrientation = placement[0];
                int desiredXPos = (Integer.parseInt(map.get(placement[1].toUpperCase())) - 1);
                int desiredYPos = (Integer.parseInt(placement[2]) - 1);

                colisions.setNewInfo(board, desiredOrientation, desiredXPos, desiredYPos, boat.getInitialSize());
                if (colisions.doesntColideWith()) {
                    boat.setxPostion(desiredXPos);
                    boat.setyPostion(desiredYPos);
                    boat.setOrientaion(desiredOrientation);
                    //draw onto board
                    this.drawOntoBoard(boat);
                    //updated the board here
                    frame.updateButtons();
                    correctPos = true;
                } else {
                    //on the last one, it breaks and goes through anyway
                    //needs to be a text box thing in the GUI like GuiClass.sendTextBox("HFJHAHFSAUH");
                    frame.showPopUpMessage("", "Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                    System.out.println("Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                }
                placement = null;
                gotCoords = null;
            }

        }
        //prints the board once done
        //should update the gui so that the board is printed iwth 
//        printer.printBoard(board.getBoard(), board.getRow(), board.getColoum());
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
