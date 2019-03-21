package com.elearning.service;

import com.elearning.entity.CourseRegistration;
import com.elearning.repository.CourseRegistrationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationService {
    private static Logger logger = LogManager.getLogger(CourseRegistrationService.class);
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    public CourseRegistration saveCourseRegister(CourseRegistration courseRegistration) {
        logger.info("saveCourseRegister method is called");
        return courseRegistrationRepository.save(courseRegistration);
    }

    public CourseRegistration getByStudentIdAndCourseId(Long studentId, Long courseId) {
        logger.info("getByStudentIdAndCourseId method is called");
        logger.debug("getByStudentIdAndCourseName parameters are studentId and courseId");
        return courseRegistrationRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public List<CourseRegistration> getAll() {
        logger.info("getAll method is called");
        return courseRegistrationRepository.findAll();
    }

    public CourseRegistration getByStudentIdAndCourseName(Long studentId, String courseName) {
        logger.info("getByStudentIdAndCourseName method is called");
        logger.debug("getByStudentIdAndCourseName parameters are studentId and courseName");
        return courseRegistrationRepository.findByStudentIdAndCourseName(studentId, courseName);
    }
}
