/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.print.Printer;

/**
 *
 * @author zdtuc
 */
public class BoardManager {

    private final int row = 10;
    private final int coloum = 10;

    User Player1;
    User Player2;

    ManuallyPlaceBoats m1;

    //utility classes
    FileManagment fm = new FileManagment();
    PrinterClass printer = new PrinterClass();
    InputChecker IC = new InputChecker();
    DataBaseInteraction DB = new DataBaseInteraction();

    //each user has their own boat list.
    BoatListWrapper p1Boats;
    BoatListWrapper p2Boats;

    //each player has three boards
    //first board is the board they place their own ships on
    //second board is when they fire at the other players board
    //thrid board is a copy of the first board once all the ships are placed down and is used to check wins
    BoardWrapper Player1Board;
    BoardWrapper Player1Board2;
    BoardWrapper Player1Board3;

    BoardWrapper Player2Board;
    BoardWrapper Player2Board2;
    BoardWrapper Player2Board3;
    //put into wrapper classes so that they can be assosiated with a player.

    //this board will not include any of the other board stuff, it will simply be a normal 2d array with only the locations
    //this will be eaiser to manage and work around, after computations are done, the print class will take care of printing the whole board.
    public BoardManager() {

        //a user is created and then a board is created and assigned to the player
        // another board is created and assined to the user. this board is the second board that is the other uses board.
        this.Player1 = new User();
        Player1Board = new BoardWrapper(row, coloum, this.Player1.getID(), " ");
        Player1Board2 = new BoardWrapper(row, coloum, this.Player1.getID(), " ");
        Player1Board3 = new BoardWrapper(row, coloum, this.Player1.getID(), " ");

        //thesee are set to whatever the ID counter is 
        //when the users are created it needs to set these board ID's to the player ID at the same time.
        this.Player2 = new User();
        Player2Board = new BoardWrapper(row, coloum, this.Player2.getID(), " ");
        Player2Board2 = new BoardWrapper(row, coloum, this.Player2.getID(), " ");
        Player2Board3 = new BoardWrapper(row, coloum, this.Player2.getID(), " ");
    }

    public void fillUsers(User user, String TempInput) {

        Scanner scan = new Scanner(System.in);

        switch (TempInput) {
            case "L":
            case "l":
                System.out.println("Please enter your username");
                if (user == this.Player2) { //only happens for user 2 because of the order.
                    TempInput = IC.notTheSameUser(Player1.getUserName());
                } else {
                    TempInput = scan.nextLine();
                }
                fm.logIn(user, TempInput);
                TempInput = "";
                break;
            case "C":
            case "c":
                System.out.println("Please enter your username");
                TempInput = scan.nextLine();
                fm.createNew(TempInput, user);
                TempInput = "";
                break;
            default:
                throw new AssertionError();
        }

        //if the user is player 1, sets there boards id to the player 1's ID
        //if the user is player 2, sets there boards id to the player 2's iD
        if (user.getID() == this.Player1.getID()) {
            this.Player1Board.setPlayerID(this.Player1.getID());
            this.Player1Board2.setPlayerID(this.Player1.getID());
            this.Player1Board3.setPlayerID(this.Player1.getID());
            this.p1Boats = new BoatListWrapper(getPlayer1().getID());
            this.fillBoats(p1Boats);
        } else {
            this.Player2Board.setPlayerID(this.Player2.getID());
            this.Player2Board2.setPlayerID(this.Player2.getID());
            this.Player2Board3.setPlayerID(this.Player2.getID());
            this.p2Boats = new BoatListWrapper(getPlayer2().getID());
            this.fillBoats(p2Boats);
        }

    }

    public void fillBoats(BoatListWrapper boats) {
        //adds all the boats to the arrayList, this means that if any future boats were to be added it would still work
        boats.getBoats().add(new Carrier());

//        boats.getBoats().add(new BattleShip());
//        boats.getBoats().add(new BattleShip());
//
//        boats.getBoats().add(new Cruiser());
//        boats.getBoats().add(new Cruiser());
//        boats.getBoats().add(new Cruiser());
//
//        boats.getBoats().add(new Submarine());
//        boats.getBoats().add(new Submarine());
//        boats.getBoats().add(new Submarine());
//        boats.getBoats().add(new Submarine());
    }

    public User getPlayer1() {
        return Player1;
    }

    public void setPlayer1(User Player1) {
        this.Player1 = Player1;
    }

    public User getPlayer2() {
        return Player2;
    }

    public void setPlayer2(User Player2) {
        this.Player2 = Player2;
    }

    public void placeBoats(User user) {
        //make the other place boats private 

        //pull up the gui window that has the stuff and use it to input this stuff?
        //pulls up the gui with the user and then closes it once its finished.
        PlaceBoatsFrame frame = new PlaceBoatsFrame();
        this.placeBoats(user.getID(), user, frame);
        frame.dispose();

        //close frame
    }

    //returns true if boats have been succesfully placed
    public void placeBoats(int ID, User user, PlaceBoatsFrame frame) {
        BoardWrapper tempBoard;
        BoardWrapper tempBoard3;
        BoatListWrapper boats;

        //the user is directly inputted this can be cleaned up
        if (Player1Board.getPlayerID() == ID) { //sets tempboard to the reference to the users board
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

    //checks if this ID has won
    public boolean checkWin(int ID) {
        boolean won = true;

        BoardWrapper tempBoard2;
        BoardWrapper tempBoard3;

        if (Player1Board.getPlayerID() == ID) { //its player1's ID it sets the TempBoard's to players 1 shooting board and player 2's intial board
            tempBoard2 = this.Player1Board2; // shooting board of player 1 
            tempBoard3 = this.Player2Board3; //the original board of player 2 when it first got set up
        } else {
            tempBoard2 = this.Player2Board2;
            tempBoard3 = this.Player1Board3;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coloum; j++) {
                if (tempBoard3.getBoardSpaceString(i, j).equals("O") && !tempBoard2.getBoardSpaceString(i, j).equals("X")) {
                    return false;
                }
            }
        }

        return won;
    }

    //returns whether or not the carrier is alive
    public boolean carrierAlive(User user) {

        int counter = 0;
        BoardWrapper tempBoard;
        tempBoard = user == Player1 ? Player1Board : Player2Board;

        for (int i = 0; i < 4; i++) {
            if (!(tempBoard.getBoardSpaceString(user.getCarrierLocationSpcae(counter++), user.getCarrierLocationSpcae(counter++)).equals("X"))) {
                return true;
            }
        }

        return false;
    }

    public boolean shotAvalible(User user) {
        //returns if they can use there shot or not

        Carrier carrier = this.getUserCarrier(user);

        if (!carrierAlive(user)) {
            return false;
        }

        if (!carrier.largeShotAvalible()) {
            return false;
        }

        return true;
    }

    public Carrier getUserCarrier(User user) {
        BoatListWrapper boats;
        boats = user == Player1 ? p1Boats : p2Boats;

        Carrier carrier = null;

        for (Boat boat : boats.getBoats()) {
            if (boat instanceof Carrier) {
                carrier = (Carrier) boat;
            }
        }

        return carrier;
    }

    //CLEANI THIS, USE THE ABOVE METHOD SHOTAVALIBLE AND IT WILL BE SO MUCH BETTER
    public void useShot(int x, int y, User user) {

        //if the carrier is alive in the users board
        //finds the carrier in the boat list
        //checks to see if the big shot is avalible and if it is, uses it (changes it to false).
        x--;
        y--;
        Carrier carrier = this.getUserCarrier(user);
        System.out.println("Use shot coordinates" + x + ", " + y);

        if (shotAvalible(user)) {
            carrier.largeshot();

            BoardWrapper tempBoard; // the board that is being shot at
            BoardWrapper tempBoard2; // the board that is showing where u are shooting

            //put into a method
            if (user == Player1) {
                tempBoard = Player2Board;
                tempBoard2 = Player1Board2;
            } else {
                tempBoard = Player1Board;
                tempBoard2 = Player2Board2;
            }

            if (x == 0) {
                x++;
            } else if (x == coloum-1) {
                x--;
            }

            if (y == 0) {
                y++;
            } else if (y == row-1) {
                y--;
            }

            //goes across a 3x3 space
            System.out.println("shot was used");
            for (int i = (y - 1); i < (y + 2); i++) {
                for (int j = (x - 1); j < (x + 2); j++) {
                    if (tempBoard.getBoardSpaceString(j, i).equals(tempBoard.getFiller())) {
                        tempBoard2.setSpace(j, i, "M");
                    } else {
                        tempBoard.setSpace(j, i, "X");
                        tempBoard2.setSpace(j, i, "X");
                    }
                }
            }

        }

    }

    //returns true if the board has any boats on it.
    private boolean hasBoats(BoardWrapper board) {
        boolean hasBoats = false;

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColoum(); j++) {
                if (!board.getBoardSpaceString(j, i).equals(board.getFiller())) {
                    return true;
                }
            }
        }

        return hasBoats;
    }

}
