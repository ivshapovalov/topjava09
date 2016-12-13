package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MealDAOInMemory implements MealDAO {

    private ConcurrentMap<Integer, Meal> meals;

    public MealDAOInMemory(List<Meal> meals) {

        //generate id's
        Collections.shuffle(meals);
        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setId(i + 1);
        }
        sortByDateTime(meals);

        this.meals = new ConcurrentHashMap<>();
        for (Meal meal : meals
                ) {
            this.meals.put(meal.getId(), meal);
        }
    }

    private List<Meal> sortByDateTime(List<Meal> meals) {
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return meals;
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> values=new ArrayList<>(meals.values());
        return sortByDateTime(values);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
            }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public Meal createNew() {
        List<Integer> keys=new ArrayList<>(meals.keySet());
        Collections.sort(keys);
        if (keys.size()==0) {
            return new Meal(1,LocalDateTime.now());
        } else {
            int maxId=keys.get(keys.size()-1);
            return new Meal(maxId+1,LocalDateTime.now());
        }
    }
}
