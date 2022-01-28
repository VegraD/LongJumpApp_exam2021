package no.ntnu.idatg1001.LongJumpApp.longJumpResultsCore;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Class representing an object longJumpResultsCore.LongJumpResult.
 * @author 10030
 * @version 1.0.0 - 2021-12-16
 */
public class LongJumpResult {

    private int startNumber;
    private String nameOfAthlete;
    private double result;
    private boolean faul;
    private LocalTime timeOfJump;

    /**
     * Constructor for the longJumpResultsCore.LongJumpResult class using all fields as parameters.
     * @param startNumber
     * @param nameOfAthlete
     * @param result
     * @param faul
     * @param timeOfJump
     */
    public LongJumpResult(int startNumber, String nameOfAthlete, double result, boolean faul, LocalTime timeOfJump) {
        this.startNumber = startNumber;
        this.nameOfAthlete = nameOfAthlete;
        this.result = result;
        this.faul = faul;
        this.timeOfJump = timeOfJump;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public String getNameOfAthlete() {
        return nameOfAthlete;
    }

    public void setNameOfAthlete(String nameOfAthlete) {
        this.nameOfAthlete = nameOfAthlete;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Getter for the faul field.
     * @return a boolean value based on whether the jump was faul. False if not, true if faul jump.
     */
    public boolean isFaul() {
        return faul;
    }

    public void setFaul(boolean faul) {
        this.faul = faul;
    }

    public LocalTime getTimeOfJump() {
        return timeOfJump;
    }

    /**
     * Setter for the timeOfJump field. Takes in a String rather than a LocalTime object.
     * @param time
     */
    public void setTimeOfJump(String time) {
        this.timeOfJump = LocalTime.parse(time);
    }

    /**
     * A method that returns an objects field values as a String.
     * @return String of the objects field values.
     */
    @Override
    public String toString() {
        if(isFaul()) {
            return "|" + getStartNumber() + " | "+ getNameOfAthlete() + " | " + getResult()
                    + " |  No | " + " | "+ getTimeOfJump() + "|";
        }else {
            return "|" + getStartNumber() + " | " + getNameOfAthlete() + " | " + getResult()
                    + " |  Yes | " + " | " + getTimeOfJump() + "|";
        }
    }

    /**
     * A method that allows for comparing two longJumpResultsCore.LongJumpResult objects using their field values.
     * @param o
     * @return boolean. True if field value(s) are similar, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongJumpResult that = (LongJumpResult) o;
        return Objects.equals(nameOfAthlete, that.nameOfAthlete) && Objects.equals(timeOfJump, that.timeOfJump);
    }

    /**
     * Creates a unique HashCode for an object with certain field values.
     * @return hashCode of an object according to its field values.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nameOfAthlete, timeOfJump);
    }
}
