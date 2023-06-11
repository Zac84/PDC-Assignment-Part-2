/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

/**
 *
 * @author zdtuc
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Boat {

    private final String name;
    private final int InitialSize;
    //postion of the first part of the boat so 0[o] 1[o] 2[o] 3[o] the postion records the first the postion of 0 
    private int xPostion;
    private int yPostion;
    //orientation only be set to Either N,E,S,W that represet north, east, south and west.
    public Orientation orientaionReal;
    private static final HashMap<String, Orientation> orientationMap = createOrientationMap();

    //stores the string representation of the boat, 'o' represents alive segments, 'x' represents blow up segments 
    //an example of a fully intact boat is 'o o o o' a partially destroyed boat is 'o o x o' or 'x x o x' and a dead boat would be 'x x x x'
    String[] visual;

    public Boat(int InitialSize, String visual, String name) {

        this.InitialSize = InitialSize;
        this.visual = visual.split(" ");
        this.name = name;
    }

    private static HashMap<String, Orientation> createOrientationMap() {
        HashMap<String, Orientation> map = new HashMap<>();
        map.put("n", new North());
        map.put("e", new East());
        map.put("s", new South());
        map.put("w", new West());
        return map;
    }

    public int calculateNewXPos(int distance) {
        return orientaionReal.calculateNewXPos(this, distance);
    }

    public int calculateNewYPos(int distance) {
        return orientaionReal.calculateNewYPos(this, distance);
    }

    public String getName() {
        return name;
    }

    public int getInitialSize() {
        return InitialSize;
    }

    public int getXPostion() {
        return xPostion;
    }

    public int getYPostion() {
        return yPostion;
    }
    
    public String[] getVisual() {
        return visual;
    }

    public void setxPostion(int xPostion) {
        this.xPostion = xPostion;
    }

    public void setyPostion(int yPostion) {
        this.yPostion = yPostion;
    }

    public void setOrientation(String orientationLetter) {
        orientaionReal = orientationMap.get(orientationLetter.toLowerCase());
        if (orientaionReal == null) {
            System.out.println("No orientationn exists");
        }
    }

    public void setLocation(String orientation, int x, int y) {
        this.setOrientation(orientation);
        this.xPostion = x;
        this.yPostion = y;
    }

}
