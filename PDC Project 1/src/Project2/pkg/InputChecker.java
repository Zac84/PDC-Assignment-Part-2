/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2.pkg;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author zdtuc
 */
public class InputChecker {

    HashMap<String, String> map = new HashMap<String, String>();
    Scanner s1 = new Scanner(System.in);

    public InputChecker() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "4");
        map.put("E", "5");
        map.put("F", "6");
        map.put("G", "7");
        map.put("H", "8");
        map.put("I", "9");
        map.put("J", "10");
    }

    //the check method takes an initial input and the desired input 
    //it will check if the input is in the desired input and using a loop
    //to make sure that its the correct input and eventually return the correct input
    //if others == true it means there are other inputs that are allowed that wont be displayed in the string
    //the inputed strings should be in the format "Display string , non-Display strings"
    //if others == false it will just be the normal output
    public String check(String desiredInput, boolean others) {
        String returnString = s1.nextLine();
        boolean stillGoing = true;

        while (stillGoing) {
            for (String s : createAnswerArray(desiredInput)) {
                if (s.equals(returnString)) {
                    stillGoing = false;
                    break;
                }
            }

            if (others && stillGoing) {
                this.PleaseEnterCorrectInput(createAnswerArray(desiredInput, ","));
                returnString = s1.nextLine();
            } else if (stillGoing) {
                this.PleaseEnterCorrectInput(createAnswerArray(desiredInput));
                returnString = s1.nextLine();
            }
        }

        return returnString;

    }

    public void PleaseEnterCorrectInput(String l1, String l2) {
        System.out.println("Wrong input, please input " + l1 + " or " + l2 + " :)");
    }

    public void PleaseEnterCorrectInput(String[] desired) {
        System.out.print("Wrong input, please input ");

        for (int i = 0; i < desired.length; i++) {
            if (i != desired.length - 1) {
                System.out.print(desired[i] + " or ");
            } else {
                System.out.print(desired[i]);
            }
        }

        System.out.println(" :)");
    }

    public boolean letterChecker(String letter) {
        String[] allowedLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        boolean correct = false;

        for (String l : allowedLetters) {
            if (l.equalsIgnoreCase(letter)) {
                correct = true;
                break;
            }
        }
        return correct;
    }

    public String checkCoordinates() {
        boolean boom = false;

        String tempInput = s1.nextLine();
        if (tempInput.equalsIgnoreCase("BOOM")) {
            boom = true;
            System.out.println("Cool! Cooridinates?: ");
            tempInput = "";
            tempInput = s1.nextLine();
        }

        String[] coordinates = this.createAnswerArray(tempInput);

        String returnString = "";

        boolean[] correct = new boolean[coordinates.length];
        int counter = 0;
        boolean stillGoing = true;

        String[] listOfNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "10"};

        //loops through everything to make sure that nothing goes out of parameters and doesn't break.
        while (stillGoing) {

            //check if the second one is lettercheck == true and if its not
            //use continue
            if (correct.length == 2 && letterChecker(coordinates[0])) {

                coordinates[0] = coordinates[0].toUpperCase();
                coordinates[0] = map.get(coordinates[0]);

                for (String number : coordinates) {
                    for (String s : listOfNumbers) {
                        if (number.equals(s)) {
                            correct[counter] = true;
                            break;
                        }
                    }
                    counter++;
                }

            }

            boolean containsFalse = false;
            //runs through the whole boolean array checking if any of them are false
            //booleans will be false if not changed
            for (boolean elements : correct) {
                if (!elements) {
                    containsFalse = true;
                    break;
                }
            }

            //loops through both arrays to check if any of them are correct.
            //if any was false, prompts the user to try again and resets all the values.
            if (containsFalse) {
                System.out.println("Wrong, Please use the correct input (Letter Number) (x y): ");
                counter = 0;
                coordinates = this.createAnswerArray(s1.nextLine());
                correct = new boolean[coordinates.length];
            } else {
                if (boom) {
                    returnString = coordinates[0] + " " + coordinates[1] + " " + "BOOM";
                } else {
                    returnString = coordinates[0] + " " + coordinates[1];
                }
                stillGoing = false;
                break;
            }
        }
        return returnString;
    }

    public String[] createAnswerArray(String answers) {
        return answers.split(" ");
    }

    // method is for returning a string array until a certain character is reached
    public String[] createAnswerArray(String answers, String redex) {
        //splits the string from where the redex
        String[] temp = answers.split(redex);

        return temp[0].split(" ");
    }

    public String notTheSameUser(String username) {
        String tempInput = s1.nextLine();
        boolean stillGoing = true;

        while (stillGoing) {
            if (!username.equals(tempInput)) {
                stillGoing = false;
            } else {
                System.out.println("User has already logged in, please use a different user :)");
                tempInput = s1.nextLine();
            }
        }

        return tempInput;
    }
}
