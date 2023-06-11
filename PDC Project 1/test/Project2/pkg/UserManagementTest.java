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
        instance.setCredintials();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNewCredintials method, of class UserManagement.
     */
    @Test
    public void testSetNewCredintials() {
        System.out.println("setNewCredintials");
        UserManagement instance = new UserManagement();
        instance.setNewCredintials();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rowExists method, of class UserManagement.
     */
    @Test
    public void testRowExists() {
        System.out.println("rowExists");
        String username = "";
        UserManagement instance = new UserManagement();
        boolean expResult = false;
        boolean result = instance.rowExists(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRow method, of class UserManagement.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        String username = "";
        UserManagement instance = new UserManagement();
        String expResult = "";
        String result = instance.getRow(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
