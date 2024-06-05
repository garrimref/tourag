package com.reltour.tourag.services;

import com.reltour.tourag.dto.TourDto;

import java.util.List;

public interface TourService {

    void saveTour(TourDto tourDto);
    List<TourDto> findAllTours();
}
