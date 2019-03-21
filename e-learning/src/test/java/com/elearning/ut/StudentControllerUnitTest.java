package com.elearning.ut;

import com.elearning.dto.StudentDTO;
import com.elearning.entity.Student;
import com.elearning.exception.StudentAlreadyExistException;
import com.elearning.repository.StudentRepository;
import com.elearning.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerUnitTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test(expected = StudentAlreadyExistException.class)
    public void testStudent_registerStudentForCourse_StudentExistException() throws Exception {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setDateOfBirth("1994-02-01");
        studentDTO.setEmail("test@email.com");
        studentDTO.setGender("m");
        studentDTO.setName("student-test");
        studentDTO.setUsername("student-username");
        studentDTO.setPassword("student-password");

        Student testStudent = new Student();
        testStudent.setEmail("test@email.com");

        when(studentRepository.findByEmail(Mockito.anyString())).thenReturn(testStudent);

        studentService.createStudent(studentDTO);
    }
}
