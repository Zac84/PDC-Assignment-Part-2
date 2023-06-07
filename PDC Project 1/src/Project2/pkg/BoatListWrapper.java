/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.util.ArrayList;

/**
 *
 * @author zdtuc
 */
public class BoatListWrapper {

    ArrayList<Boat> boats = new ArrayList<Boat>();
    int ID;
    
    public BoatListWrapper(int ID) {
        this.ID = ID;
    }

    public ArrayList<Boat> getBoats() {
        return boats;
    }
    
}
