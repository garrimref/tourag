package com.reltour.tourag.controller;

import com.reltour.tourag.common.FileTools;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthController {

    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @Value ("lowNoneImage.png")
    private String noneAvatarImagePath;

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
    public String registerUser(
            @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        User existingUser = userService.findByEmail(userDto.getEmail());

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email","228","Ця пошта вже використовується.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            return "/signup";
        }

        if (!file.isEmpty()) {
            userDto.setAvatarFilename(extractFilename(file));
        } else {
            userDto.setAvatarFilename(noneAvatarImagePath);
        }
        userService.saveUser(userDto);
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
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