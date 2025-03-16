package com.emtech.configuration_service.dto;

import com.emtech.configuration_service.model.ClassLevel;
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
