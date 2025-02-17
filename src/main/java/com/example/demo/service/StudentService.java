package com.example.demo.service;

import com.example.demo.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        // Đọc file JSON từ classpath
        InputStream inputStream = getClass().getResourceAsStream("/students.json");

        if (inputStream == null) {
            throw new RuntimeException("File students.json not found in resources!");
        }

        try {
            // Ánh xạ dữ liệu từ JSON sang danh sách Student
            students = mapper.readValue(inputStream, new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load students data from JSON", e);
        }
    }

    /**
     * Lấy danh sách tất cả sinh viên.
     */
    public List<Student> getAllStudents() {
        return students != null ? students : Collections.emptyList();
    }

    /**
     * Lấy thông tin sinh viên theo ID.
     *
     * @param id ID của sinh viên cần tìm.
     * @return Đối tượng Student nếu tìm thấy, hoặc null nếu không tìm thấy.
     */
    public Student getStudentById(String id) {
        if (students == null || students.isEmpty()) {
            return null;
        }
        return students.stream()
                .filter(student -> String.valueOf(student.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }
}