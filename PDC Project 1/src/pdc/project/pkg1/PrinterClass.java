/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
public class PrinterClass {

    //class is for printing different things like the board depending on the boats and their positions
    //working
    public void Clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    //working
    //source of ascii ART "https://patorjk.com/software/taag/#p=display&h=2&f=Doom&t=Battle%20Ships"
    public void printTitleScreen() {
        System.out.println("______       _   _   _        _____ _     _           ");
        System.out.println("| ___ \\     | | | | | |      /  ___| |   (_)          ");
        System.out.println("| |_/ / __ _| |_| |_| | ___  \\ `--.| |__  _ _ __  ___ ");
        System.out.println("| ___ \\/ _` | __| __| |/ _ \\  `--. \\ '_ \\| | '_ \\/ __|");
        System.out.println("| |_/ / (_| | |_| |_| |  __/ /\\__/ / | | | | |_) \\__ \\");
        System.out.println("\\____/ \\__,_|\\__|\\__|_|\\___| \\____/|_| |_|_| .__/|___/");
        System.out.println("                                           | |        ");
        System.out.println("                                           |_|        ");
    }

    //used to print the board with stuff on it 
    //method overloading
    //the inputted array will be the locations of everything with no other clutter, it takes this array and then
    //prints it out with the rest of the board.
    // i is the coloums, j is the place rows 
    public void printBoard(String[][] CurrentBoard, int row, int coloum) {
        this.printStartOfTable();

        boolean tenth = false;

        int counter = 1;
        for (int j = 0; j < row; j++) {
            if (counter == 10) {
                System.out.print("" + counter++ + "| ");
                tenth = true;
            } else {
                System.out.print(" " + counter++ + "| ");
            }

            for (int i = 0; i < coloum; i++) {
                System.out.print(CurrentBoard[i][j] + " | ");
            }

            if (!tenth) {
                System.out.println("\n  |---+---+---+---+---+---+---+---+---+---+");
            } else {
                System.out.println("\n  =========================================");
            }
        }

    }

    public void printStartOfTable() {
        //prints start of table
        System.out.println("  =========================================\n"
                + "  | a | b | c | d | e | f | g | h | i | j |\n"
                + "  |---------------------------------------|");
    }

    public void printDoubleBoard(BoardWrapper PlayerBoard1, BoardWrapper PlayerBoard2, String player1, String player2) {
        System.out.println("\n  =========================================     =========================================\n"
                + "  | a | b | c | d | e | f | g | h | i | j |     | a | b | c | d | e | f | g | h | i | j |\n"
                + "  |---------------------------------------|     |---------------------------------------|");

        boolean tenth = false;

        int counter = 1;
        int counter2 = 1;
        for (int j = 0; j < PlayerBoard1.getRow(); j++) {

            if (counter == 10) {
                System.out.print(counter++ + "| ");
                tenth = true;
            } else {
                System.out.print(" " + counter++ + "| ");
            }

            for (int i = 0; i < PlayerBoard1.getColoum(); i++) {
                System.out.print(PlayerBoard1.getBoardSpaceString(i, j) + " | ");
            }
            System.out.print("  ");

            if (counter2 == 10) {
                System.out.print(counter2++ + "| ");
                tenth = true;
            } else {
                System.out.print(" " + counter2++ + "| ");
            }

            for (int i = 0; i < PlayerBoard1.getColoum(); i++) {
                System.out.print(PlayerBoard2.getBoardSpaceString(i, j) + " | ");
            }

            if (!tenth) {
                System.out.println("\n  |---+---+---+---+---+---+---+---+---+---+     |---+---+---+---+---+---+---+---+---+---+");
            } else {
                System.out.println("\n  =========================================     =========================================");
            }
        }
        System.out.println("            Left board: " + player1 + "'s Board    Right board: attacking " + player2 + "'s board");
    }

    //return true if any users new to login, false otherwise
    //Prints the intial log in screen 
    public void LogInScreen(User p1, User p2) {
        //if they have not been set yet they 

        System.out.println("Player 1: " + p1.getUserName());
        System.out.println("Player 2: " + p2.getUserName());
        System.out.println("");
        System.out.println("Both Players Must Login");

    }

    public void shootingScreen(String name) {
        System.out.println(name+ "'s turn");
        System.out.println("Please enter the cooridnates that you would like to shoot in the form (x y) eg (5 5): ");
        System.out.println("Type \"BOOM\" for a 3x3 shot that can only be used once (Carrier (longest ship) must be alive :))");
    }
    
    public void PleaseEnterCorrectInput(String l1, String l2) {
        System.out.println("Wrong input, please input " + l1 + " or " + l2 + " :)");
    }

    public void PrintShipPlacementMenu() {
        System.out.println("Manually Place ships? (M)");
        System.out.println("Done (D)");
    }

}
