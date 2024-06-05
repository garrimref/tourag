package com.reltour.tourag.dto;

import com.reltour.tourag.domain.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TourDto {
    private Long id;
    private User author;
    private String name;
    private String description;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public TourDto() {
    }

    public TourDto(Long id, User author, String name, String description, String location, Date date) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public String getAuthorName() {
        return author.getFirstName() + " " +author.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
