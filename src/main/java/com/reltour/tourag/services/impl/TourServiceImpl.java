package com.reltour.tourag.services.impl;

import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.repositories.TourRepository;
import com.reltour.tourag.services.TourService;
import com.reltour.tourag.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final UserService userService;

    public TourServiceImpl(TourRepository tourRepository, UserService userService) {
        this.tourRepository = tourRepository;
        this.userService = userService;
    }

    @Override
    public void saveTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void saveTour(User user, Tour tour) {
        tour.setAuthor(user);
        userService.addAchievementToUser(user.getId(), 1L);
        tourRepository.save(tour);

    }

    @Override
    public List<Tour> findAllToursByAuthorId(Long id) {
        return tourRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Tour> findAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        tourRepository.deleteAllByAuthorId(userId);
    }


}
