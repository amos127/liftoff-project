package org.launchcode.liftoffproject.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DailyLog {

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    @ManyToOne
    private User user;

    private int moodScore;
    private int energyScore;

    @Min(value = 0, message = "Must be between 0 and 24.")
    @Max(value = 0, message = "Must be between 0 and 24.")
    private double hoursSlept;

    private boolean ateBreakfast;
    private boolean ateLunch;
    private boolean ateDinner;

    @PositiveOrZero(message = "Must be zero or higher")
    private int alcoholicDrinks;

    @PositiveOrZero(message = "Must be zero or higher")
    private int caffeinatedDrinks;
    private boolean didExercise;
    private boolean wentOutside;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public DailyLog(Date date, int moodScore, int energyScore, double hoursSlept, boolean ateBreakfast,
                    boolean ateLunch, boolean ateDinner,
                    int alcoholicDrinks, int caffeinatedDrinks,
                    boolean didExercise, boolean wentOutside) {
        this.date = date;
        this.moodScore = moodScore;
        this.hoursSlept = hoursSlept;
        this.energyScore = energyScore;
        this.ateBreakfast = ateBreakfast;
        this.ateLunch = ateLunch;
        this.ateDinner = ateDinner;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public double getHoursSlept() {
        return hoursSlept;
    }

    public void setHoursSlept(double hoursSlept) {
        this.hoursSlept = hoursSlept;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
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
