package com.elearning.service;

import com.elearning.dto.CourseDTO;
import com.elearning.entity.Course;
import com.elearning.exception.CourseNotFoundException;
import com.elearning.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private static Logger logger = LogManager.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRegistrationService courseRegistrationService;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<CourseDTO> getAll() {
        logger.info("getAll called");
        return courseRegistrationService.getAll().stream().filter(courseRegistration -> !courseRegistration.isRegister()).map(courseRegistration -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setDescription(courseRegistration.getCourse().getDescription());
            courseDTO.setInstructor(courseRegistration.getCourse().getInstructor());
            courseDTO.setLastUpdated(courseRegistration.getCourse().getLastUpdated());
            courseDTO.setName(courseRegistration.getCourse().getName());
            courseDTO.setPublishDate(courseRegistration.getCourse().getPublishDate());
            courseDTO.setTotalHours(courseRegistration.getCourse().getTotalHours());
            return courseDTO;
        }).collect(Collectors.toList());
    }

    public Course findById(Long id) {
        logger.info("findById method called");
        logger.debug("findById parameter is id");
        Course course = courseRepository.getOne(id);
        if (Objects.isNull(course))
            throw new CourseNotFoundException("Course is not exist");
        return course;
    }

    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }
}
