/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

/**
 *
 * @author zdtuc
 */
public class PlaceBoats {

    
    
    public PlaceBoats() {

    }

    public void placeBoats(User user) {
        PlaceBoatsFrame frame = new PlaceBoatsFrame();
        this.placeBoats(user, frame);
        frame.dispose();

        //close frame
    }

    //returns true if boats have been succesfully placed
    public void placeBoats(User user, PlaceBoatsFrame frame) {
        BoardWrapper tempBoard;
        BoardWrapper tempBoard3;
        BoatListWrapper boats;

        //the user is directly inputted this can be cleaned up
        if (Player1Board.getPlayerID() == user.getID()) { //sets tempboard to the reference to the users board
            tempBoard = Player1Board;
            tempBoard3 = Player1Board3;
            boats = p1Boats;
        } else {
            tempBoard = Player2Board;
            tempBoard3 = Player2Board3;
            boats = p2Boats;
        }

        frame.setBoard(tempBoard);
        //put frame in here and then only close when d is inputted.
        //will have to pass the frame into the manually place boats thing

        //reset the board
        //if you want you can add a reset button that just calls this
        //tempBoard.clear();
        //sends a frame so that, opening and closing can be controlled in here
        m1 = new ManuallyPlaceBoats(boats.getBoats(), tempBoard, user, frame);
        m1.placeBoats();

        //copys it into board 3
        for (int i = 0; i < tempBoard3.getRow(); i++) {
            for (int j = 0; j < tempBoard3.getColoum(); j++) {
                tempBoard3.setSpace(i, j, tempBoard.getBoardSpaceString(i, j));
            }
        }
    }

}
