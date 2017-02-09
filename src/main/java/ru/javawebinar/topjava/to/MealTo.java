package ru.javawebinar.topjava.to;

import java.io.Serializable;

/**
 * Created by User on 09.02.2017.
 */
public class MealTo{

    private Integer id;

    private String stringDateTime;

    private String description;

    private int calories;

    public MealTo() {
    }

    public MealTo(Integer id, String stringDateTime, String description, int calories) {
        this.id = id;
        this.stringDateTime = stringDateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStringDateTime() {
        return stringDateTime;
    }

    public void setStringDateTime(String stringDateTime) {
        this.stringDateTime = stringDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", stringDateTime='" + stringDateTime + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
