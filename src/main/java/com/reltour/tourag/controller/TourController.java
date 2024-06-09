package com.reltour.tourag.controller;

import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.TourDto;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.security.CustomUserDetails;
import com.reltour.tourag.services.TourService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    private TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public String tours(Model model) {

        List<TourDto> tours = tourService.findAllTours();
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping("/create")
    public String showTourCreateForm(Model model){
        TourDto tourDto = new TourDto();
        model.addAttribute("tour", tourDto);
        return "tour_create";
    }

    @PostMapping("/create")
    public String createTour(@AuthenticationPrincipal User user, @ModelAttribute("tour") TourDto tourDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("tour", tourDto);
            return "tour_create";
        }
        tourDto.setAuthor(user);
        tourService.saveTour(tourDto);
        return "redirect:/tours";

    }
}
