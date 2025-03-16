package com.emtech.students_microservice.implementation;

import com.emtech.students_microservice.client.ConfigurationServiceClient;
import com.emtech.students_microservice.dto.StudentDTO;
import com.emtech.students_microservice.dto1.ClassLevelDTO;
import com.emtech.students_microservice.dto1.StreamDTO;

import com.emtech.students_microservice.exception.ResourceNotFoundException;
import com.emtech.students_microservice.model.Student;
import com.emtech.students_microservice.repository.StudentRepository;
import com.emtech.students_microservice.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ConfigurationServiceClient configurationServiceClient;

    public StudentServiceImpl(StudentRepository studentRepository, ConfigurationServiceClient configurationServiceClient) {
        this.studentRepository = studentRepository;
        this.configurationServiceClient = configurationServiceClient;
    }

    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        System.out.println("reached here");
        // Ensure student does not already exist
        if (studentRepository.findByAdmissionNumber(studentDTO.getAdmissionNumber()).isPresent()) {
            throw new IllegalArgumentException("Student with this admission number already exists!");
        }
        System.out.println("Fetching Class Level for ID: " + studentDTO.getFormName());
        System.out.println("Fetching Stream for ID: " + studentDTO.getStreamName());

        // Validate Class Level
        ClassLevelDTO classLevel;
        try {
            classLevel = configurationServiceClient.getClassLevelByFormName(studentDTO.getFormName());
            if (classLevel == null) {
                throw new IllegalArgumentException("Class Level not found for: " + studentDTO.getFormName());
            }
        } catch (Exception e) {
            System.out.println("Error fetching Class Level: " + e.getMessage());
            throw new RuntimeException("Error fetching Class Level: " + e.getMessage());
        }

        System.out.println("Class Level Response: " + classLevel);


        // Validate Stream
        StreamDTO stream;
        try {
            System.out.println("here ");
            stream = configurationServiceClient.getStreamByStreamName(studentDTO.getStreamName());
            if (stream == null) {
                System.out.println("null stream");
                throw new IllegalArgumentException("Stream not found for ID: " + studentDTO.getStreamName());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Stream ID format: " + studentDTO.getStreamName(), e);
        } catch (Exception e) {
            System.out.println("Error fetching Stream: " + e.getMessage());
            throw new RuntimeException("Error fetching Stream: " + e.getMessage(), e);
        }

        System.out.println("Stream Response: " + stream);


        // Create Student Entity
        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());

        // ✅ Only generate a new admission number if it's not provided
        if (studentDTO.getAdmissionNumber() == null || studentDTO.getAdmissionNumber().isEmpty()) {
            student.setAdmissionNumber(generateAdmissionNumber());
        } else {
            student.setAdmissionNumber(studentDTO.getAdmissionNumber());
        }

        student.setParentDetails(studentDTO.getParentDetails());
        student.setFormName(classLevel.getFormName());
        student.setStreamName(stream.getStreamName());

        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEnrollmentDate(studentDTO.getEnrollmentDate());

        // Save Student
        Student savedStudent = studentRepository.save(student);

        // Return DTO
        return new StudentDTO(

                        savedStudent.getStudentName(),
                        savedStudent.getAdmissionNumber(),
                savedStudent.getFormName(),
                savedStudent.getStreamName(),
                savedStudent.getDateOfBirth(),
                savedStudent.getEnrollmentDate(),
                savedStudent.getParentDetails()
                );
    }
    private String generateAdmissionNumber() {
        return "ADM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
    }

    public StudentDTO mapToDTO(Student student) {
        return new StudentDTO(
                student.getStudentName(),
                student.getAdmissionNumber(),
                student.getFormName(),
                student.getStreamName(),
                student.getDateOfBirth(),
                student.getEnrollmentDate(),
                student.getParentDetails()
        );
    }

    @Override
    public Object getStudentByAdmissionNumber(String admissionNumber) {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return List.of();
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updatedStudentDTO) {
        return null;
    }

    // ✅ Add Debugging Method
    @Override
    public void debugStudentRepository(Long studentId) {
        System.out.println("🔎 Fetching student with ID: " + studentId);

        List<Student> allStudents = studentRepository.findAll();
        System.out.println("📌 Total students in DB: " + allStudents.size());

        for (Student s : allStudents) {
            System.out.println("👀 Found Student ID: " + s.getId() + " - Name: " + s.getStudentName());
        }

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            System.out.println("❌ Student with ID " + studentId + " not found in the repository!");
        } else {
            System.out.println("✅ Student found: " + student.getStudentName());
        }
    }


}

