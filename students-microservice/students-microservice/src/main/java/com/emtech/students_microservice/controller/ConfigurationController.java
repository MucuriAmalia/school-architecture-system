package com.emtech.students_microservice.controller;

import com.emtech.students_microservice.client.ConfigurationServiceClient;
import com.emtech.students_microservice.dto1.ClassLevelDTO;
import com.emtech.students_microservice.dto1.GradeSystemDTO;
import com.emtech.students_microservice.dto1.StreamDTO;
import com.emtech.students_microservice.dto1.SubjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationServiceClient configurationServiceClient;

    public ConfigurationController(ConfigurationServiceClient configurationServiceClient) {
        this.configurationServiceClient = configurationServiceClient;
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassLevelDTO> getClassLevel(@PathVariable UUID id) {
        return ResponseEntity.ok(configurationServiceClient.getClassLevel(String.valueOf(id)));
    }

    @GetMapping("/streams/{id}")
    public ResponseEntity<StreamDTO> getStream(@PathVariable UUID id) {
        return ResponseEntity.ok(configurationServiceClient.getStream(UUID.fromString(String.valueOf(id))));
    }

    @GetMapping("/grades")
    public ResponseEntity<List<GradeSystemDTO>> getGrades() {
        return ResponseEntity.ok(configurationServiceClient.getGrades());
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        return ResponseEntity.ok(configurationServiceClient.getSubjects());
    }
}
