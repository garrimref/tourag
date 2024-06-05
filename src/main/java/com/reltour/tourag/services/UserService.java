package com.reltour.tourag.services;

import com.reltour.tourag.domain.User;
import com.reltour.tourag.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto>findAllUsers();

    UserDto convertToDTO(User user);
}