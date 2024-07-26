package com.reltour.tourag.services.impl;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.repositories.AchievementRepository;
import com.reltour.tourag.services.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    public List<Achievement> findAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement getAchievementById(Long id) {
        return achievementRepository.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found"));
    }

    @Override
    public void saveAchievement(Achievement achievement) {
        achievementRepository.save(achievement);
    }

    @Override
    public void deleteById(Long id) {
        achievementRepository.deleteById(id);
    }
}