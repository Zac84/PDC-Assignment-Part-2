/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zdtuc
 */
//utility class, so doesn't need to have multiple objects created
public class ManuallyPlaceBoats {

    ArrayList<Boat> Boats;
    BoardWrapper board;
//    PrinterClass printer = new PrinterClass();
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

        String gotCoords = null;
        String desiredOrientation;

        for (Boat boat : Boats) {
            frame.setLabels(user.getUserName(), boat.getName(), boat.getInitialSize() + "");
            boolean correctPos = false;

            while (!correctPos) {

                while (gotCoords == null) {
                    gotCoords = frame.getCoordinates();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ManuallyPlaceBoats.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String[] placement = gotCoords.split(" ");
                desiredOrientation = placement[0];

                int desiredXPos = (Integer.parseInt(map.get(placement[1].toUpperCase())) - 1);
                int desiredYPos = (Integer.parseInt(placement[2]) - 1);

                colisions.setNewInfo(board, desiredOrientation, desiredXPos, desiredYPos, boat.getInitialSize());
                if (colisions.doesntColideWith()) {
                    boat.setLocation(desiredOrientation, desiredXPos, desiredYPos);
                    //draw onto board
                    this.drawOntoBoard(boat);
                    //updated the board here
                    frame.updateButtons();
                    correctPos = true;
                } else {
                    frame.showPopUpMessage("", "Please make sure boat doesn't go out of boundys and has a 1 pixel radius of free space");
                }
                gotCoords = null;
            }
        }
    }

    public void drawOntoBoard(Boat boat) {

        int carrierCounter = 0;

        for (int i = 0; i < boat.getInitialSize(); i++) {

            int tempX = boat.calculateNewXPos(i);
            int tempY = boat.calculateNewYPos(i);

            board.setSpace(tempX, tempY, boat.visual[i]);

            if (boat.getName().equals("Carrier")) {
                user.setCarrierLocation(carrierCounter++, tempX);
                user.setCarrierLocation(carrierCounter++, tempY);
            }
        }

    }

}
