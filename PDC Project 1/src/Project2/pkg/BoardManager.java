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
        String TempInput = "";

        //pull up the gui window that has the stuff and use it to input this stuff?
        //pulls up the gui with the user and then closes it once its finished.
        
        
        boolean placed = false;
        while (!(TempInput.equalsIgnoreCase("d") && placed)) { //while they haven't finished placing boats and it makes sure they have finished placing boats (placed)
            System.out.println(user.getUserName() + " Boat Placements: "); //put label into the gui
            printer.PrintShipPlacementMenu();// print with label 
            TempInput = IC.check("M D , m d", true); //don't need to do this, just have button that is done but has pop up window if they haven't placed yet.
            placed = this.placeBoats(user.getID(), TempInput, placed, user);
        }
        
        //close frame
    }

    //returns true if boats have been succesfully placed
    public boolean placeBoats(int ID, String decsion, boolean placed, User user) {
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

        //TODO: Change into gui components
        //changes the users board;
        switch (decsion) {
            case "M":// m for Manually place
            case "m":
                if (this.hasBoats(tempBoard)) { // if the board already has boats been placed on it, it it clears the board.
                    tempBoard.clear();
                }
                //see ManuallyPlaceBoats for TODO 
                m1 = new ManuallyPlaceBoats(boats.getBoats(), tempBoard, user);
                m1.placeBoats();
                placed = true;
                break;
            case "d":// done
            case "D":
                if (!placed) {
                    //needs to be a message box gui typa thing or something
                    System.out.println("Please place ships first :)");
                } else {
                    for (int i = 0; i < tempBoard3.getRow(); i++) {
                        for (int j = 0; j < tempBoard3.getColoum(); j++) {
                            tempBoard3.setSpace(i, j, tempBoard.getBoardSpaceString(i, j));
                        }
                    }
                }
                break;
            default:
                break;
        }

        if (!placed) {
            System.out.println("Please place ships first :)");
        }
        return placed;
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


    public boolean carrierAlive(User user) {
        //returns whether or not the carrier is alive
        boolean isAlive = false;

        int counter = 0;
        BoardWrapper tempBoard;
        tempBoard = user == Player1 ? Player1Board : Player2Board;

        for (int i = 0; i < 4; i++) {
            if (!(tempBoard.getBoardSpaceString(user.getCarrierLocationSpcae(counter++), user.getCarrierLocationSpcae(counter++)).equals("X"))) {
                isAlive = true;
                break;
            }
        }

        return isAlive;
    }

    public boolean shotAvalible(User user) {
        //returns if they can use there shot or not
        BoatListWrapper boats;
        boats = user == Player1 ? p1Boats : p2Boats;

        Carrier carrier = null;
        
        if (!carrierAlive(user)) {
            return false;
        }

        for (Boat boat : boats.getBoats()) {
            if (boat instanceof Carrier) {
                carrier = (Carrier) boat;
            }
        }
        
        if (!carrier.largeShotAvalible()) {
            return false;
        }

        return true;
    }

    
    //CLEANI THIS, USE THE ABOVE METHOD SHOTAVALIBLE AND IT WILL BE SO MUCH BETTER
    public void useShot(int x, int y, User user) {
        //uses the shot from the users board.

        BoatListWrapper boats;
        boats = user == Player1 ? p1Boats : p2Boats;

        //if the carrier is alive in the users board
        //finds the carrier in the boat list
        //checks to see if the big shot is avalible and if it is, uses it (changes it to false).
        Boolean NotUsed = true;
        if (carrierAlive(user)) {
            for (Boat boat : boats.getBoats()) {
                if (boat instanceof Carrier) {
                    Carrier carrier = (Carrier) boat;
                    if (carrier.largeShotAvalible()) {
                        carrier.largeshot();
                        BoardWrapper tempBoard; // the board that is being shot at
                        BoardWrapper tempBoard2; // the board that is showing where u are shooting

                        if (user == Player1) {
                            tempBoard = Player2Board;
                            tempBoard2 = Player1Board2;
                        } else {
                            tempBoard = Player1Board;
                            tempBoard2 = Player2Board2;
                        }

                        //if the user is player 1, the board that is going to be shot at is player 2 board or vise versa.
                        System.out.println("shots will always be entirly in the board, if you choose to shoot a boarding square it will aim at the inner square, doing the same or potentially more damage");

                        if (x == 0) {
                            x++;
                        } else if (x == coloum) {
                            x--;
                        }

                        if (y == 0) {
                            y++;
                        } else if (y == row) {
                            y--;
                        }

                        //goes across a 3x3 space
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
                    } else {
                        System.out.println("Large shot has been used already :( ");
                    }
                    break;
                }
            }
        } else {
            System.out.println("Carrier is not alive");
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
