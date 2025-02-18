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
import java.util.Map;
import java.util.stream.Collectors;

// Đánh dấu lớp này là một service trong Spring Boot.
@Service
public class StudentService {

    private List<Student> students; // Lưu trữ danh sách sinh viên dưới dạng list.
    private Map<String, Student> studentMap; // Lưu trữ danh sách sinh viên dưới dạng map để tối ưu hóa việc tìm kiếm theo ID.

    /**
     * Phương thức init() sẽ được thực thi ngay sau khi ứng dụng khởi động.
     * Đọc dữ liệu từ file JSON và lưu vào bộ nhớ (list và map).
     */
    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper(); // Sử dụng ObjectMapper để đọc file JSON.

        try (InputStream inputStream = getClass().getResourceAsStream("/students.json")) { // Mở file JSON từ resources.
            if (inputStream == null) {
                throw new RuntimeException("File students.json not found in resources!"); // Ném exception nếu file không tồn tại.
            }

            // Đọc dữ liệu từ file JSON và ánh xạ vào list students.
            students = mapper.readValue(inputStream, new TypeReference<>() {});

            // Chuyển đổi list students thành map với key là ID và value là đối tượng Student.
            studentMap = students.stream()
                    .collect(Collectors.toMap(Student::getId, student -> student));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load students data from JSON", e); // Ném exception nếu xảy ra lỗi khi đọc file.
        }
    }

    /**
     * Lấy danh sách tất cả sinh viên.
     *
     * @return Danh sách sinh viên hoặc danh sách rỗng nếu không có dữ liệu.
     */
    public List<Student> getAllStudents() {
        return students != null ? students : Collections.emptyList();
    }

    /**
     * Lấy thông tin sinh viên theo ID.
     *
     * @param id ID của sinh viên cần tìm kiếm.
     * @return Đối tượng Student hoặc null nếu không tìm thấy.
     */
    public Student getStudentById(String id) {
        return studentMap != null ? studentMap.get(id) : null;
    }
}