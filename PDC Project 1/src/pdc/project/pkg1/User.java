/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

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
    //records the postion of the carrier
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
    
    //add in file management the win count, idk what to do, falling asleep.*

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
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
    
}
