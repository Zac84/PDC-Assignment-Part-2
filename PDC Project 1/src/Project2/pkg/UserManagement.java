/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.impl.sql.compile.TableName;

/**
 *
 * @author zdtuc
 */
public class UserManagement {

    public String Username;
    public User user;
    private final Connection con;
    public String tableName;

    public UserManagement(User user, String username, Connection con, String tableName) {
        this.Username = username;
        this.user = user;
        this.con = con;
        this.tableName = tableName;
    }

    public void login() {
        //check if user exists

        if (this.rowExists(Username)) {
            this.setCredintials();
            System.out.println("logged in");
        } else {
            this.setNewCredintials();
            System.out.println("Created");
        }

        //if user exists, user.setCredintials
        //if user doesn't exists, user.setNewCredintials
    }

    public void setCredintials() {

        String[] userRow = this.getRow(Username).split(" ");
        
        if(userRow == null || userRow.length < 3) {
            System.out.println("failed");
            return;
        }
        
        user.logIn(userRow[1], Integer.parseInt(userRow[0]), Integer.parseInt(userRow[2]));
    }

    public void setNewCredintials() {
        String insertQuery = "INSERT INTO " + this.tableName + " (username, Wins) VALUES (?, ?)";
        try {
            PreparedStatement insertStatement = con.prepareStatement(insertQuery);
            insertStatement.setString(1, this.Username);
            insertStatement.setInt(2, 1);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.setCredintials();

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

    public String getRow(String username) {
        String row = "";
        String checkRowQuery = "SELECT * FROM " + tableName + " WHERE username = ?";
        try (PreparedStatement statement = con.prepareStatement(checkRowQuery)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("username");
                    int number = resultSet.getInt("Wins");
                    row = id + " " + name + " " + number;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Broke " + ex.getMessage());
        }

        return row;
    }

    public void increaseWins(User user) {
        String updateQuery = "UPDATE " + tableName + " SET Wins = ? WHERE username = ?";
        try (PreparedStatement statement = con.prepareStatement(updateQuery)) {
            statement.setInt(1, user.getNumberOfWins() + 1);
            statement.setString(2, user.getUserName());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Row updated successfully.");
            } else {
                System.out.println("No matching row found.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setNewUser (User user, String username) {
        this.Username = username;
        this.user = user;
    }

}
