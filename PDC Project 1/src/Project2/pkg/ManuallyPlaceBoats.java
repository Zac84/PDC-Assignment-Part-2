/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

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
    
    PlaceBoatsFrame frame;

    //inputs the arraylist of boats so that for every player you can make another.
    public ManuallyPlaceBoats(ArrayList boats, BoardWrapper board, User user, PlaceBoatsFrame frame) {
        this.Boats = boats;
        this.board = board;
        this.user = user;
        this.frame = frame;
        //maybe something like a place boats state in the gui
    }

    public void placeBoats() {

        InputChecker IC = new InputChecker();
        
        String desiredOrientation = "";
        String[] desiredPos;

        //needs to be GUI labels 
        //could be GUI class method that is something like manuallyPlaceBoats and has these in the labels
//        System.out.println("To place a boat, select a orientation (N, E, S, W), this is the direction the boat will face");
//        System.out.println(" and pick the coridinates x,y in the form (letter number)");
        

        for (Boat boat : Boats) {
            
            //updates the board / shows the board with the current stuff on it, for GUI it should just be like a GuiClass.UpdateBoard;
            printer.printBoard(board.getBoard(), board.getRow(), board.getColoum());
            //should be done within the gui, could be a method like guiClass.changeSomeLabel(boat.getName(), boat.getInitialSize()); 
            System.out.println("Please place you boat: " + boat.getName() + " Which is " + boat.getInitialSize() + " Long");
            System.out.println("Orientation: (N E S W): ");
            //this needs to be an input box of somesort that could be done with a GuiClass.getOrientation
            desiredOrientation = IC.check("N E S W , n e s w", true);
            System.out.println("Location: (x y): ");
            //this also needs to be an input box of someSort that could be done with GuiClass.getCourdinates();
            //the stuff from input checker should be implemented into the gui class.
            
            //get the coordinates like the shooting thing - see battleships main method
            desiredPos = IC.checkCoordinates().split(" ");
            //do normal stuff
            
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
                    //updated the board here
                    frame.updateButtons();
                    correctPos = true;
                } else {
                    //on the last one, it breaks and goes through anyway
                    //needs to be a text box thing in the GUI like GuiClass.sendTextBox("HFJHAHFSAUH");
                    frame.showPopUpMessage("", "Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                    System.out.println("Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                    
                    //needs to be guied
                    desiredPos = IC.checkCoordinates().split(" ");
                }
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
