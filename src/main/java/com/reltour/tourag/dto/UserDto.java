package com.reltour.tourag.dto;


import com.reltour.tourag.domain.Achievement;
import com.reltour.tourag.domain.Role;

import java.util.Set;

public class UserDto {

    private Long id;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private Set<Achievement> achievements;
    private Set<Role> roles ;

    private String AvatarFilename;

    public UserDto(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarFilename() {
        return AvatarFilename;
    }

    public void setAvatarFilename(String avatarFilename) {
        AvatarFilename = avatarFilename;
    }
}
