package com.elearning.controller;

import com.elearning.dto.CourseRegisterDTO;
import com.elearning.entity.CourseRegistration;
import com.elearning.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseRegisterationController {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @GetMapping("/all/registeration")
    public List<CourseRegisterDTO> getAll() {
        List<CourseRegistration> courseRegistrations = courseRegistrationService.getAll();

        return courseRegistrations.stream().map(courseReg -> {
            CourseRegisterDTO courseRegisterDTO = new CourseRegisterDTO();
            courseRegisterDTO.setStudentId(courseReg.getStudent().getId());
            courseRegisterDTO.setCourseId(courseReg.getCourse().getId());
            return courseRegisterDTO;
        }).collect(Collectors.toList());
    }
}
