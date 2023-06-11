/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Project2.pkg;

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
public class UserManagementTest {

    DataBaseInteraction db;
    User user;
    UserManagement um;

    public UserManagementTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.db = new DataBaseInteraction("pdc");
        this.db.establishConnection();
        user = new User();
        um = new UserManagement(user, "testUser", db.getConnection(), db.getTableName());
    }

    @After
    public void tearDown() {
        this.db.deleteTable("pdc");
        this.db.closeConnections();
        user = null;
        um = null;
    }

    /**
     * Test of login method, of class UserManagement.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        //testing new user
        UserManagement instance = um;
        instance.login();
        //testing loggin in with same user
        String sameUsername = um.user.getUserName();
        um.setNewUser(new User(), sameUsername);
        instance.login();
        //
        assertTrue(um.rowExists(sameUsername));
    }

    /**
     * Test of setCredintials method, of class UserManagement.
     */
    @Test
    public void testSetCredintials() {
        System.out.println("setCredintials");
        UserManagement instance = um;
        instance.setNewCredintials(); //set credintials is in the setNewCredintials method.
        boolean worked = false;
        if (um.user.getID() + "" != null && um.user.getUserName() != null) {
            worked = true;
        }
        assertTrue(worked);
    }

    /**
     * Test of setNewCredintials method, of class UserManagement.
     */
    @Test
    public void testSetNewCredintials() {
        System.out.println("setNewCredintials");
        UserManagement instance = um;
        instance.setNewCredintials();
        boolean worked = false;
        if (um.user.getID() + "" != null && um.user.getUserName() != null) {
            worked = true;
        }
        assertTrue(worked);
    }

    /**
     * Test of rowExists method, of class UserManagement.
     */
    @Test
    public void testRowExists() {
        System.out.println("rowExists");
        UserManagement instance = um;
        instance.setNewCredintials(); //set credintials is in the setNewCredintials method.

        boolean expResult = false;
        boolean result = instance.rowExists("NotAUser");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRow method, of class UserManagement.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        String username = "testUser";
        um.setNewCredintials();
        UserManagement instance = um;
        String expResult = um.user.getID() + " " + username + " " + um.user.getNumberOfWins();
        String result = instance.getRow(username);
        assertEquals(expResult, result);
    }

}
