package com.emtech.configuration_service.controller;

import com.emtech.configuration_service.dto.ClassLevelDTO;
import com.emtech.configuration_service.service.ClassLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/class-levels")
public class ClassLevelController {
    private final ClassLevelService classLevelService;

    public ClassLevelController(ClassLevelService classLevelService) {
        this.classLevelService = classLevelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClassLevelDTO>> getAllClassLevels() {
        List<ClassLevelDTO> classLevels = classLevelService.getAllClassLevels();
        return ResponseEntity.ok(classLevels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassLevelDTO> getClassLevelById(@PathVariable("id") UUID id) {
        Optional<ClassLevelDTO> classLevel = classLevelService.getClassLevelById(id);
        return classLevel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-form/{formName}")
    public ResponseEntity<ClassLevelDTO> getClassLevelByFormName(@PathVariable String formName) {
        Optional<ClassLevelDTO> classLevel = classLevelService.getClassLevelByName(formName);
        return classLevel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ClassLevelDTO> createClassLevel(@RequestBody ClassLevelDTO classLevelDTO) {
        ClassLevelDTO savedClassLevel = classLevelService.createClassLevel(classLevelDTO);
        return ResponseEntity.ok(savedClassLevel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassLevelDTO> updateClassLevel(@PathVariable UUID id, @RequestBody ClassLevelDTO classLevelDTO) {
        ClassLevelDTO updatedClassLevel = classLevelService.updateClassLevel(id, classLevelDTO);
        return ResponseEntity.ok(updatedClassLevel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassLevel(@PathVariable UUID id) {
        classLevelService.deleteClassLevel(id);
        return ResponseEntity.noContent().build();
    }
}
