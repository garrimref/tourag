package com.reltour.tourag.controller;

import com.reltour.tourag.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private UserService userService;

    @GetMapping( "/")
    public String index() {
        return "index";
    }
}