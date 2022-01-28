package no.ntnu.idatg1001.LongJumpApp.longJumpResultsCore;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class representing the inputHandler of the application.
 * @author 10030
 * @version 1.0.0 - 2021-12-16
 */
public class InputHandler {

    Scanner scn;

    /**
     * Constructor for the InputHandler class. Creates a scanner used later in the class.
     */
    public InputHandler() {
        scn = new Scanner(System.in);
    }

    /**
     * Method that takes int input from the user.
     * @param min
     * @param max
     * @return int if it is valid.
     */
    public int getIntFromUser(int min, int max) {
        boolean validInt = false;
        int intFromUser = 0;

        while (!validInt) {
            try {
                intFromUser = scn.nextInt();
                if (validateInt(intFromUser, min, max)) {
                    validInt = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Please enter an integer!");
                scn.next();
            }
        }
        return intFromUser;

    }

    /**
     * Method that takes String input from the user.
     * @return String input by user, if valid.
     */
    public String getStringFromUser() {
        String stringFromUser = scn.next();
        stringFromUser += scn.nextLine();
        while(!validateString(stringFromUser)) {
            System.err.println("Please enter a valid String!");
            stringFromUser = scn.next();
            stringFromUser += scn.nextLine();
        }
        return stringFromUser;
    }

    /**
     * Method that takes String input for LocalTime from user.
     * @return String input by user, if valid.
     */
    public String getTimeStringFromUser() {
        String stringFromUser = scn.next();
        scn.nextLine();
        if (validateTimeString(stringFromUser)) {
            return stringFromUser;
        } else {
            System.err.println("Please enter a valid String!");
            return null;
        }
    }

    /**
     * Method that takes double input from user.
     * @param min
     * @param max
     * @return double input by user, if valid.
     */
    public double getDoubleFromUser(int min, int max) {
        boolean validDouble = false;
        double doubleFromUser = 0;

        while (!validDouble) {
            try {
                doubleFromUser = scn.nextDouble();
                if (validateDouble(doubleFromUser, min, max)) {
                    validDouble = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Please enter a double/integer!");
                scn.next();
            }
        }
        return doubleFromUser;

    }

    /**
     * Method that validates integer input.
     * @param intFromUser
     * @param min
     * @param max
     * @return true if valid integer, false if not.
     */
    public boolean validateInt(int intFromUser, int min, int max) {
        boolean validInt = false;
        if (intFromUser >= min && intFromUser <= max) {
            validInt = true;
        } else {
            System.err.println("Please enter a valid integer between " + min + " and " + max);
        }
        return validInt;
    }

    /**
     * Method that validates String input.
     * @param stringFromUser
     * @return true if valid String, false if not.
     */
    public boolean validateString(String stringFromUser) {
        int length = stringFromUser.length();
        if (length == 0) {
            return false;
        }

        for(int i = 0;i<length;i++) {
            if((Character.isDigit(stringFromUser.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that validates double input.
     * @param doubleFromUser
     * @param min
     * @param max
     * @return true if valid double, false if not.
     */
    public boolean validateDouble(double doubleFromUser, int min, int max) {
        boolean validDouble = false;
        if(doubleFromUser >= min && doubleFromUser <= max) {
            validDouble = true;
        } else{
            System.err.println("Please enter a valid double/int.");
        }
        return validDouble;
    }

    /**
     * Method that validates String input us in LocalTime.
     * @param stringFromUser
     * @return true if valid String that can be used i LocalTime, false if not.
     */
    public boolean validateTimeString(String stringFromUser) {
        int length = stringFromUser.length();
        if (length < 4 || length > 5) {
            return false;
        }
        return true;
    }

}

