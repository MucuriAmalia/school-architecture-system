package com.emtech.students_microservice.service;

import com.emtech.students_microservice.dto.StudentDTO;


import java.util.List;

public interface StudentService {
    StudentDTO registerStudent(StudentDTO studentDTO);
    void deleteStudent(Long id);

    StudentDTO getStudentById(Long id);

    Object getStudentByAdmissionNumber(String admissionNumber);

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(Long id, StudentDTO updatedStudentDTO);

    void debugStudentRepository(Long studentId);
}
