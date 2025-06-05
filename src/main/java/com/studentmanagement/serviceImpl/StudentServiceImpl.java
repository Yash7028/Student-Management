package com.studentmanagement.serviceImpl;

import com.studentmanagement.Entity.Student;
import com.studentmanagement.Repository.StudentRepo;
import com.studentmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getStudentByInfo(String field, String keyword) {
        switch (field) {
            case "firstName":
                return studentRepo.findByFirstNameContainingIgnoreCase(keyword);
            case "middleName":
                return studentRepo.findByMiddleNameContainingIgnoreCase(keyword);
            case "lastName":
                return studentRepo.findByLastNameContainingIgnoreCase(keyword);
            case "course":
                return studentRepo.findByCourseContainingIgnoreCase(keyword);
            default:
                return List.of(); // fallback if field is invalid
        }
    }

    @Override
    public List<Student> searchStudentsByAnyField(String keyword) {
        return studentRepo.findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCourseContainingIgnoreCase(
                keyword, keyword, keyword, keyword);
    }


    @Override
    @Transactional
    public boolean saveStudent(Student student) {
        try {
            studentRepo.save(student);
            return true;
        }catch (Exception e){
            log.error("Something went wrong: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteStudent(int id) {
        if (studentRepo.existsById(id)){
            studentRepo.deleteById(id);
            return  true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(int id, Student updatedStudent) {

        try {
            Student student = studentRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            // Update fields
            student.setFirstName(updatedStudent.getFirstName());
            student.setMiddleName(updatedStudent.getMiddleName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setCourse(updatedStudent.getCourse());

            studentRepo.save(student);
            return true;

        } catch (Exception e) {
            log.error("Something went wrong: {}", e.getMessage(), e);
            return false;
        }

    }
}
