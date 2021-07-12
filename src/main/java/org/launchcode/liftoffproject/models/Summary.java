package org.launchcode.liftoffproject.models;

import java.util.List;

public class Summary {

    //finds the maximum streak of "yes" responses to boolean questions
    public static int maxBreakfastStreak(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast()) {
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

    public static int maxThreeMealsStreak(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast() && dailyLog.isAteLunch() && dailyLog.isAteDinner()) {
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
    public static int maxZeroAlcoholStreak(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.getAlcoholicDrinks() == 0) {
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

    public static double averageMoodByBreakfast(Iterable<DailyLog> dailyLogs, boolean condition) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast() == condition) {
                sum += dailyLog.getMoodScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
    }

    public static double averageEnergyByBreakfast(Iterable<DailyLog> dailyLogs, boolean condition) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast() == condition) {
                sum += dailyLog.getEnergyScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
    }

    public static double averageMoodBreakfastExercise(Iterable<DailyLog> dailyLogs) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast() && dailyLog.isDidExercise()) {
                sum += dailyLog.getMoodScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
    }

    public static double averageMoodBreakfastExerciseFalse(Iterable<DailyLog> dailyLogs) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (!dailyLog.isAteBreakfast() || !dailyLog.isDidExercise()) {
                sum += dailyLog.getMoodScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
    }

    public static double averageEnergyBreakfastExercise(Iterable<DailyLog> dailyLogs) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isAteBreakfast() && dailyLog.isDidExercise()) {
                sum += dailyLog.getEnergyScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
    }

    public static double averageEnergyBreakfastExerciseFalse(Iterable<DailyLog> dailyLogs) {
        double sum = 0;
        int conditionCount = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (!dailyLog.isAteBreakfast() || !dailyLog.isDidExercise()) {
                sum += dailyLog.getEnergyScore();
                conditionCount++;
            }
        } return Math.round((sum/conditionCount) * 100.0) / 100.0;
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
