package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Đánh dấu lớp này là một REST controller, tự động chuyển đổi đối tượng Java thành JSON khi trả về phản hồi.
@RestController
@RequestMapping("/api") // Định nghĩa tiền tố chung cho tất cả các endpoint (ví dụ: /api/student/{id}).
public class StudentController {

    // Inject instance của StudentService vào controller để sử dụng các phương thức của service.
    @Autowired
    private StudentService studentService;

    /**
     * Endpoint GET để lấy thông tin sinh viên theo ID.
     * Ví dụ: GET /api/student/1
     *
     * @param id ID của sinh viên cần tìm kiếm.
     * @return ResponseEntity chứa thông tin sinh viên hoặc mã lỗi 404 nếu không tìm thấy.
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        // Gọi phương thức getStudentById từ service để lấy thông tin sinh viên theo ID.
        Student student = studentService.getStudentById(id);

        // Nếu sinh viên không tồn tại, trả về mã lỗi 404 NOT FOUND.
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Trả về mã 200 OK và thông tin sinh viên.
        return ResponseEntity.ok(student);
    }

    /**
     * Endpoint GET để lấy danh sách tất cả sinh viên.
     * Ví dụ: GET /api/students
     *
     * @return ResponseEntity chứa danh sách tất cả sinh viên.
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        // Gọi phương thức getAllStudents từ service để lấy danh sách sinh viên.
        List<Student> students = studentService.getAllStudents();

        // Trả về mã 200 OK và danh sách sinh viên.
        return ResponseEntity.ok(students);
    }
}