package com.studentmanagement.service;

import com.studentmanagement.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(int id);

    List<Student> getStudentByInfo(String field,String keyword);

    List<Student> searchStudentsByAnyField(String keyword);

    boolean saveStudent(Student student);

    boolean deleteStudent(int id);

    boolean updateStudent(int id,Student student);
}
