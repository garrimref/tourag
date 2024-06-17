package com.reltour.tourag.controller;

import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.services.TourService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class TourController {

    private final TourService tourService;

    @Value("${upload.path}")
    private String uploadPath;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String tours(Model model) {

        List<Tour> tours = tourService.findAllTours();
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping("/tours/create")
    public String showTourCreateForm(Model model){
        Tour tour = new Tour();
        model.addAttribute("tour", tour);
        return "tourCreate";
    }

    @PostMapping("/tours/create")
    public String createTour(
            @AuthenticationPrincipal User user,
            @ModelAttribute("tour") Tour tour,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        if(bindingResult.hasErrors()){
            model.addAttribute("tour", tour);
            return "tourCreate";
        }

        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            tour.setFilename(resultFilename);
        }

        tour.setAuthor(user);
        tourService.saveTour(tour);
        return "redirect:/tours";

    }
}
