package com.emtech.students_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {

    private Long studentId;
    private String subject;
    private int score;
    private String term;
    private int year;

}
