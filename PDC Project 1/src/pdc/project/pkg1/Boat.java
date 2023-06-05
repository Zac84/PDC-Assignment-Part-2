/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
import java.util.ArrayList;

public abstract class Boat {
    
    private final String name;
    private final int InitialSize;
    //postion of the first part of the boat so 0[o] 1[o] 2[o] 3[o] the postion records the first the postion of 0 
    private int xPostion;
    private int yPostion;
    //orientation only be set to Either N,E,S,W that represet north, east, south and west.
    private String orientaion;

    //stores the string representation of the boat, 'o' represents alive segments, 'x' represents blow up segments 
    //an example of a fully intact boat is 'o o o o' a partially destroyed boat is 'o o x o' or 'x x o x' and a dead boat would be 'x x x x'
    String[] visual;

    public Boat(int InitialSize, String visual, String name) {
        this.InitialSize = InitialSize;
        this.visual = visual.split(" ");
        this.name = name;
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

    public String getOrientaion() {
        return orientaion;
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

    public void setOrientaion(String orientaion) {
        this.orientaion = orientaion;
    }

    
    
}
