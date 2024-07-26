package com.reltour.tourag.controller;

import com.reltour.tourag.common.FileTools;
import com.reltour.tourag.domain.Role;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class TourController {

    private final TourService tourService;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("bigNoneImage.png")
    private String noneImagePath;


    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String tours(Model model) {
        List<Tour> tours = tourService.findAllTours();
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping("/tours/{tour}")
    public String tours(@PathVariable Tour tour,@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("tour", tour);
        return "tourProfile";
    }

    @GetMapping("/tours/create")
    public String showTourCreateForm(Model model) {
        Tour tour = new Tour();
        model.addAttribute("tour", tour);
        return "createTour";
    }

    @PostMapping("/tours/create")
    public String createTour(
            @AuthenticationPrincipal User user,
            @ModelAttribute("tour") Tour tour,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tour", tour);
            return "createTour";
        }

        if (!file.isEmpty()) {
            tour.setFilename(extractFilename(file));
        } else {
            tour.setFilename(noneImagePath);
        }

        tourService.saveTour(user, tour);
        return "redirect:/tours";
    }

    @GetMapping("/tours/edit/{tour}")
    public String showEditForm(@AuthenticationPrincipal User user, @PathVariable Tour tour, Model model) {
        if (tour.getAuthor().equals(user) || user.getRoles().contains(Role.ADMIN)) {
            model.addAttribute(tour);
            return "editTour";
        }
        return "redirect:/tours";
    }
    @PostMapping("/tours/edit")
    public String updateTour(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam String date,
            @RequestParam("tourId") Tour tour,
            @RequestParam("file") MultipartFile file) throws IOException, ParseException {

        tour.setName(name);
        tour.setDescription(description);
        tour.setLocation(location);
        if (!date.isEmpty()) {
            tour.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }

        if (!file.isEmpty()) {
            tour.setFilename(extractFilename(file));
        }

        tourService.saveTour(tour);
        return "redirect:/tours";
    }

    @GetMapping("/tours/delete/{tour}")
    public String deleteTour(@AuthenticationPrincipal User user, @PathVariable Tour tour) {
        if (tour.getAuthor().equals(user) || user.getRoles().contains(Role.ADMIN)) {
            tourService.deleteById(tour.getId());
        }
        return "redirect:/tours";
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

