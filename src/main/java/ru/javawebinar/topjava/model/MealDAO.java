package ru.javawebinar.topjava.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ivan on 12.12.2016.
 */
public class MealDAO {
    private final List<MealWithExceed> meals;

    public MealDAO(List<MealWithExceed> meals) {
        this.meals = meals;

        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setId(i+1);
        }
        Collections.shuffle(meals);
    }

    public List<MealWithExceed> getMeals() {
        return meals;
    }

    public void addMeal(MealWithExceed mealWithExceed) {
        meals.add(mealWithExceed);
    }

    public void updateMeal(MealWithExceed mealWithExceed) {
        int index=meals.indexOf(mealWithExceed);
        meals.remove(index);
        meals.add(mealWithExceed);
    }
    public void deleteMeal(int id) {
        int index=meals.indexOf(new MealWithExceed(id));
        meals.remove(index);
    }


    public MealWithExceed getMealById(int id) {
        int index=meals.indexOf(new MealWithExceed(id));
        return meals.get(index);
    }
}
