package com.emtech.students_microservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Many exams belong to one student

    private String subject;
    private int score;
    private String term;
    private int year;

//    @ManyToOne
//    @JoinColumn(name = "report_id", nullable = false)
//    private Report report;

}
