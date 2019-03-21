package com.elearning.controller;

import com.elearning.dto.CourseDTO;
import com.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAll();
    }
}
