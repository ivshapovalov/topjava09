package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    private int id;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean
            exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public MealWithExceed(int id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public MealWithExceed(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateTimeAsString() {
        return TimeUtil.formatLocalDateTimeToString(dateTime,"yyyy-MM-dd HH:mm");
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealWithExceed that = (MealWithExceed) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
