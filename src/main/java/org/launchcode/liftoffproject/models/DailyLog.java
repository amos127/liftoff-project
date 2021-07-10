package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DailyLog {

    @Id
    @GeneratedValue
    private int id;

    private String date;
    private int moodScore;
    private int energyScore;
    private boolean ateBreakfast;
    private boolean ateLunch;
    private boolean ateDinner;
    private boolean tookMedicine;
    private int alcoholicDrinks;
    private int caffeinatedDrinks;
    private boolean didExercise;
    private boolean wentOutside;

    public DailyLog(String date, int moodScore, int energyScore, boolean ateBreakfast,
                    boolean ateLunch, boolean ateDinner,
                    boolean tookMedicine, int alcoholicDrinks, int caffeinatedDrinks,
                    boolean didExercise, boolean wentOutside) {
        this.date = date;
        this.moodScore = moodScore;
        this.energyScore = energyScore;
        this.ateBreakfast = ateBreakfast;
        this.ateLunch = ateLunch;
        this.ateDinner = ateDinner;
        this.tookMedicine = tookMedicine;
        this.alcoholicDrinks = alcoholicDrinks;
        this.caffeinatedDrinks = caffeinatedDrinks;
        this.didExercise = didExercise;
        this.wentOutside = wentOutside;
    }

    public DailyLog() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public int getEnergyScore() {
        return energyScore;
    }

    public void setEnergyScore(int energyScore) {
        this.energyScore = energyScore;
    }

    public boolean isAteBreakfast() {
        return ateBreakfast;
    }

    public void setAteBreakfast(boolean ateBreakfast) {
        this.ateBreakfast = ateBreakfast;
    }

    public boolean isAteLunch() {
        return ateLunch;
    }

    public void setAteLunch(boolean ateLunch) {
        this.ateLunch = ateLunch;
    }

    public boolean isAteDinner() {
        return ateDinner;
    }

    public void setAteDinner(boolean ateDinner) {
        this.ateDinner = ateDinner;
    }

    public boolean isTookMedicine() {
        return tookMedicine;
    }

    public void setTookMedicine(boolean tookMedicine) {
        this.tookMedicine = tookMedicine;
    }

    public int getAlcoholicDrinks() {
        return alcoholicDrinks;
    }

    public void setAlcoholicDrinks(int alcoholicDrinks) {
        this.alcoholicDrinks = alcoholicDrinks;
    }

    public int getCaffeinatedDrinks() {
        return caffeinatedDrinks;
    }

    public void setCaffeinatedDrinks(int caffeinatedDrinks) {
        this.caffeinatedDrinks = caffeinatedDrinks;
    }

    public boolean isDidExercise() {
        return didExercise;
    }

    public void setDidExercise(boolean didExercise) {
        this.didExercise = didExercise;
    }

    public boolean isWentOutside() {
        return wentOutside;
    }

    public void setWentOutside(boolean wentOutside) {
        this.wentOutside = wentOutside;
    }

    @Override
    public String toString() {
        return "DailyLog{" +
                "date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyLog dailyLog = (DailyLog) o;
        return id == dailyLog.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
