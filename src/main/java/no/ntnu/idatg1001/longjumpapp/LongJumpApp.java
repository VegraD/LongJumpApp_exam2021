package no.ntnu.idatg1001.longjumpapp;
import no.ntnu.idatg1001.longjumpapp.longjumpresultscore.InputHandler;
import no.ntnu.idatg1001.longjumpapp.longjumpresultscore.LongJumpRegister;
import no.ntnu.idatg1001.longjumpapp.longjumpresultscore.LongJumpResult;

import java.time.LocalTime;

/**
 * Class representing the LongJumpApp class - the UI of the application.
 * Based off of given framework LongJumUI.java written by Arne.
 * @author 10030
 * @version 1.0.0 - 2021-12-16
 */
public class LongJumpApp {

    private LongJumpRegister longJumpRegister;
    private InputHandler inputHandler;
    int menuChoice;

    /**
     * Constructor for the LongJumpApp. The constructor creates the register, inputhandler,
     * fills the register and starts the main operation of the class.
     */
    public LongJumpApp() {
        longJumpRegister = new LongJumpRegister();
        inputHandler = new InputHandler();
        longJumpRegister.fillSet();
        start();
    }

    private static final int ADD_RESULT = 1;
    private static final int LIST_ALL_RESULTS = 2;
    private static final int SHOW_RESULT_BY_ATHLETE = 3;
    private static final int SHOW_BEST_RESULT = 4;
    private static final int CALCULATE_AVERAGE_RESULT = 5;
    private static final int EXIT = 6;

    /**
     * The main start method of the application.
     * @param args Commandline arguments as an array of String
     */
    public static void main(String[] args) {
        new LongJumpApp();
    }

    /**
     * This method creates the visible part of the app.
     */
    public void init() {
        System.out.println("----------------------------------");
        System.out.println("Long Jump application Beta 1.0.0\n");
        System.out.println("Please select an option below (1-6):");
        System.out.println("1. Register a long jump result");
        System.out.println("2. List all results");
        System.out.println("3. Show all results by a given athlete");
        System.out.println("4. Show the best result");
        System.out.println("5. Calculate the average result");
        System.out.println("6. Exit application");
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user. Continues until the user decides to exit the application.
     */
    public void start() {
        boolean finished = false;
        while (!finished) {
            init();
            menuChoice = inputHandler.getIntFromUser(1, 6);
            switch (menuChoice) {
                case ADD_RESULT:
                    addLongJumpResult();
                    break;

                case LIST_ALL_RESULTS:
                    listAllResults();
                    break;

                case SHOW_RESULT_BY_ATHLETE:
                    searchResultByAthlete();
                    break;

                case SHOW_BEST_RESULT:
                    listBestResult();
                    break;

                case CALCULATE_AVERAGE_RESULT:
                    calculateAvgResult();
                    break;

                case EXIT:
                    System.out.println("\nThank you for using the Long Jump Application. Bye!\n");
                    finished = true;
                    break;

                default:
                    System.out.println(
                            "\nERROR: Please provide a number between 1 and 6 \n");
            }
        }
    }

    /**
     * Method used for adding a LongJumpResult to the LongJumpRegister.
     * Is run when the user inputs the number 1.
     */
    public void addLongJumpResult() {
        System.out.println("Please enter the athletes start number:");
        int startNumberFromUser = inputHandler.getIntFromUser(100, 999);

        System.out.println("Please enter the name of the athlete:");
        String nameOfAthleteFromUser = inputHandler.getStringFromUser();

        System.out.println("Please enter the jump result of the athlete:");
        double resultFromUser = inputHandler.getDoubleFromUser(0, 15);

        System.out.println("Type 1 if jump was faul, type 2 if not:");
        int faulFromUser = inputHandler.getIntFromUser(1, 2);

        System.out.println("Please enter the time of the jump in the current format(ex. '10:15'):");
        String timeOfJumpFromUser = inputHandler.getTimeStringFromUser();

        if(faulFromUser == 1) {
            longJumpRegister.getLongJumpResultsSet().add(new LongJumpResult(startNumberFromUser,
                    nameOfAthleteFromUser, resultFromUser, true, LocalTime.parse(timeOfJumpFromUser)));
            System.out.println("Jump successfully registered!");
        } else {
            longJumpRegister.getLongJumpResultsSet().add(new LongJumpResult(startNumberFromUser,
                    nameOfAthleteFromUser, resultFromUser, false, LocalTime.parse(timeOfJumpFromUser)));
            System.out.println("Jump successfully registered!");
        }
    }

    /**
     * Method used for listing all LongJumpResult objects.
     * Is run when the user inputs the number 2.
     */
    public void listAllResults() {
        System.out.println("|S.Nr | Name | Result (metres) | Valid | Time (h:m)|");
        for(LongJumpResult result:longJumpRegister.getLongJumpResultsSet()) {
            System.out.println(result.toString());
        }
    }

    /**
     * Method used for searchin for a certain athlete in the register.
     * Is run when the user inputs the number 3.
     */
    public void searchResultByAthlete() {
        System.out.println("Please write the name of the athlete you would like to see results for:");
        String athleteNameFromUser = inputHandler.getStringFromUser();

        if(longJumpRegister.findResultByAthleteName(athleteNameFromUser).isEmpty() ) {
            System.err.println("Couldnt find any results from the given athlete. Returning to menu.");
        } else {
            System.out.println("These result were found by athlete name " + athleteNameFromUser + ":");
            for(LongJumpResult result:longJumpRegister.findResultByAthleteName(athleteNameFromUser)) {
                System.out.println(result.toString());
            }
        }
    }

    /**
     * Method used for listing the best current result in the register + athlete.
     * Is run when the user inputs the number 4.
     */
    public void listBestResult() {
        String athleteWithBestJump = "";
        for(LongJumpResult result:longJumpRegister.getLongJumpResultsSet()) {
            if(result.getResult() == longJumpRegister.findBestJump()) {
                athleteWithBestJump = result.getNameOfAthlete();
            }
        }
        System.out.println("The best current result is " + longJumpRegister.findBestJump() + " by: " + athleteWithBestJump);
    }


    /**
     * Method used for calculating the average results of all jumpers.
     * Is run when the user inputs the number 5.
     */
    public void calculateAvgResult() {
        System.out.println("The average result of all jumps are: " + longJumpRegister.calculateAvgResult() + "\n");
        double avgValidResults = 0;
        int validResults = 0;
        for(LongJumpResult result:longJumpRegister.getLongJumpResultsSet()) {
            if(!result.isFaul()){
                avgValidResults += result.getResult();
                validResults++;
            }
        }
        if(validResults != 0) {
            System.out.println("Alternatively, the average of all VALID jumps are: " + avgValidResults / validResults);
        }
    }

}
