/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
public class BoardWrapper {

    String[][] board;
    int PlayerID;
    int row;
    int coloum;
    String filler;

    public BoardWrapper(int row, int coloum, int PlayerID, String filler) {
        this.row = row;
        this.coloum = coloum;
        this.board = new String[row][coloum];
        this.filler = filler;
        this.PlayerID = PlayerID;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coloum; j++) {
                this.board[i][j] = filler;
            }
        }

    }

    public void clear() {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColoum(); j++) {
                this.setSpace(j, i, this.filler);
            }
        }
    }

    public void setSpace(int x, int y, String replacement) {
        this.board[x][y] = replacement;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getBoardSpaceString(int x, int y) {
        return board[x][y];
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }

    public int getRow() {
        return row;
    }

    public int getColoum() {
        return coloum;
    }

    public String getFiller() {
        return filler;
    }

}
