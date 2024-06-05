package com.reltour.tourag.controller;

import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "signup";

    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        User existingUser = userService.findByEmail(userDto.getEmail());

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email","228","Ця пошта вже використовується.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            return "/signup";
        }

        userService.saveUser(userDto);
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}