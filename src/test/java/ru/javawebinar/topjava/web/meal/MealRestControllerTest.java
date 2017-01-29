package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


/**
 * Created by User on 27.01.2017.
 */
public class MealRestControllerTest extends AbstractControllerTest {

    private static final String MEAL_REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(MEAL_REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(MEAL_REST_URL))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(MEAL_WITH_EXCEED_MATCHER.contentListMatcher(MEALS_WITH_EXCEED)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(MEAL_REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(MEALS_DELETED, mealService.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(999);
        mockMvc.perform(put(MEAL_REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, AuthorizedUser.id()));
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = new Meal(of(2020, Month.JANUARY, 01, 12, 59), "Created meal", 199);
        ResultActions action = mockMvc.perform(post(MEAL_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());


        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        List<Meal> estimatedList = new ArrayList<>();
        estimatedList.addAll(MEALS);
        estimatedList.add(0, expected);
        MATCHER.assertCollectionEquals(estimatedList, mealService.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        TestUtil.print(mockMvc.perform(get(MEAL_REST_URL +
                "between?startDate=2015-05-29&startTime=12:00:00&endDate=2015-05-30&endTime=20:00:01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_WITH_EXCEED_MATCHER.contentListMatcher(MEALS_BETWEEN)));
    }
/*
'2011-12-03T10:15:30'
startDate, startTime, endDate, endTime

@DateTimeFormat(iso = ISO.DATE)
    private LocalDate localDate;

    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime localTime;

    @DateTimeFormat(iso = ISO.TIME)
    private OffsetTime offsetTime;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime localDateTime;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private ZonedDateTime zonedDateTime;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime offsetDateTime;
 */
}