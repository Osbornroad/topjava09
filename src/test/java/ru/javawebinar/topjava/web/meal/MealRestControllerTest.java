package ru.javawebinar.topjava.web.meal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
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
                .andExpect(MEAL_MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {

    }

}