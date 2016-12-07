package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //***************Решение через циклы*********************

//        Map<LocalDate, Integer> groupedDatesbyDate = new HashMap<>();
//        for (UserMeal meal : mealList
//                ) {
//            Integer existingDates = groupedDatesbyDate.get(meal.getDate());
//            if (existingDates != null) {
//                groupedDatesbyDate.put(meal.getDate(), existingDates + meal.getCalories());
//            } else {
//                groupedDatesbyDate.put(meal.getDate(), meal.getCalories());
//            }
//        }
//
//        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
//        for (UserMeal meal : mealList
//                ) {
//            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
//                boolean isExceed = groupedDatesbyDate.get(meal.getDate()) > caloriesPerDay ? true : false;
//                userMealWithExceedList.add(new
//                        UserMealWithExceed(meal
//                        .getDateTime(),
//                        meal.getDescription(), meal.getCalories(),
//                        isExceed));
//            }
//
//        }

        /***************Решение через Stream API******************/
        Map<LocalDate, Integer> groupedDatesbyDate =
                mealList.stream()
                        .collect(Collectors.groupingBy(p -> p.getDate(),
                                Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> userMealWithExceedList = mealList.stream()
                .filter((s) -> TimeUtil.isBetween(s.getTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal
                        .getDescription(), userMeal.getCalories(),
                        groupedDatesbyDate.get(userMeal.getDate()) > 0))
                .collect(Collectors.toList());

        return userMealWithExceedList;
    }
}
