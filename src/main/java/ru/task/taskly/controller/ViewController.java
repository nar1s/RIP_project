package ru.task.taskly.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.task.taskly.model.Deal;
import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.*;
import ru.task.taskly.service.AuthorizationService;
import ru.task.taskly.service.DealService;
import ru.task.taskly.service.ProfileService;
import ru.task.taskly.service.impl.BaseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/view")
public class ViewController {
    private AuthorizationService authorizationService;
    private DealService dealService;
    private BaseService baseService;
    private ProfileService profileService;

    @Autowired
    public ViewController(AuthorizationService authorizationService,
                          DealService dealService,
                          BaseService baseService,
                          ProfileService profileService) {
        this.authorizationService = authorizationService;
        this.dealService = dealService;
        this.baseService = baseService;
        this.profileService = profileService;
    }

    @GetMapping(value = "/enter")
    public String enter(Model model){
        model.addAttribute("user", new UserEnterDto());
        return "enter";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserEnterDto userEnterDto,
                        HttpSession httpSession){
        User user = authorizationService.authorize(userEnterDto);
        httpSession.setAttribute("user", user);
        return "redirect:/view/calendar";
    }

    @GetMapping("/calendar")
    public String calendar(HttpSession httpSession,
                           Model model) {
        User user = (User) httpSession.getAttribute("user");
        HashMap<String, List<DealCalendarDto>> deals = dealService.getCalendarDeal(user.getDeals());
        String dealsJSON = baseService.convertToJSON(deals);
        model.addAttribute("deals",dealsJSON);
        model.addAttribute("user", user);
        return "calendar";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute UserRegistrationDto userRegistrationDto) {
        authorizationService.registration(userRegistrationDto);
        return "redirect:/view/enter";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/view/enter";
    }

    @GetMapping("/profile")
    public String profile(Model model,
                          HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        HashMap<String, List<DealCalendarDto>> deals = dealService.getCalendarDeal(user.getDeals());
        String dealsJSON = baseService.convertToJSON(deals);
        model.addAttribute("updateTaskId", -1);
        model.addAttribute("updateTask", new DealUpdateDto());
        model.addAttribute("update", new UserUpdateDto());
        model.addAttribute("deals", dealsJSON);
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("update") UserUpdateDto userUpdateDto,
                                HttpSession httpSession){
        User oldUser = (User) httpSession.getAttribute("user");
        User newUser = profileService.updateUser(oldUser, userUpdateDto);
        httpSession.setAttribute("user", newUser);
        return "redirect:/view/profile";
    }

    @GetMapping("/add_task")
    public String addTaskGet(Model model){
        model.addAttribute("task", new DealCreateDto());
        return "add_task";
    }

    @PostMapping("/add_task")
    public String addTask(@ModelAttribute("task") DealCreateDto dealCreateDto,
                          HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Deal deal = dealService.addDeal(user, dealCreateDto);
        user.getDeals().add(deal);
        return "redirect:/view/profile";
    }

    @GetMapping("/my_task")
    public String getTasks(HttpSession httpSession,
                           Model model){
        User user = (User) httpSession.getAttribute("user");
        HashMap<String, List<DealCalendarDto>> deals = dealService.getCalendarDeal(user.getDeals());
        final String today = dealService.getTodayDate();
        final String tomorrow = dealService.getTomorrowDate();
        List<DealCalendarDto> todayDeals = deals.getOrDefault(today, Collections.emptyList());
        List<DealCalendarDto> tomorrowDeals = deals.getOrDefault(tomorrow, Collections.emptyList());
        model.addAttribute("todayDeals", todayDeals);
        model.addAttribute("tomorrowDeals", tomorrowDeals);
        model.addAttribute("tomorrow", tomorrow);
        model.addAttribute("today", today);
        return "my_tasks";
    }

    @GetMapping("/timer")
    public String timer(){
        return "timer";
    }

}
