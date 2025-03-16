package com.emtech.students_microservice.dto1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SubjectDTO {
    private UUID id;
    private String subjectName;
}

