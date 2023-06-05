/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
public class Carrier extends Boat {

    public Carrier() {
        super(4, "O O O O", "Carrier");

    }

//large shot is only avalible on this specific boat
    boolean largeshot = true;

    //returns true if large shop is still avalible
    public boolean largeShotAvalible() {
        return largeshot;
    }

    //uses the large shot
    public void largeshot() {
        if (largeshot) {
            this.largeshot = false;
        }
    }

}
