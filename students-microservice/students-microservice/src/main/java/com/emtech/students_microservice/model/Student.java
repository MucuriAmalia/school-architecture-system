package com.emtech.students_microservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "studentName")
    private String studentName;

    @Column(name = "admission_number", unique = true, nullable = false)
    private String admissionNumber; // Auto-generated

    private LocalDate dateOfBirth;

    @Embedded
    private ParentDetails parentDetails; // Embedded for structured data

    private LocalDate enrollmentDate;

    @Column
    private String FormName; // Example: "Grade 8"

    private String StreamName; // Example: "Stream A"


//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Status status;

    @PrePersist
    private void generateAdmissionNumber() {
        this.admissionNumber = "ADM-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // One student can have multiple exams
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;

    // One student can have multiple reports
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Report> reports;
}
