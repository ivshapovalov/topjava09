package ru.javawebinar.topjava.model;

import java.util.List;

public interface MealDAO {

    List<Meal> getAll();

    void update(Meal meal);

    void delete(int id);

    void clear();

    Meal get(int id);

    Meal addNew();

}
