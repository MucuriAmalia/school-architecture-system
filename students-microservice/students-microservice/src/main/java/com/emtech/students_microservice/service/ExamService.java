package com.emtech.students_microservice.service;

import com.emtech.students_microservice.dto.ExamDTO;
import com.emtech.students_microservice.dto.ReportDTO;
import com.emtech.students_microservice.model.Exam;

import java.util.List;

public interface ExamService {
    ExamDTO createExam(ExamDTO examDTO);
    List<ExamDTO> getAllExams();
    List<ExamDTO> getExamsByStudentId(Long studentId);

    ExamDTO getExamById(Long id);

    void deleteExam(Long id);


    List<ReportDTO> generateTermYearReport(String term, int year);

//    ExamDTO recordExamResult(Exam exam);
}
