package com.emtech.configuration_service.service;

import com.emtech.configuration_service.dto.GradeSystemDTO;
import com.emtech.configuration_service.model.GradeSystem;
import com.emtech.configuration_service.repository.GradeSystemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradeSystemService {
    private final GradeSystemRepository gradeSystemRepository;

    public GradeSystemService(GradeSystemRepository gradeSystemRepository) {
        this.gradeSystemRepository = gradeSystemRepository;
    }

    public List<GradeSystemDTO> getAllGradeSystems() {
        return gradeSystemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GradeSystemDTO> getGradeSystemById(Long id) {
        return gradeSystemRepository.findById(id)
                .map(this::convertToDTO);
    }

    public GradeSystemDTO createGradeSystem(GradeSystemDTO gradeSystemDTO) {
        GradeSystem gradeSystem = convertToEntity(gradeSystemDTO);
        GradeSystem savedGradeSystem = gradeSystemRepository.save(gradeSystem);
        return convertToDTO(savedGradeSystem);
    }

    public GradeSystemDTO updateGradeSystem(Long id, GradeSystemDTO updatedGradeSystemDTO) {
        return gradeSystemRepository.findById(id)
                .map(gradeSystem -> {
                    gradeSystem.setGradingSystem(GradeSystem.GradingSystem.valueOf(updatedGradeSystemDTO.getGrade()));
                    gradeSystem.setMinScore(updatedGradeSystemDTO.getMinScore());
                    gradeSystem.setMaxScore(updatedGradeSystemDTO.getMaxScore());
                    gradeSystem.setPoints(updatedGradeSystemDTO.getPoints());
                    return convertToDTO(gradeSystemRepository.save(gradeSystem));
                }).orElseThrow(() -> new RuntimeException("Grade System not found"));
    }

    public void deleteGradeSystem(Long id) {
        gradeSystemRepository.deleteById(id);
    }

    private GradeSystemDTO convertToDTO(GradeSystem gradeSystem) {
        return new GradeSystemDTO(
                gradeSystem.getId(),
                String.valueOf(gradeSystem.getGradingSystem()),
                gradeSystem.getMinScore(),
                gradeSystem.getMaxScore(),
                gradeSystem.getPoints()
        );
    }

    private GradeSystem convertToEntity(GradeSystemDTO gradeSystemDTO) {
        return new GradeSystem(
                gradeSystemDTO.getId(),
                gradeSystemDTO.getGrade(),
                gradeSystemDTO.getMinScore(),
                gradeSystemDTO.getMaxScore(),
                gradeSystemDTO.getPoints()
        );
    }
}

