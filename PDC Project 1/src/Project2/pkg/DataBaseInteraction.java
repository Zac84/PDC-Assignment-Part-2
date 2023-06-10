/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author zdtuc
 */
public class DataBaseInteraction {

    public Connection con;
    public static final String URL = "jdbc:derby:pdc;create=true";
    public static final String USERNAME = "pdc";
    public static final String PASSWORD = "pdc";

    public DataBaseInteraction(String tableName) {
        this.establishConnection();
        this.tableInitializer(tableName);
        this.closeConnections();
    }

    public static void main(String[] args) {
        DataBaseInteraction dbm = new DataBaseInteraction("users");
    }
    
    //to establish connection with embeded database
    public void establishConnection() {
        if (this.con == null) {
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected: " + con);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public Connection getConnection () {
        if(this.con == null) {
            return null;
        }
        
        return this.con;
    }

    public void tableInitializer(String tableName) {
        if (this.con == null) {
            return;
        }

        if (!this.tableExists(tableName)) {
            return;
        }
        
        this.createTable();

    }

    public boolean tableExists(String tableName) {
        boolean tableExists = false;
        String checkTableQuery = "SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = '" + tableName + "'";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(checkTableQuery)) {
            tableExists = resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tableExists;
    }

    public void closeConnections() {
        if (con != null) {
            try {
                con.close();
                System.out.println("closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void createTable() {
        String createTableQuery = "CREATE TABLE MyTable (ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name VARCHAR(100), Wins INT)";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
