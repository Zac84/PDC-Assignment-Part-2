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
    public String tableName;

    public DataBaseInteraction(String tableName) {
        this.tableName = tableName;
        this.establishConnection();
//        this.deleteTable(tableName);
        this.tableInitializer();
//        this.addRow();
        this.viewAllData();
//        this.viewAllTables();
//        this.closeConnections();
    }

    public static void main(String[] args) {
        DataBaseInteraction dbm = new DataBaseInteraction("pdc");
//        UserManagement um = new UserManagement(new User(), "Mike", this.getConnection(), "");
        UserManagement userManager = new UserManagement(new User(), "MIKE", dbm.getConnection(), "pdc");
        userManager.login();
//        System.out.println(userManager.getRow("MIKE"));

    }

    //to establish connection with embeded database
    public void establishConnection() {
        if (this.con == null) {
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                System.out.println("Connected: " + con);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void addRow(String username) {
        String insertQuery = "INSERT INTO " + this.tableName + " (username, Wins) VALUES (?, ?)";
        try {
            PreparedStatement insertStatement = con.prepareStatement(insertQuery);
            insertStatement.setString(1, username);
            insertStatement.setInt(2, 1);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteTable(String tableName) {
        String dropTableQuery = "DROP TABLE " + tableName;
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(dropTableQuery);
            System.out.println("Table deleted successfully.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        if (this.con != null) {
            return this.con;
        }
        
        return null;
    }

    public void tableInitializer() {
        if (this.con == null) {
            return;
        }

        if (this.tableExists(this.tableName)) {
            return;
        }

        this.createTable(this.tableName);

    }

    //returns if the current table name exists
    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while (resultSet.next()) {
                String name = resultSet.getString("TABLE_NAME");
                String schema = resultSet.getString("TABLE_SCHEM");
                if (name.equalsIgnoreCase(tableName)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("BRO" + e.getMessage());
        }

        return false;
    }

    public void closeConnections() {
        if (con != null) {
            try {
                con.close();
                con = null;
//                System.out.println("closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void createTable(String tableName) {
        String createTableQuery = "CREATE TABLE " + tableName + " (ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), username VARCHAR(100), Wins INT,  PRIMARY KEY (ID))";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println("created");
        } catch (SQLException ex) {
            System.out.println("Create table failed because" + ex.getMessage());
        }
    }

    public void viewAllData() {
        String selectQuery = "SELECT * FROM " + this.tableName;
        try {
            ResultSet resultSet = con.createStatement().executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("username");
                int number = resultSet.getInt("Wins");
                System.out.println("ID: " + id + ", username: " + name + ", Wins: " + number);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("view all data broke");
        }

    }

    public void viewAllTables() {
        try {
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while (resultSet.next()) {
                String name = resultSet.getString("TABLE_NAME");
                String schema = resultSet.getString("TABLE_SCHEM");
                System.out.println(name + " from schema " + schema);
            }
        } catch (SQLException e) {
            System.out.println("BRO" + e.getMessage());
        }
    }

    public boolean rowExists(String username) {
        String checkRowQuery = "SELECT 1 FROM " + tableName + " WHERE username = ?";
        try (PreparedStatement statement = con.prepareStatement(checkRowQuery)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException ex) {
            System.out.println("Broke" + ex.getMessage());
        }
        return false;
    }

    public String getTableName () {
        return this.tableName;
    }
    
}
