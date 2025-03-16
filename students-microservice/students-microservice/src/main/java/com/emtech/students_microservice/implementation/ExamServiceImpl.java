package com.emtech.students_microservice.implementation;

import com.emtech.students_microservice.dto.ExamDTO;
import com.emtech.students_microservice.dto.ReportDTO;
import com.emtech.students_microservice.model.Exam;
import com.emtech.students_microservice.model.Student;
import com.emtech.students_microservice.exception.ResourceNotFoundException;
import com.emtech.students_microservice.repository.ExamRepository;
import com.emtech.students_microservice.repository.StudentRepository;
import com.emtech.students_microservice.service.ExamService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.emtech.students_microservice.utils.GradingSystemUtil.getRemarks;

@Service
@Getter
@Setter
@Transactional

public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    // Convert DTO to Entity
    private Exam mapToEntity(ExamDTO dto) {
        System.out.println("Mapping DTO to Entity...");
        System.out.println("Received Student ID: " + dto.getStudentId());

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> {
                    System.err.println("❌ Student not found with ID: " + dto.getStudentId());
                    return new ResourceNotFoundException("Student not found with ID: " + dto.getStudentId());
                });

        Exam exam = new Exam();
        exam.setStudent(student);
        exam.setSubject(dto.getSubject());
        exam.setScore(dto.getScore());
        exam.setTerm(dto.getTerm());
        exam.setYear(dto.getYear());

        System.out.println("✅ Exam entity created successfully!");
        return exam;
    }

    // Convert Entity to DTO
    private ExamDTO mapToDTO(Exam exam) {
        return new ExamDTO(
                exam.getStudent().getId(),
                exam.getSubject(),
                exam.getScore(),
                exam.getTerm(),
                exam.getYear()
        );
    }

    @Override
    @Transactional
    public ExamDTO createExam(ExamDTO examDTO) {
        try {
            System.out.println("Reached here");

            // Convert DTO to Entity
            Exam exam = mapToEntity(examDTO);

            // Save Exam
            Exam savedExam = examRepository.save(exam);
            System.out.println("Saved Exam ID: " + savedExam.getId()); // ✅ Debugging

            return mapToDTO(savedExam);
        } catch (Exception e) {
            System.err.println("Error saving exam: " + e.getMessage()); // ✅ Log the error
            throw new RuntimeException("Failed to save exam. Please try again."); // ✅ Throw a user-friendly exception
        }
    }

    @Override
    public ExamDTO getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + id));
        return mapToDTO(exam);
    }

    @Override
    public List<ExamDTO> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamDTO> getExamsByStudentId(Long studentId) {
        return examRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam not found with ID: " + id);
        }
        examRepository.deleteById(id);
    }

    @Override
    public List<ReportDTO> generateTermYearReport(String term, int year) {
        List<ReportDTO> reports = new ArrayList<>();

        try {
            List<Object[]> results = examRepository.getTermYearReport(term, year);

            for (Object[] row : results) {
                // Ensure correct column mapping based on the query structure
                Long studentId = row[0] != null ? ((Number) row[0]).longValue() : null; // Handle null case
                String studentName = row[1] != null ? (String) row[1] : "Unknown";
                Double totalScore = row[2] != null ? ((Number) row[2]).doubleValue() : 0.0;
                Double avgScore = row[3] != null ? ((Number) row[3]).doubleValue() : 0.0;
                int rank = row[4] != null ? ((Number) row[4]).intValue() : 0; // Safe casting

                String grade = getGrade(avgScore);
                String remarks = getRemarksFromGrade(grade);

                // Create a new ReportDTO object
                ReportDTO report = new ReportDTO(
                        studentId,  // Ensure it's correctly mapped
                        studentName,
                        term,
                        rank,
                        avgScore,
                        getGrade(avgScore),  // Ensure this method exists
                        getRemarksFromGrade(getGrade(avgScore))  // Use Double instead of String
                );

                reports.add(report);
            }
        } catch (DataAccessException e) {
            System.err.println("Database error while fetching term-year report: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while generating term-year report: " + e.getMessage());
        }

        return reports;
    }

    // Method to determine grade based on average score
    private String getGrade(double avgScore) {
        if (avgScore >= 80) return "A";
        if (avgScore >= 70) return "B";
        if (avgScore >= 60) return "C";
        if (avgScore >= 50) return "D";
        return "F";
    }

    // Method to determine remarks based on grade
    private String getRemarksFromGrade(String grade) {
        switch (grade) {
            case "A": return "Excellent";
            case "B": return "Very Good";
            case "C": return "Good";
            case "D": return "Fair";
            default: return "Needs Improvement";
        }
    }


}
