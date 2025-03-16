package com.emtech.students_microservice.dto1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassLevelDTO {
    private UUID id;
    private String formName;
}