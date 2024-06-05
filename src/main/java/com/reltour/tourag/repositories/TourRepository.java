package com.reltour.tourag.repositories;

import com.reltour.tourag.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
