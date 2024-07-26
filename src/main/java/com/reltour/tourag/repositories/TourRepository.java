package com.reltour.tourag.repositories;

import com.reltour.tourag.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    void deleteById(Long id);

    void deleteAllByAuthorId(Long userId);

    List<Tour> findAllByAuthorId(Long author_id);
}
