package com.reltour.tourag.controller;

import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userProfile(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserEmail = authentication.getName();
//
//        User currentUser = userService.findByEmail(currentUserEmail);
//        UserDto userDto = userService.convertToDTO(currentUser);
//        model.addAttribute("user", userDto);

        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
