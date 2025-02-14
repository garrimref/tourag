package com.reltour.tourag.services.impl;

import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.domain.Role;
import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;
import com.reltour.tourag.repositories.AchievementRepository;
import com.reltour.tourag.repositories.UserRepository;
import com.reltour.tourag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AchievementRepository achievementRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setAvatarFilename(userDto.getAvatarFilename());
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        //tourService.deleteAllByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> users_dto = new ArrayList<>();
        for (User user : users) {
            users_dto.add(convertToDTO(user));
        }
        return users_dto;
    }

    public UserDto convertToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAchievements(user.getAchievements());
        userDto.setRoles(user.getRoles());
        userDto.setAvatarFilename(user.getAvatarFilename());
        return userDto;
    }

    public void addAchievementToUser(Long userId, Long achievementId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));

        user.getAchievements().add(achievement);
        userRepository.save(user);
    }

    public Set<Achievement> getUserAchievements(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAchievements();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
