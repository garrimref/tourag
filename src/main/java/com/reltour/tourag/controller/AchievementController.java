package com.reltour.tourag.controller;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.services.AchievementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/achievements/edit/{achievement}")
    public String showEditForm(@PathVariable Achievement achievement, Model model) {
        model.addAttribute("achievement", achievement);
        return "editAchievement";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/achievements/edit")
    public String updateUser(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam("achievementId") Achievement achievement) {
        achievement.setName(name);
        achievement.setDescription(description);

        achievementService.saveAchievement(achievement);
        return "redirect:/admin/achievements";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/achievements/create")
    public String showCreateForm(Model model) {
        Achievement achievement = new Achievement();
        model.addAttribute("achievement", achievement);
        return "createAchievement";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/achievements/create")
    public String createAchievement(@ModelAttribute("achievement") Achievement achievement) {
        achievementService.saveAchievement(achievement);
        return "redirect:/admin/achievements";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/achievements/delete/{achievement}")
    public String deleteTour(@PathVariable Achievement achievement) {
        achievementService.deleteById(achievement.getId());
        return "redirect:/admin/achievements";
    }
}
