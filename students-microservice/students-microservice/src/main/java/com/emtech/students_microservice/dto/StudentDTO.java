package com.emtech.students_microservice.dto;

import com.emtech.students_microservice.model.ParentDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String studentName;
    private String admissionNumber;
    private String formName;
    private String streamName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

    private ParentDetails parentDetails;
}
