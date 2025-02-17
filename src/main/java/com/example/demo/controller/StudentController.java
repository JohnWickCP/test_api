package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin(origins = "http://localhost:9090")
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Hiển thị trang web
    @GetMapping("/student/{id}")
    public String getStudentPage(@PathVariable String id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student";
    }

    // API trả về JSON
    @GetMapping("/api/student/{id}")
    @ResponseBody
    public Student getStudentApi(@PathVariable String id) {
        return studentService.getStudentById(id);
    }
}