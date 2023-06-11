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

    ManuallyPlaceBoats m1;
    BoardManager board;
    
    public PlaceBoats() {
    }

    public void placeBoats(User user, BoardManager board) {
        PlaceBoatsFrame frame = new PlaceBoatsFrame();
        this.board = board;
        this.placeBoats(user, frame);
        frame.dispose();
    }

    //returns true if boats have been succesfully placed
    public void placeBoats(User user, PlaceBoatsFrame frame) {
        BoardWrapper tempBoard;
        BoardWrapper tempBoard3;
        BoatListWrapper boats;

        //the user is directly inputted this can be cleaned up
        if (board.Player1Board.getPlayerID() == user.getID()) { //sets tempboard to the reference to the users board
            tempBoard = board.Player1Board;
            tempBoard3 = board.Player1Board3;
            boats = board.p1Boats;
        } else {
            tempBoard = board.Player2Board;
            tempBoard3 = board.Player2Board3;
            boats = board.p2Boats;
        }

        frame.setBoard(tempBoard);

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
