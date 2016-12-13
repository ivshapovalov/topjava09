package ru.javawebinar.topjava.model;

import java.util.List;


public interface MealDAO {
    List<Meal> getMeals();

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(int id);

    Meal getMealById(int id);
}
