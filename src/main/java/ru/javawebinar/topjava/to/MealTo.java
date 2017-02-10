package ru.javawebinar.topjava.to;

import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by User on 09.02.2017.
 */
public class MealTo{

    private Integer id;

    //@NotBlank(message = "Date&Time must not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @NotBlank(message = "Description must not be null")
    private String description;

    private int calories;

    public MealTo() {
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
                ", stringDateTime='" + dateTime + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
