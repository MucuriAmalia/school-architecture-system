package com.emtech.configuration_service.dto;

import com.emtech.configuration_service.model.Subject;
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
