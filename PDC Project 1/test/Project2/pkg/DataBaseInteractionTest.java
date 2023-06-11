/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Project2.pkg;

import java.sql.Connection;
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
public class DataBaseInteractionTest {
    
    DataBaseInteraction db;
    
    public DataBaseInteractionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        db = new DataBaseInteraction("pdc");
        db.establishConnection();
    }
    
    @After
    public void tearDown() {
        //deletes the table everytime after you use it
        db.deleteTable("pdc");
        db.closeConnections();
        
    }


    /**
     * Test of establishConnection method, of class DataBaseInteraction.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        DataBaseInteraction instance = new DataBaseInteraction("pdc");
        instance.establishConnection();
        assertNotNull(instance.getConnection());
        instance.closeConnections();
    }

//    /**
//     * Test of addRow method, of class DataBaseInteraction.
//     */
    @Test
    public void testAddRow() {
        System.out.println("addRow");
        DataBaseInteraction instance = db;
        String newUserName = "BOB";
        instance.addRow(newUserName);
        assertTrue(db.rowExists(newUserName));
    }

    /**
     * Test of getConnection method, of class DataBaseInteraction.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DataBaseInteraction instance = db;
        assertNotNull(db.getConnection());
    }

    /**
     * Test of tableInitializer method, of class DataBaseInteraction.
     */
    @Test
    public void testTableInitializer() {
        System.out.println("tableInitializer");
        DataBaseInteraction instance = db;
        instance.tableInitializer();
        assertTrue(db.tableExists("pdc"));
    }

    /**
     * Test of tableExists method, of class DataBaseInteraction.
     */
    @Test
    public void testTableExists() {
        System.out.println("tableExists");
        DataBaseInteraction instance = db;
        boolean expResult = true;
        boolean result = instance.tableExists("pdc");
        assertEquals(expResult, result);
    }

    /**
     * Test of closeConnections method, of class DataBaseInteraction.
     */
    @Test
    public void testCloseConnections() {
        System.out.println("\ncloseConnections");
        db.closeConnections();
        boolean connection = false;
        if(db.getConnection() != null) {
            connection = true;
        }
        db.establishConnection();//needs to be reverted so that it can be closed again
        assertFalse(connection);
    }

    /**
     * Test of createTable method, of class DataBaseInteraction.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        DataBaseInteraction instance = db;
        String newTableName = "TestTable";
        instance.createTable(newTableName);
        assertTrue(instance.tableExists(newTableName));
    }

    /**
     * Test of rowExists method, of class DataBaseInteraction.
     */
    @Test
    public void testRowExists() {
        System.out.println("rowExists");
        String username = "Man";
        DataBaseInteraction instance = db;
        boolean expResult = false;
        boolean result = instance.rowExists(username);
        assertEquals(expResult, result);
    }
    
}
