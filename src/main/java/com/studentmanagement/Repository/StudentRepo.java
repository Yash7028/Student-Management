package com.studentmanagement.Repository;

import com.studentmanagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student , Integer> {
    List<Student> findByFirstNameContainingIgnoreCase(String keyword);
    List<Student> findByMiddleNameContainingIgnoreCase(String keyword);
    List<Student> findByLastNameContainingIgnoreCase(String keyword);
    List<Student> findByCourseContainingIgnoreCase(String keyword);

    // Multi-field OR search method
    List<Student> findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCourseContainingIgnoreCase(
            String firstNameKeyword, String middleNameKeyword, String lastNameKeyword, String courseKeyword);

}
