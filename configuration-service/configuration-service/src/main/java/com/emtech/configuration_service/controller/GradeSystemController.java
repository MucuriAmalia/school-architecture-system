package com.emtech.configuration_service.controller;

import com.emtech.configuration_service.dto.GradeSystemDTO;
import com.emtech.configuration_service.service.GradeSystemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grade-systems")
public class GradeSystemController {
    private final GradeSystemService gradeSystemService;

    public GradeSystemController(GradeSystemService gradeSystemService) {
        this.gradeSystemService = gradeSystemService;
    }

    @GetMapping("/all")
    public List<GradeSystemDTO> getAllGradeSystems() {
        return gradeSystemService.getAllGradeSystems();
    }

    @GetMapping("/{id}")
    public Optional<GradeSystemDTO> getGradeSystemById(@PathVariable Long id) {
        return gradeSystemService.getGradeSystemById(id);
    }

    @PostMapping("/create")
    public GradeSystemDTO createGradeSystem(@RequestBody GradeSystemDTO gradeSystemDTO) {
        return gradeSystemService.createGradeSystem(gradeSystemDTO);
    }

    @PutMapping("/{id}")
    public GradeSystemDTO updateGradeSystem(@PathVariable Long id, @RequestBody GradeSystemDTO gradeSystemDTO) {
        return gradeSystemService.updateGradeSystem(id, gradeSystemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGradeSystem(@PathVariable Long id) {
        gradeSystemService.deleteGradeSystem(id);
    }
}
