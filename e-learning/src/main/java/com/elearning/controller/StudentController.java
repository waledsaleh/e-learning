package com.elearning.controller;

import com.elearning.dto.CourseDTO;
import com.elearning.dto.LoginDTO;
import com.elearning.dto.StudentDTO;
import com.elearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<String> studentLogin(@RequestBody @Valid LoginDTO loginDTO) {
        return new ResponseEntity<>(studentService.loginStudentByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<CourseDTO> retrieveCoursesForStudent(@PathVariable Long studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @PostMapping(value = "/students/{studentId}/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerStudentForCourse(
            @PathVariable Long studentId, @RequestBody @Valid CourseDTO newCourse) {
        studentService.addCourse(studentId, newCourse);
        return new ResponseEntity("Course Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Boolean> unRegisterCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.unRegisterCourse(studentId, courseId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/student/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return new ResponseEntity("Register Success", HttpStatus.CREATED);
    }

}
