package com.elearning.service;

import com.elearning.dto.CourseDTO;
import com.elearning.dto.StudentDTO;
import com.elearning.entity.Course;
import com.elearning.entity.CourseRegistration;
import com.elearning.entity.Student;
import com.elearning.exception.CourseAlreadyExistException;
import com.elearning.exception.StudentAlreadyExistException;
import com.elearning.exception.StudentNotFoundException;
import com.elearning.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static Logger logger = LogManager.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CourseService courseService;

    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String loginStudentByEmailAndPassword(String email, String password) {
        logger.info("loginStudentByEmailAndPassword is called");

        logger.debug("loginStudentByEmailAndPassword parameters are email and password");
        Student student = findByEmail(email);
        if (Objects.isNull(student)) {
            logger.error("StudentNotFoundException");
            throw new StudentNotFoundException("Student username not found");
        }
        if (email.equalsIgnoreCase(student.getEmail()) && bCryptPasswordEncoder.matches(password, student.getPassword())) {
            return "Login success";
        }

        return "Email or Password is incorrect";
    }

    public Student retrieveStudent(Long studentId) {
        logger.info("retrieveStudent is called");
        logger.debug("retrieveStudent parameter is studentId");

        Student student = studentRepository.getOne(studentId);

        if (Objects.isNull(student)) {
            logger.error("StudentNotFoundException");
            throw new StudentNotFoundException("Student is not exist");
        }
        return student;
    }

    public List<CourseDTO> retrieveCourses(Long studentId) {
        logger.info("retrieveCourses is called");
        logger.debug("retrieveCourses parameter is studentId");

        Student student = retrieveStudent(studentId);

        List<CourseRegistration> courseRegistrations = student.getRegistrations();

        return courseRegistrations.stream().filter(course -> !course.isRegister()).map(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setDescription(course.getCourse().getDescription());
            courseDTO.setInstructor(course.getCourse().getInstructor());
            courseDTO.setLastUpdated(course.getCourse().getLastUpdated());
            courseDTO.setName(course.getCourse().getName());
            courseDTO.setPublishDate(course.getCourse().getPublishDate());
            courseDTO.setTotalHours(course.getCourse().getTotalHours());

            return courseDTO;
        }).collect(Collectors.toList());
    }

    public void addCourse(Long studentId, CourseDTO courseDTO) {
        logger.info("addCourse is called");
        logger.debug("addCourse parameters are studentId and courseDTO");

        if (Objects.nonNull(courseRegistrationService.getByStudentIdAndCourseName(studentId, courseDTO.getName()))) {
            logger.error("CourseAlreadyExistException");
            throw new CourseAlreadyExistException("Course is already added to this student");
        }
        Student student = retrieveStudent(studentId);

        Course course = new Course();
        course.setDescription(courseDTO.getDescription());
        course.setInstructor(courseDTO.getInstructor());
        course.setLastUpdated(courseDTO.getLastUpdated());
        course.setName(courseDTO.getName());
        course.setPublishDate(courseDTO.getPublishDate());
        course.setTotalHours(courseDTO.getTotalHours());

        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setStudent(student);

        courseRegistrationService.saveCourseRegister(courseRegistration);

        logger.debug("Course Added");

    }

    public void unRegisterCourse(Long studentId, Long courseId) {
        logger.info("unRegisterCourse is called");
        logger.debug("unRegisterCourse parameters are studentId and courseId");

        Student student = retrieveStudent(studentId);
        Course course = courseService.findById(courseId);

        CourseRegistration courseRegistration = courseRegistrationService.getByStudentIdAndCourseId(student.getId(), course.getId());
        courseRegistration.setRegister(true);

        courseRegistrationService.saveCourseRegister(courseRegistration);

        logger.debug("Delete Success");
    }

    public Student findByEmail(String email) {
        logger.info("findByEmail is called");
        return studentRepository.findByEmail(email);
    }

    public void createStudent(StudentDTO studentDTO) {
        logger.info("createStudent is called");
        logger.debug("createStudent parameter is studentDTO");

        if (Objects.nonNull(findByEmail(studentDTO.getEmail()))) {
            logger.error("StudentAlreadyExistException");
            throw new StudentAlreadyExistException("Student is already exist");
        }
        Student student = new Student();

        student.setDateOfBirth(LocalDate.parse(studentDTO.getDateOfBirth(), dateTimeFormatter));
        student.setEmail(studentDTO.getEmail());
        student.setGender(studentDTO.getGender());
        student.setName(studentDTO.getName());
        student.setPassword(bCryptPasswordEncoder.encode(studentDTO.getPassword()));
        student.setUsername(studentDTO.getUsername());

        studentRepository.save(student);

        logger.debug("Student Saved Success");

    }
}
