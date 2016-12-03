package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

       // List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
//        LocalDate currentDate = mealList.get(0).getDateTime().toLocalDate();
//        Map<LocalDate, Boolean> exceededDates = new HashMap<>();
//        exceededDates.put(currentDate, false);
//        int calories = 0;
//        boolean exceed = false;
//        for (UserMeal meal : mealList
//                ) {
//            if (!meal.getDateTime().toLocalDate().isEqual(currentDate)) {
//                calories = 0;
//                currentDate = meal.getDateTime().toLocalDate();
//            }
//
//            calories += meal.getCalories();
//            if (calories > caloriesPerDay) {
//                exceededDates.put(currentDate, true);
//            }
//        }
//        for (UserMeal meal : mealList
//                ) {
//            if (meal.getDateTime().toLocalTime().compareTo(endTime) <= 0 &&
//                    meal.getDateTime().toLocalTime().compareTo(startTime) >= 0) {
//                userMealWithExceedList.add(new
//                        UserMealWithExceed(meal
//                        .getDateTime(),
//                        meal.getDescription(), meal.getCalories(),
//                        exceededDates.get(meal.getDateTime().toLocalDate())));
//            }
//
//        }
        Map<LocalDate, Integer> exceededDates=
                mealList.stream()
                        .collect(Collectors.groupingBy(p -> p.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)))
                ;


        List<UserMealWithExceed> userMealWithExceedList = mealList.stream()
                .filter((s) -> s.getDateTime().toLocalTime().compareTo(endTime) <= 0)
                .filter((s) -> s.getDateTime().toLocalTime().compareTo(startTime) >= 0)
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal
                        .getDescription(),
                        userMeal.getCalories(),
                        exceededDates.get(userMeal.getDateTime().toLocalDate())
                                .compareTo(caloriesPerDay)>0))
                .collect(Collectors.toList());


        return userMealWithExceedList;    }
}
