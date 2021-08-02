package org.launchcode.liftoffproject.models;

import java.util.Collection;

public class Stats {

    public static double avgMood(Iterable<DailyLog> logs) {
        double sum = 0;
        int logCount = ((Collection<?>)logs).size();
        for (DailyLog log : logs) {
            sum += log.getMoodScore();
        } return Math.round((sum/logCount) * 100.0) / 100.0;
    }

    public static double avgEnergy(Iterable<DailyLog> logs) {
        double sum = 0;
        int logCount = 0;
        if (logs instanceof Collection<?>) {
            logCount = ((Collection<?>)logs).size();
        }
        for (DailyLog log : logs) {
            sum += log.getEnergyScore();
        } return Math.round((sum/logCount) * 100.0) / 100.0;
    }

    public static double avgAlcohol(Iterable<DailyLog> logs) {
        double sum = 0;
        int logCount = 0;
        if (logs instanceof Collection<?>) {
            logCount = ((Collection<?>)logs).size();
        }
        for (DailyLog log : logs) {
            sum += log.getAlcoholicDrinks();
        } return Math.round((sum/logCount) * 100.0) / 100.0;
    }

    public static double avgCaffeine(Iterable<DailyLog> logs) {
        double sum = 0;
        int logCount = ((Collection<?>)logs).size();
//        if (logs instanceof Collection<?>) {
//            logCount = ((Collection<?>)logs).size();
//        }
        for (DailyLog log : logs) {
            sum += log.getCaffeinatedDrinks();
        } return Math.round((sum/logCount) * 100.0) / 100.0;
    }

    public static double avgHoursSlept(Iterable<DailyLog> logs) {
        double sum = 0;
        int logCount = ((Collection<?>)logs).size();
        for (DailyLog log : logs) {
            sum += log.getHoursSlept();
        } return Math.round((sum/logCount) * 100.0) / 100.0;
    }

    public static int daysSleptOverSeven(Iterable<DailyLog> dailyLogs) {
        int days = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.getHoursSlept() >= 7) {
                days++;
            }
        } return days;
    }

    public static int daysExercised(Iterable<DailyLog> logs) {
        int count = 0;
        for (DailyLog log : logs) {
            if (log.isDidExercise()) {
                count++;
            } else {
                continue;
            }
        } return count;
    }

    public static int daysOutside(Iterable<DailyLog> logs) {
        int count = 0;
        for (DailyLog log : logs) {
            if (log.isWentOutside()) {
                count++;
            } else {
                continue;
            }
        } return count;
    }

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

    public static int maxExerciseStreak(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.isDidExercise()) {
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

    public static int maxStreakLessThanThreeDrinks(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.getAlcoholicDrinks() < 3) {
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

    public static int maxStreakSleptOverSevenHours(Iterable<DailyLog> dailyLogs) {

        int longestStreak = 0;
        int currentStreak = 0;
        for (DailyLog dailyLog : dailyLogs) {
            if (dailyLog.getHoursSlept() >= 7) {
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
}
