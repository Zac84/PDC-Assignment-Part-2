/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc.project.pkg1;

/**
 *
 * @author zdtuc
 */
import java.util.*;
import java.io.*;

public class FileManagment {
    
    public int getID() {
        
        // *WARNING* HAD TO CHANGE RUN WORKING DIRECTORY TO PROJECT FOLDER OR IT BREAKS *WARNING*

        BufferedWriter pw;
        BufferedReader bufferedReader;
        String ID = "";
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader("./Users/IDcounter.txt"));
                ID = bufferedReader.readLine();
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading from file " + "./resources/T02_input.txt");
            }

            pw = new BufferedWriter(new PrintWriter(new FileOutputStream("./Users/IDcounter.txt")));
            int tempInt = (Integer.parseInt(ID) + 1);

            pw.write(String.valueOf(tempInt));
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("BRUH");
        }

        return (Integer.parseInt(ID) + 1);
    }

    public int addWin(User user) {
        BufferedWriter pw;
        BufferedReader bufferedReader;
        String[] fileContents = new String[3];
        int numOFWins = 0;
        
        try {
            bufferedReader = new BufferedReader(new FileReader("./Users/" + user.getUserName() + ".txt"));

            String nline = "";
            for (int i = 0; (nline = bufferedReader.readLine()) != null; i++) {
                fileContents[i] = nline;
            }
            bufferedReader.close();
            
            pw = new BufferedWriter(new PrintWriter(new FileOutputStream("./Users/" + user.getUserName() + ".txt")));
            numOFWins = (Integer.parseInt(fileContents[2]) + 1);
            
            pw.write(fileContents[0] + "\n" + fileContents[1] + "\n" + numOFWins);
            pw.flush();
            pw.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
        }
        
        return numOFWins;
    }

    //takes in a user and edits it / logs in
    public void logIn(User user, String username) {

        String[] info = new String[3];

        try {
            BufferedReader br = new BufferedReader(new FileReader("./Users/" + username + ".txt"));

            String nline = "";
            for (int i = 0; (nline = br.readLine()) != null; i++) {
                info[i] = nline;
            }

            br.close();
            user.logIn(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("User not found, creating a new user :)");
            this.createNew(username, user);
        } catch (IOException e) {
            System.out.println("Error reading from file " + "./resources/T02_input.txt");
        }

    }

    public void createNew(String username, User user) {
        Scanner scan = new Scanner(System.in);

        //the user is already created with the ID
        boolean fileCreatedSuccesfully = false;
        while (!fileCreatedSuccesfully) {
            try {
                File myFile = new File("./Users/" + username + ".txt");
                if (myFile.createNewFile() == true) { // *WARNING* if playing up, it only works when not debugging. Must be running program normally *WARNING*
                    System.out.println("File Created");

                    try {
                        BufferedWriter pw = new BufferedWriter(new PrintWriter(new FileOutputStream(myFile)));
                        pw.write(username + "\n" + user.getID() + "\n" + 0);
                        pw.flush();
                        pw.close();
                        fileCreatedSuccesfully = true;
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {
                    System.out.println("File already exists, please try a different name :)");
                    myFile = null;
                    username = scan.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Caught" + e);
            }
        }
        user.setUserName(username);

    }

}
