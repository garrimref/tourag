package com.reltour.tourag.services;

import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.domain.User;

import java.util.List;

public interface TourService {

    void saveTour(Tour tour);
    List<Tour> findAllTours();

    void deleteById(Long id);
    void deleteAllByUserId(Long userId);

    void saveTour(User user, Tour tour);

    List<Tour> findAllToursByAuthorId(Long id);
}
