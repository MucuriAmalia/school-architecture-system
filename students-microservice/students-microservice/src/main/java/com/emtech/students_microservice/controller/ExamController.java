package com.emtech.students_microservice.controller;

import com.emtech.students_microservice.dto.ExamDTO;
import com.emtech.students_microservice.dto.ReportDTO;
import com.emtech.students_microservice.dto.StudentDTO;
import com.emtech.students_microservice.implementation.ExamServiceImpl;
import com.emtech.students_microservice.implementation.StudentServiceImpl;
import com.emtech.students_microservice.service.ExamService;
import com.emtech.students_microservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamServiceImpl examService;
    private final StudentServiceImpl studentService;

    public ExamController(ExamServiceImpl examService, StudentServiceImpl studentService) {
        this.examService = examService;
        this.studentService = studentService;
    }

    // 1️⃣ Record a new exam result for a student
    @PostMapping("/{studentId}/create")
    public ResponseEntity<ExamDTO> createExam(
            @PathVariable Long studentId,
            @RequestBody ExamDTO examDTO
    ) {
        try {
            System.out.println("📩 Received API request to record exam for student ID: " + studentId);

            // Validate if the student exists
            StudentDTO student = studentService.getStudentById(studentId);
            if (student == null) {
                System.err.println("❌ Student with ID " + studentId + " not found!");

            return ResponseEntity.notFound().build();
        }

        // Set student ID and save exam
        examDTO.setStudentId(studentId);
        ExamDTO savedExam = examService.createExam(examDTO);

            System.out.println("✅ Exam recorded successfully!");
        return ResponseEntity.ok(savedExam);
    } catch (Exception e) {
            System.err.println("❌ Error recording exam: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 2️⃣ Get exam by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO exam = examService.getExamById(id);
        return exam != null ? ResponseEntity.ok(exam) : ResponseEntity.notFound().build();
    }

    // 3️⃣ Get all exams for a student
    @GetMapping("/students/{studentId}/exams")
    public ResponseEntity<List<ExamDTO>> getStudentExams(@PathVariable Long studentId) {
        List<ExamDTO> exams = examService.getExamsByStudentId(studentId);
        return ResponseEntity.ok(exams);
    }

    // 4️⃣ Get all exams
    @GetMapping("/all")
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportDTO>> getTermYearReport(@RequestParam String term,
                                                             @RequestParam int year) {
        List<ReportDTO> reports = examService.generateTermYearReport(term, year);
        return ResponseEntity.ok(reports);
    }

    // 5️⃣ Delete an exam record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
