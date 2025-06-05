package com.studentmanagement.controller;

import com.studentmanagement.Entity.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "list";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam(value="field", required = false) String field,
                                 @RequestParam("keyword") String keyword,
                                 Model model) {

        List<Student> students;
        if (field == null || field.isEmpty()) {
            // Search all fields
            students = service.searchStudentsByAnyField(keyword);
        } else {
            // Search by selected field only
            students = service.getStudentByInfo(field, keyword);
        }

        model.addAttribute("students", students);
        return "list"; // your view name
    }


    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/";
    }

}
