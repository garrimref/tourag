package com.reltour.tourag.services.impl;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.repositories.AchievementRepository;
import com.reltour.tourag.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public Achievement createAchievement(String name, String description) {
        Achievement achievement = new Achievement();
        achievement.setName(name);
        achievement.setDescription(description);
        return achievementRepository.save(achievement);
    }

    public Achievement updateAchievement(Long id, String name, String description) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievement.setName(name);
        achievement.setDescription(description);
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement getAchievementById(Long id) {
        return achievementRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}