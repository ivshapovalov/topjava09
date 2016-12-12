package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new Meal(LocalDateTime.of(2015, Month.JUNE, 01, 8, 0), "Завтрак", 100),
                new Meal(LocalDateTime.of(2015, Month.JUNE, 01, 15, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.JUNE, 01, 22, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.JUNE, 02, 8, 0), "Завтрак", 1600),
                new Meal(LocalDateTime.of(2015, Month.JUNE, 03, 8, 0), "Ужин", 1000)
        );
        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(meals,
                LocalTime.of(0,
                0), LocalTime.of(23, 59), 1500);


        request.setAttribute("meals",mealsWithExceeded);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }


}
