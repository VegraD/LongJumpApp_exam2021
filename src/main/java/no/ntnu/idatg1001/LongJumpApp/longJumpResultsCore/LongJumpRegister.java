package no.ntnu.idatg1001.LongJumpApp.longJumpResultsCore;

import java.time.LocalTime;
import java.util.*;

/**
 * Class representing a register longJumpRegister.
 * @author 10030
 * @version 1.0.0 - 2021-12-16
 */
public class LongJumpRegister {

    private Set<LongJumpResult> longJumpResultsSet;

    /**
     * Constructor for the longJumpResultsCore.LongJumpRegister class.
     * Using a HashSet and not a fixed ArrayList/ArrayList considering there
     * will be a lot of results to register, and results will be added frequently.
     * Therefore, i can not use a fixed ArrayList whatsoever. An ArrayList is usable,
     * but is slower than a HashSet whenever there are > 20 elements in the List/Set.
     * Could also just as well have used a HashMap in this scenario.
     */
    public LongJumpRegister() {
        longJumpResultsSet = new HashSet<>();
    }

    /**
     * Method used for accessing the LongJumpResultsSet.
     * @return Set of LongJumpResults currently in the register.
     */
    public Set<LongJumpResult> getLongJumpResultsSet() {
        return longJumpResultsSet;
    }

    /**
     * Method used for getting the size of the LongJumpResultsSet.
     * @return integer equal to the amount of elements in the register.
     */
    public int getResultAmount() {
        return longJumpResultsSet.size();
    }

    /**
     * Method used for adding LongJumpResults to the register.
     * @param longJumpResult
     */
    public void addLongJumpStat(LongJumpResult longJumpResult) {
        longJumpResultsSet.add(longJumpResult);
    }

    /**
     * Method used for removing a specific LongJumpStat from the register.
     * @param startNumber
     * @param timeOfJump
     */
    public void removeLongJumpStat(int startNumber, LocalTime timeOfJump) {
        Iterator<LongJumpResult> longJumpResultIterator = createIterator();
        while(longJumpResultIterator.hasNext()) {
            LongJumpResult longJumpResultTemp = longJumpResultIterator.next();
            if(longJumpResultTemp.getStartNumber() == startNumber
                    && longJumpResultTemp.getTimeOfJump().getHour() == timeOfJump.getHour()
                    && longJumpResultTemp.getTimeOfJump().getMinute() == timeOfJump.getMinute()) {
                longJumpResultsSet.remove(longJumpResultTemp);
            }
        }
    }

    /**
     * Method used for returning an iterator for the other methods to use.
     */
    public Iterator<LongJumpResult> createIterator() {
        return longJumpResultsSet.iterator();
    }

    /**
     * Method used for finding the best jump result currently in the register.
     * Found while searching for a method to determine the highest value in a Collection.
     * Found: https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
     * Using this in my code significantly decreased the simpleness of this method,
     * considering other ways of doing the same process takes far longer to write, and
     * increases the amount of lines of code.
     * @return
     */
    public double findBestJump() {
        List<Double> jumpResultsTemp = new ArrayList<>();
        Iterator<LongJumpResult> longJumpResultIterator = createIterator();
        while(longJumpResultIterator.hasNext()) {
            LongJumpResult longJumpResultTemp = longJumpResultIterator.next();
            jumpResultsTemp.add(longJumpResultTemp.getResult());
        }
        return Collections.max(jumpResultsTemp,null);
    }

    /**
     * Method used for finding all results in the register with the given name.
     * @param name
     * @return ArrayList of all results by the athlete
     */
    public List<LongJumpResult> findResultByAthleteName(String name) {
        List<LongJumpResult> longJumpResultsTemp = new ArrayList<>();
        for (LongJumpResult result:longJumpResultsSet) {
            if(result.getNameOfAthlete().equalsIgnoreCase(name)){
                longJumpResultsTemp.add(result);
            }
        }
        return longJumpResultsTemp;
    }

    /**
     * Method used for calculating the average result across the whole Set.
     * @return average jump results as double.
     */
    public double calculateAvgResult() {
        double avgResult = 0d;
        for (LongJumpResult result:longJumpResultsSet) {
            avgResult += result.getResult();
        }
        return avgResult/getResultAmount();
    }

    /**
     * Method used for filling up the register with "dummy" LongJumpResults.
     */
    public void fillSet(){
        longJumpResultsSet.add(new LongJumpResult(210, "Malaika Mihambo", 6.98d, false, LocalTime.parse("10:15")));
        longJumpResultsSet.add(new LongJumpResult(211, "Tara Davis", 6.85d, false, LocalTime.parse("10:17")));
        longJumpResultsSet.add(new LongJumpResult(204, "Brittney Reese", 6.52d, false, LocalTime.parse("10:19")));
        longJumpResultsSet.add(new LongJumpResult(224, "Khaddi Sagnia", 6.76d, false, LocalTime.parse("10:21")));
        longJumpResultsSet.add(new LongJumpResult(211, "Tara Davis", 6.42d, true, LocalTime.parse("10:24")));
        longJumpResultsSet.add(new LongJumpResult(210, "Malaika Mihambo", 6.56d, false, LocalTime.parse("10:30")));
        longJumpResultsSet.add(new LongJumpResult(204, "Brittney Reese", 6.86d, false, LocalTime.parse("10:34")));
        longJumpResultsSet.add(new LongJumpResult(224, "Khaddi Sagnia", 6.65d, true, LocalTime.parse("10:37")));
        longJumpResultsSet.add(new LongJumpResult(210, "Malaika Mihambo", 6.12d, false, LocalTime.parse("10:40")));
    }

}
