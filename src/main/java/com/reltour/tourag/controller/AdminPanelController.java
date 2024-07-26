package com.reltour.tourag.controller;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.services.AchievementService;
import com.reltour.tourag.services.TourService;
import com.reltour.tourag.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {


    private final UserService userService;
    private final TourService tourService;
    private final AchievementService achievementService;

    public AdminPanelController(UserService userService, TourService tourService, AchievementService achievementService) {
        this.userService = userService;
        this.tourService = tourService;
        this.achievementService = achievementService;
    }

    @GetMapping("/admin")
    public String showControlList(Model model) {
        List<Tour> tours = tourService.findAllTours();
        model.addAttribute("tours", tours);
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "adminPanel";
    }

    @GetMapping("/admin/achievements")
    public String showAchievementsMenu(Model model) {
        List<Achievement> achievements = achievementService.findAllAchievements();
        model.addAttribute("achievements", achievements);
    return "achievementsMenu";
    }
}
