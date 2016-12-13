package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealDAOInMemory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

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

    MealDAOInMemory dao;
    private final int caloriesPerDay=1500;

    @Override
    public void init() throws ServletException {
        super.init();

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

        dao = new MealDAOInMemory(meals);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = getAction(req);

        if (path.startsWith("/meals/editmeal")) {
            int id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("meal", dao.getMealById(id));
            jsp("/editmeal",req,resp);
        } else if (path.startsWith("/meals/deletemeal")) {
            int id = Integer.valueOf(req.getParameter("id"));
            try {
                dao.deleteMeal(id);
                redirect("../meals",resp);
            } catch (Exception e) {
                req.setAttribute("message", "Incorrect data. Try again!");
                jsp("/error",req,resp);
            }
        } else if (path.startsWith("/meals")) {
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(dao.getMeals(),
                    LocalTime.of(0,
                            0), LocalTime.of(23, 59), caloriesPerDay);
            req.setAttribute("meals", mealsWithExceeded);
            req.setAttribute("caloriesPerDay", caloriesPerDay);
            jsp("/meals",req,resp);
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = getAction(req);
        if (path.startsWith("/meals/updatemeal")) {
            int id = Integer.valueOf(req.getParameter("id"));
            int calories = Integer.valueOf(req.getParameter("calories"));
            String description = req.getParameter("description");
            try {
            LocalDateTime dateTime = TimeUtil.formatStringToLocalDateTime(req.getParameter
                    ("dateTime"), "yyyy-MM-dd HH:mm");

                dao.updateMeal(new Meal(id, dateTime, description, calories));
                redirect("../meals",resp);


            } catch (Exception e) {
                req.setAttribute("message", "Incorrect data. Try again!");
                jsp("/error",req,resp);
            }
        }

    }
    private void jsp(String jsp, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(jsp + ".jsp").forward(req,resp);
    }

    private void redirect(String url, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(resp.encodeRedirectURL(url));
    }
}
