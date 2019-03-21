package com.elearning.repository;

import com.elearning.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    @Query("SELECT c from CourseRegistration c where c.student.id = :studentId and c.course.id = :courseId")
    CourseRegistration findByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Query("SELECT c from CourseRegistration c where c.student.id = :studentId and c.course.name = :courseName")
    CourseRegistration findByStudentIdAndCourseName(@Param("studentId") Long studentId, @Param("courseName") String courseName);

}
