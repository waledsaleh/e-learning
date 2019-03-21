package com.elearning.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    @Column(name = "last_updated")
    private LocalDate lastUpdated;
    @Column(name = "total_hours")
    private int totalHours;
    @Column(name = "instructor")
    private String instructor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    List<CourseRegistration> registrations;

    public Course() {
    }

    public Course(Long id, String name, String description, LocalDate publishDate, LocalDate lastUpdated, int totalHours, String instructor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.lastUpdated = lastUpdated;
        this.totalHours = totalHours;
        this.instructor = instructor;
    }

    public List<CourseRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<CourseRegistration> registrations) {
        this.registrations = registrations;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return totalHours == course.totalHours &&
                Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(publishDate, course.publishDate) &&
                Objects.equals(lastUpdated, course.lastUpdated) &&
                Objects.equals(instructor, course.instructor) &&
                Objects.equals(registrations, course.registrations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, publishDate, lastUpdated, totalHours, instructor, registrations);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishDate=" + publishDate +
                ", lastUpdated=" + lastUpdated +
                ", totalHours=" + totalHours +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
