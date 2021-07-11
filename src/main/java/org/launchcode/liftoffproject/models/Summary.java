package org.launchcode.liftoffproject.models;

import java.util.List;

public class Summary {

    //finds the maximum streak of "yes" responses to boolean questions
    public static int maxTrueStreak(List<Boolean> boolList) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (boolean item : boolList) {
            if (item == true) {
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }
        return longestStreak;
    }

    //finds the maximum streak of "0" responses to integer questions
    public static int maxZeroStreak(List<Integer> intList) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (int item : intList) {
            if (item == 0) {
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }
        return longestStreak;
    }

    public static double averageScoreBoolean(List<Integer> scoreIntList, List<Boolean> boolList, boolean condition) {
        double sum = 0;
        int conditionCount = 0;
        for (int i = 0; i < boolList.size(); i++) {
            if (boolList.get(i) == condition) {
                sum += scoreIntList.get(i);
                conditionCount++;
            }
        } return sum/conditionCount;
    }

    public static double averageScoreTwoBoolean(List<Integer> scoreIntList, List<Boolean> boolList,
                                                List<Boolean> boolListTwo, boolean condition, boolean conditionTwo) {
        double sum = 0;
        int conditionCount = 0;
        for (int i = 0; i < boolList.size(); i++) {
            if (boolList.get(i) == condition && boolListTwo.get(i) == conditionTwo) {
                sum += scoreIntList.get(i);
                conditionCount++;
            }
        } return sum/conditionCount;
    }

    public static double averageScoreBooleanAndInt(List<Integer> scoreIntList, List<Boolean> boolList,
                                                   List<Integer> intList, boolean condition, int conditionInt) {
        double sum = 0;
        int conditionCount = 0;
        for (int i = 0; i < boolList.size(); i++) {
            if (boolList.get(i) == condition && intList.get(i) <= conditionInt) {
                sum += scoreIntList.get(i);
                conditionCount++;
            }
        } return sum/conditionCount;
    }

    public static double averageScoreInt(List<Integer> scoreIntList, List<Integer> otherIntList, int conditionInt) {
        double sum = 0;
        int conditionCount = 0;
        for (int i = 0; i < otherIntList.size(); i++) {
            if (otherIntList.get(i) <= conditionInt) {
                sum += scoreIntList.get(i);
                conditionCount++;
            }
        } return sum/conditionCount;
    }
}
