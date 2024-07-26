package com.reltour.tourag.services;

import com.reltour.tourag.domain.Achievement;

import java.util.List;

public interface AchievementService {

    void deleteAchievement(Long id);
    List<Achievement> findAllAchievements();
    Achievement getAchievementById(Long id);
    void saveAchievement(Achievement achievement);

    void deleteById(Long id);
}
