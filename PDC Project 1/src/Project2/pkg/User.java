/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

/**
 *
 * @author zdtuc
 */
public class User {
    
    FileManagment fm = new FileManagment();
    private static int IDcounter;
    private int ID = 0;
    private String UserName;
    private int numberOfWins = 0;
    public int[] carrierLocation = new int[8];
    
    public User() {
        IDcounter = fm.getID();
        this.ID = IDcounter;
        this.UserName = null;
    }

    public void logIn(String username, int id, int numberOfWins) {
        this.setUserName(username);
        this.setID(id);
        this.setNumberOfWins(numberOfWins);
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfWins () {
        return this.numberOfWins;
    }
    
    public int getID() {
        return ID;
    }

    private void setID(int ID) {
        this.ID = ID;
    }
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setCarrierLocation(int pos, int num) {
        this.carrierLocation[pos] = num;
    }

    public int getCarrierLocationSpcae(int pos) {
        return carrierLocation[pos];
    }
    
    public String toString () {
        return this.ID + " " + this.UserName + " " + this.numberOfWins;
    }
    
}
