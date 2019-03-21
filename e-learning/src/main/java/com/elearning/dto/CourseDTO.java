package com.elearning.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class CourseDTO {
    @NotEmpty(message = "Please enter course name")
    private String name;
    @NotEmpty(message = "Please enter description")
    private String description;
    @NotEmpty(message = "Please enter publishDate")
    private LocalDate publishDate;
    @NotEmpty(message = "Please enter lastUpdated")
    private LocalDate lastUpdated;
    @NotEmpty(message = "Please enter totalHours")
    private int totalHours;
    @NotEmpty(message = "Please enter instructor")
    private String instructor;

    public CourseDTO() {
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
