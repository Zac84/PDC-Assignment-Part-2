/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
//this is a seperate class as this needs to be used in 2 seperate classes.
public class Colisions {

    //all reutrn false if they don't colide
    BoardWrapper board;
    BoardWrapper cpyBoard;
    String desiredOrientation;
    int desiredXPos;
    int desiredYPos;
    int BoatSize;
    int row;
    int coloum;

    public Colisions() {
        this.board = null;
        this.desiredOrientation = null;
        this.desiredXPos = -89123491;
        this.desiredYPos = -89123491;
        this.BoatSize = -89123491;
    }

    //done this way instead of in the constructor as it is just a utility class and I am trying to avoid wasting memory
    public void setNewInfo(BoardWrapper board, String desiredOrientation, int desiredXPos, int desiredYPos, int BoatSize) {
        this.board = board;
        this.desiredOrientation = desiredOrientation;
        this.desiredXPos = desiredXPos; // coloum
        this.desiredYPos = desiredYPos; // row
        this.BoatSize = BoatSize;
        this.row = board.getRow();
        this.coloum = board.getColoum();
    }

    //returns true if it doesn't collide with anything
    public boolean doesntColideWith() {
        return (this.inBoundrys() && this.hasSpace());
    }

    //returns true if x,y is the default space
    public boolean directColision(int x, int y) {
        boolean direct = true;
        if (board.getBoardSpaceString(x, y).equals(board.getFiller())) {
            direct = false;
        } else {
            System.out.println("failed direct colision at " + x + " " + y);
        }

        return direct;
    }

    public boolean checkAdjacentSquares(int x, int y, String place) {
        x = x + 1;
        y = y + 1;
        //as its checking the cpy of the board and not the real board.

        boolean failed = false;

        switch (place) {
            case "L": //checks the left, top and bottom squares
                failed = (!cpyBoard.getBoardSpaceString(x, y + 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x, y - 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x - 1, y).equals(this.board.getFiller()));
                break;
            case "R"://checks the right, top and bottom squares
                failed = (!cpyBoard.getBoardSpaceString(x, y + 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x, y - 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x + 1, y).equals(this.board.getFiller()));
                break;
            case "LR"://checks the left and right squares
                failed = (!cpyBoard.getBoardSpaceString(x + 1, y).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x - 1, y).equals(this.board.getFiller()));
                break;
            case "T"://checks the top, left and right squares
                failed = (!cpyBoard.getBoardSpaceString(x, y - 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x + 1, y).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x - 1, y).equals(this.board.getFiller()));
                break;
            case "B"://checks the bottom, left and right squares
                failed = (!cpyBoard.getBoardSpaceString(x, y + 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x - 1, y).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x + 1, y).equals(this.board.getFiller()));
                break;
            case "TB"://checks the top and bottom squares
                failed = (!cpyBoard.getBoardSpaceString(x, y + 1).equals(this.board.getFiller()) || !cpyBoard.getBoardSpaceString(x, y - 1).equals(this.board.getFiller()));
                break;
        }

        System.out.println("Checking Adjacent Squares: " + failed + " returns true if failed");
        //returns true if it failed
        return failed;
    }

    //checks if the desired placement has any other ships within the 1 space perimiter
    //if returns true it means there are no other ships in the 1 pixel perimiter
    //returns false if it failed
    public boolean hasSpace() {

        boolean passed = true;

        //the real board starts at 1,1 and ends at 10,10
        cpyBoard = new BoardWrapper(row + 2, coloum + 2, 2, " ");
        //cpy board is made so that when the colisions are being checked, it doesn't check something out of range and break it.

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColoum(); j++) {
                cpyBoard.setSpace((i + 1), (j + 1), board.getBoardSpaceString(i, j));
            }
        }

        switch (desiredOrientation) {
            case "n":
            case "N":
                //iterates over the length of the boat and checks that sides adjacent squares to see if they are default
                for (int o = 0; o < BoatSize; o++) {
                    int tempY = (desiredYPos - o);
                    int tempX = desiredXPos;

                    System.out.println("checking adjacent squares to " + tempX + " " + tempY);

                    if (directColision(tempX, tempY)) {
                        passed = false;
                        break;
                    }

                    if (o == 0) {
                        if (checkAdjacentSquares(tempX, tempY, "B")) {
                            passed = false;
                        }
                    } else if (o == BoatSize - 1) {
                        if (checkAdjacentSquares(tempX, tempY, "T")) {
                            passed = false;
                        }
                    } else {
                        if (checkAdjacentSquares(tempX, tempY, "LR")) {
                            passed = false;
                        }
                    }

                    if (!passed) {
                        break;
                    }
                }
                break;
            case "e":
            case "E":
                for (int o = 0; o < BoatSize; o++) {
                    int tempY = desiredYPos;
                    int tempX = (desiredXPos + o);

                    System.out.println("checking adjacent squares to " + tempX + " " + tempY);

                    if (directColision(tempX, tempY)) {
                        passed = false;
                        break;
                    }

                    if (o == 0) {
                        if (checkAdjacentSquares(tempX, tempY, "L")) {
                            passed = false;
                        }
                    } else if (o == BoatSize - 1) {
                        if (checkAdjacentSquares(tempX, tempY, "R")) {
                            passed = false;
                        }
                    } else {
                        if (checkAdjacentSquares(tempX, tempY, "TB")) {
                            passed = false;
                        }
                    }
                    if (!passed) {
                        break;
                    }
                }
                break;
            case "s":
            case "S":
                for (int o = 0; o < BoatSize; o++) {
                    int tempY = (desiredYPos + o);
                    int tempX = desiredXPos;

                    if (directColision(tempX, tempY)) {
                        passed = false;
                        break;
                    }

                    if (o == 0) {
                        if (checkAdjacentSquares(tempX, tempY, "T")) {
                            passed = false;
                        }
                    } else if (o == BoatSize - 1) {
                        if (checkAdjacentSquares(tempX, tempY, "B")) {
                            passed = false;
                        }
                    } else {
                        if (checkAdjacentSquares(tempX, tempY, "LR")) {
                            passed = false;
                        }
                    }
                    if (!passed) {
                        break;
                    }
                }
                break;
            case "w":
            case "W":
                for (int o = 0; o < BoatSize; o++) {
                    int tempY = desiredYPos;
                    int tempX = (desiredXPos - o);

                    System.out.println("checking adjacent squares to " + tempX + " " + tempY);

                    if (directColision(tempX, tempY)) {
                        passed = false;
                        break;
                    }

                    if (o == 0) {
                        if (checkAdjacentSquares(tempX, tempY, "R")) {
                            passed = false;
                        }
                    } else if (o == BoatSize - 1) {
                        if (checkAdjacentSquares(tempX, tempY, "L")) {
                            passed = false;
                        }
                    } else {
                        if (checkAdjacentSquares(tempX, tempY, "TB")) {
                            passed = false;
                        }
                    }
                    if (!passed) {
                        break;
                    }
                }
                break;
        }

        cpyBoard = null;

        if (!passed) {
            System.out.println("failed has space");
        }

        return passed;
    }

    //checks the desired placement is within the boundrys of board, so not bellow 1 or about 10
    public boolean inBoundrys() {
        boolean temp = false;

        switch (desiredOrientation) {
            case "n":
            case "N":
                System.out.println("Boat size " + this.BoatSize + " desiredYPos " + desiredYPos);
                if ((withinColoums(coloum) && desiredYPos <= 9 && desiredYPos >= (BoatSize-1))) { //works
                    temp = true;
                }
                break;
            case "e":
            case "E":
                if ((withinRows(row) && desiredXPos >= 0 && desiredXPos <= coloum - BoatSize)) { //works
                    temp = true;
                }
                break;
            case "s":
            case "S":
                if ((withinColoums(coloum) && desiredYPos >= 0 && desiredYPos <= (row - BoatSize))) { // works
                    temp = true;
                }
                break;
            case "w":
            case "W":
                if ((withinRows(row) && desiredXPos >= (BoatSize - 1) && desiredXPos <= row)) { //boatsize - 1 because it its the start of the array and that starts at 0
                    temp = true;
                }
                break;
        }

        if (!temp) {
            System.out.println("failed in boundry");
        }

        return temp;
    }

    //checks that it doesn't go out of range on the y axis
    public boolean withinColoums(int coloum) {
        if(!(desiredXPos >= 0 && desiredXPos <= coloum)) {
            System.out.println("failed within coloums");
        }
        return (desiredXPos >= 0 && desiredXPos <= coloum);
    }

    //checks that it doesn't go out of range on the x axis 
    public boolean withinRows(int row) {
        if(!(desiredYPos >= 0 && desiredYPos <= row)) {
            System.out.println("failed within rows");
        }
        return (desiredYPos >= 0 && desiredYPos <= row);
    }
}
