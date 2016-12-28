package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.function.Function;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int USER1_ID = START_SEQ;
    public static final int MEAL_ID = START_SEQ+2;

    public static final Meal USER1_MEAL1 = new Meal (MEAL_ID, DateTimeUtil.parseLocalDateTime
            ("2016-12-22 09:00:00"),"Завтрак",2000);
    public static final Meal USER1_MEAL2 = new Meal (MEAL_ID+1, DateTimeUtil.parseLocalDateTime
            ("2016-12-23 12:00:00"),"Обед",500);
    public static final Meal USER1_MEAL3 = new Meal (MEAL_ID+2, DateTimeUtil.parseLocalDateTime
            ("2016-12-23 18:00:00"),"Ужин",100);
    public static final Meal USER1_MEAL4 = new Meal (MEAL_ID+3, DateTimeUtil.parseLocalDateTime
            ("2016-12-22 18:00:00"),"Ужин",1000);
    public static final Meal USER1_MEAL5 = new Meal (MEAL_ID+4, DateTimeUtil.parseLocalDateTime
            ("2016-12-22 12:00:00"),"Обед",500);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
