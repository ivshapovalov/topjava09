package ru.javawebinar.topjava.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Ivan on 12.12.2016.
 */
public class MealDAOInMemory implements MealDAO {
    private ConcurrentMap<Integer, Meal> meals;

    public MealDAOInMemory(List<Meal> meals) {
        Collections.shuffle(meals);

        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setId(i + 1);
        }
        sortMeals(meals);
        this.meals = new ConcurrentHashMap<>();
        for (Meal meal : meals
                ) {
            this.meals.put(meal.getId(), meal);
        }
    }

    private List<Meal> sortMeals(List<Meal> meals) {
        Collections.sort(meals, new Comparator<Meal>() {
            @Override
            public int compare(Meal m1, Meal m2) {

                return m1.getDateTime().compareTo(m2.getDateTime());
            }
        });
        return meals;
    }

    @Override
    public List<Meal> getMeals() {
        List<Meal> values=new ArrayList<>(meals.values());
        return sortMeals(values);
    }

    @Override
    public void addMeal(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {

        meals.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(id);
    }

    @Override
    public Meal getMealById(int id) {
        return meals.get(id);
    }
}
