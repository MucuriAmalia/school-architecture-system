package com.emtech.students_microservice.controller;

import com.emtech.students_microservice.dto.StudentDTO;
import com.emtech.students_microservice.implementation.StudentServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")


public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    // 1️⃣ Register a new student
    @PostMapping("/register")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO savedStudent = studentService.registerStudent(studentDTO);
        return ResponseEntity.ok(savedStudent);
    }


    // 2️⃣ Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }


    // 3️⃣ Get student by Admission Number
    @GetMapping("/admission/{admissionNumber}")
    public ResponseEntity<Object> getStudentByAdmissionNumber(@PathVariable String admissionNumber) {
        return  ResponseEntity.ok().body(studentService.getStudentByAdmissionNumber(admissionNumber)
        );
    }


    // 4️⃣ Get all students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // 5️⃣ Update student details
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO updatedStudentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, updatedStudentDTO));
    }


    // 6️⃣ Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
