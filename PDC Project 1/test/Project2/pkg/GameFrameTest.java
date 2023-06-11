/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Project2.pkg;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zdtuc
 */
public class GameFrameTest {

    GameFrame instance;
    
    public GameFrameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new GameFrame();
        instance.setVisible(false);
    }

    @After
    public void tearDown() {
        instance.dispose();
    }

    /**
     * Test of updateLabels method, of class GameFrame.
     */
    @Test
    public void testUpdateLabels() {
        System.out.println("updateLabels");
        String player1 = "zac";
        String player2 = "zac2";

        instance.updateLabels(player1, player2);
        boolean labelsUpdated = false;
        instance = new GameFrame();
        instance.setVisible(false);
        if (instance.labels[0].getText().equals("'s turn" + player1) && instance.labels[2].getText().equals("'s Board" + player2)) {
            labelsUpdated = true;
        }

        assertTrue(labelsUpdated);
    }

    /**
     * Test of setUser method, of class GameFrame.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        GameFrame instance = new GameFrame();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of boomButtonPress method, of class GameFrame.
     */
    @Test
    public void testBoomButtonPress() {
        System.out.println("boomButtonPress");
        GameFrame instance = new GameFrame();
        instance.boomButtonPress();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCoordinates method, of class GameFrame.
     */
    @Test
    public void testCheckCoordinates() {
        System.out.println("checkCoordinates");
        String input = "";
        GameFrame instance = new GameFrame();
        boolean expResult = false;
        boolean result = instance.checkCoordinates(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordinates method, of class GameFrame.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        GameFrame instance = new GameFrame();
        int[] expResult = null;
        int[] result = instance.getCoordinates();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeEnabledState method, of class GameFrame.
     */
    @Test
    public void testChangeEnabledState() {
        System.out.println("changeEnabledState");
        boolean state = false;
        GameFrame instance = new GameFrame();
        instance.changeEnabledState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setButtons method, of class GameFrame.
     */
    @Test
    public void testSetButtons() {
        System.out.println("setButtons");
        JButton[][] buttons = null;
        BoardWrapper normalBoard = null;
        GameFrame instance = new GameFrame();
        instance.setButtons(buttons, normalBoard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateButtons method, of class GameFrame.
     */
    @Test
    public void testUpdateButtons() {
        System.out.println("updateButtons");
        BoardWrapper player1Board = null;
        BoardWrapper player2Board = null;
        GameFrame instance = new GameFrame();
        instance.updateButtons(player1Board, player2Board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleButtonClick method, of class GameFrame.
     */
    @Test
    public void testHandleButtonClick() {
        System.out.println("handleButtonClick");
        ActionEvent e = null;
        GameFrame instance = new GameFrame();
        instance.handleButtonClick(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allowBoomButton method, of class GameFrame.
     */
    @Test
    public void testAllowBoomButton() {
        System.out.println("allowBoomButton");
        boolean allowed = false;
        GameFrame instance = new GameFrame();
        instance.allowBoomButton(allowed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showPopUpMessage method, of class GameFrame.
     */
    @Test
    public void testShowPopUpMessage() {
        System.out.println("showPopUpMessage");
        String title = "";
        String message = "";
        GameFrame instance = new GameFrame();
        instance.showPopUpMessage(title, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intructionsMessage method, of class GameFrame.
     */
    @Test
    public void testIntructionsMessage() {
        System.out.println("intructionsMessage");
        GameFrame instance = new GameFrame();
        instance.intructionsMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
