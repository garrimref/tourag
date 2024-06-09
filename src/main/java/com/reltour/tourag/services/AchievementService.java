package com.reltour.tourag.services;

import com.reltour.tourag.domain.Achievement;

import java.util.List;

public interface AchievementService {
    Achievement createAchievement(String name, String description);
    Achievement updateAchievement(Long id, String name, String description);
    void deleteAchievement(Long id);
    List<Achievement> getAllAchievements();

    Achievement getAchievementById(Long id);
}
