package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID,USER1_ID);
        MATCHER.assertEquals(USER1_MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1,1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID,USER1_ID);
        MATCHER.assertCollectionEquals(
                Arrays.asList(USER1_MEAL3,USER1_MEAL2,USER1_MEAL4,USER1_MEAL5),
                service.getAll
                        (USER1_ID));

    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1,1);
    }

    @Test
    public void getBetweenDates() throws Exception {

        LocalDate localDate=DateTimeUtil.parseLocalDate
                ("2016-12-22 09:00:00");
        Collection<Meal> allByDates = service.getBetweenDates(localDate,localDate,USER1_ID);
        MATCHER.assertCollectionEquals(
                Arrays.asList(USER1_MEAL4,USER1_MEAL5,USER1_MEAL1), allByDates);

    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime startTime=DateTimeUtil.parseLocalDateTime("2016-12-22 17:00:00");
        LocalDateTime endTime=DateTimeUtil.parseLocalDateTime("2016-12-23 17:00:00");
        Collection<Meal> allByDateTime = service.getBetweenDateTimes(startTime,endTime,USER1_ID);
        MATCHER.assertCollectionEquals(
                Arrays.asList(USER1_MEAL2,USER1_MEAL4), allByDateTime);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(USER1_ID);
        MATCHER.assertCollectionEquals(
                Arrays.asList(USER1_MEAL3,USER1_MEAL2,USER1_MEAL4,USER1_MEAL5,USER1_MEAL1), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal();
        updated.setId(MEAL_ID);
        updated.setDateTime(DateTimeUtil.parseLocalDateTime
                ("2016-12-22 10:00:00"));
        updated.setDescription("Второй завтрак");
        updated.setCalories(1000);
        service.update(updated,USER1_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID,USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotUsersMeal() throws Exception {
        Meal updated = new Meal();
        updated.setId(MEAL_ID+100);
        updated.setDateTime(DateTimeUtil.parseLocalDateTime
                ("2016-12-22 10:00:00"));
        updated.setDescription("Второй завтрак");
        updated.setCalories(1000);
        service.update(updated,USER1_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID,USER1_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal =  new Meal (null, DateTimeUtil.parseLocalDateTime
                ("2016-12-23 19:00:00"),"Поздний ужин",2000);
        Meal created = service.save(newMeal,USER1_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(
                Arrays.asList(newMeal,USER1_MEAL3,USER1_MEAL2,USER1_MEAL4,USER1_MEAL5,USER1_MEAL1),
                service.getAll(USER1_ID));

    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateDateTimeSave() throws Exception {
        Meal meal =  new Meal (null, DateTimeUtil.parseLocalDateTime
                ("2016-12-23 18:00:00"),"Ужин",2000);
        service.save(meal,USER1_ID);
    }

}