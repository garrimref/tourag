package com.reltour.tourag.controller;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.domain.Role;
import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.services.TourService;
import com.reltour.tourag.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Value("${upload.path}")
    private String uploadPath;
    private final UserService userService;
    private final TourService tourService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, TourService tourService, PasswordEncoder passwordEncoder) {
        this.tourService = tourService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/community")
    public String showCommunityPage(@AuthenticationPrincipal User currentUser, Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", users);
        return "community";
    }

    @GetMapping("/community/{user}")
    public String userProfile(@PathVariable User user, @AuthenticationPrincipal User currentUser, Model model) {
        Set<Achievement> achievements = userService.getUserAchievements(user.getId());
        List<Tour> tours = tourService.findAllToursByAuthorId(user.getId());
        model.addAttribute("tours", tours);
        model.addAttribute("achievements", achievements);
        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "userProfile";
    }

    @GetMapping("/community/edit/{user}")
    public String showEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/community/edit")
    public String updateUser(
            @AuthenticationPrincipal User currentUser,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Map<String, String> form,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") User user) throws IOException {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        if (!password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        if (!file.isEmpty()) {
            user.setAvatarFilename(extractFilename(file));
        }

        if (currentUser.getRoles().contains(Role.ADMIN)) {
            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());

            user.getRoles().clear();

            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
        }
        userService.saveUser(user);
        return "redirect:/community";
    }
    @GetMapping("/community/delete/{user}")
    public String deleteUser(@AuthenticationPrincipal User currentUser, @PathVariable User user) {
        if (currentUser.equals(user) || currentUser.getRoles().contains(Role.ADMIN)) {
            userService.deleteById(user.getId());
        }
        return "redirect:/community";
    }

    public String extractFilename(MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);

        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }
}
