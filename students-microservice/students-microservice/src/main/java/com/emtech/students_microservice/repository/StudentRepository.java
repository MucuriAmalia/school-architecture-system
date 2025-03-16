package com.emtech.students_microservice.repository;

import com.emtech.students_microservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long Id);
    Optional<Student> findByAdmissionNumber(String admissionNumber);
}
