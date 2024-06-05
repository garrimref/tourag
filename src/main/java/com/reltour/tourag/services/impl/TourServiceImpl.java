package com.reltour.tourag.services.impl;

import com.reltour.tourag.domain.Tour;
import com.reltour.tourag.dto.TourDto;
import com.reltour.tourag.repositories.TourRepository;
import com.reltour.tourag.services.TourService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public void saveTour(TourDto tourDto) {
        Tour tour = new Tour();
        tour.setAuthor(tourDto.getAuthor());
        tour.setName(tourDto.getName());
        tour.setDescription(tourDto.getDescription());
        tour.setLocation(tourDto.getLocation());
        tour.setDate(tourDto.getDate());
        tourRepository.save(tour);
    }

    @Override
    public List<TourDto> findAllTours() {
        Iterable<Tour> tours = tourRepository.findAll();
        List<TourDto> tours_dto = new ArrayList<>();
        for (Tour tour :
                tours) {
            tours_dto.add(convertToDto(tour));
        }
        return tours_dto;
    }

    private TourDto convertToDto(Tour tour) {
        TourDto tour_dto = new TourDto();
        tour_dto.setId(tour.getId());
        tour_dto.setAuthor(tour.getAuthor());
        tour_dto.setName(tour.getName());
        tour_dto.setDescription(tour.getDescription());
        tour_dto.setLocation(tour.getLocation());
        tour_dto.setDate(tour.getDate());
        return tour_dto;
    }
}
