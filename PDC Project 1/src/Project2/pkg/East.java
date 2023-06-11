/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

/**
 *
 * @author zdtuc
 */
public class East extends Orientation {

    @Override
    public int calculateNewXPos(Boat boat, int distance) {
        return boat.getXPostion() + distance;
    }

    @Override
    public int calculateNewYPos(Boat boat, int distance) {
        return boat.getYPostion();
    }
}
