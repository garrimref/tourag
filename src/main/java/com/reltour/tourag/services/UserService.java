package com.reltour.tourag.services;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void saveUser(UserDto userDto);
    void saveUser(User user);
    void deleteById(Long id);

    User findByEmail(String email);

    List<UserDto>findAllUsers();

    UserDto convertToDTO(User user);

    void addAchievementToUser(Long userId, Long achievementId);
    Set<Achievement> getUserAchievements(Long userId);

}