package com.reltour.tourag.services;

import com.reltour.tourag.domain.Tour;

import java.util.List;

public interface TourService {

    //void saveTour(TourDto tourDto);
    void saveTour(Tour tour);
    List<Tour> findAllTours();
}
