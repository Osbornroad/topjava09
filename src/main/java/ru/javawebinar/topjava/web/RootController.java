package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService service;

    @Autowired
    private MealService mealService;

    @Autowired
    MealRestController mealRestController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model, HttpServletRequest request) {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("meals", mealRestController.getAll());
            model.addAttribute("meals", mealRestController.getAll());
            return "meals";
        } else if ("delete".equals(action)) {
            int id = getId(request);
            mealRestController.delete(id);
            return "redirect:meals";
        } /*else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = "create".equals(action) ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                    mealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        }*/
        return "redirect:meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
