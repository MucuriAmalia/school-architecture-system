package com.emtech.students_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Long studentId;
    private String studentName;
    private String term;
    private int rank;
    private double averageScore;
    private String grade;
    private String remarks;


    // ✅ Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "ReportDTO{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", term='" + term + '\'' +
                ", rank='" + rank + '\'' +
                ", averageScore=" + averageScore +
                ", grade='" + grade + '\'' +
                '}';
    }
}
