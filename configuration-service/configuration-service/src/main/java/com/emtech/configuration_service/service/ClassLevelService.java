package com.emtech.configuration_service.service;

import com.emtech.configuration_service.dto.ClassLevelDTO;
import com.emtech.configuration_service.model.ClassLevel;
import com.emtech.configuration_service.repository.ClassLevelRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassLevelService {
    private final ClassLevelRepository classLevelRepository;

    public ClassLevelService(ClassLevelRepository classLevelRepository) {
        this.classLevelRepository = classLevelRepository;
    }

    // ✅ Get all class levels
    public List<ClassLevelDTO> getAllClassLevels() {
        List<ClassLevel> classLevels = classLevelRepository.findAll();
        return classLevels.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get class level by ID
    public Optional<ClassLevelDTO> getClassLevelById(UUID id) {
        return classLevelRepository.findById(id)
                .map(this::convertToDTO); // No need to cast, Java infers types correctly
    }



    // ✅ Get class level by name
    public Optional<ClassLevelDTO> getClassLevelByName(String formName) {
        return classLevelRepository.findByFormName(formName)
                .map(this::convertToDTO);
    }

    // ✅ Create a new class level
    public ClassLevelDTO createClassLevel(ClassLevelDTO classLevelDTO) {
        // Convert DTO to entity
        ClassLevel classLevel = new ClassLevel();

        classLevel.setFormName(classLevelDTO.getFormName());

        // Save and return DTO
        ClassLevel savedClassLevel = classLevelRepository.save(classLevel);
        return convertToDTO(savedClassLevel);
    }

    // ✅ Update an existing class level
    public ClassLevelDTO updateClassLevel(UUID id, ClassLevelDTO updatedClassLevelDTO) {
        return classLevelRepository.findById(id)
                .map(classLevel -> {
                    classLevel.setFormName(updatedClassLevelDTO.getFormName());
                    return convertToDTO(classLevelRepository.save(classLevel));
                })
                .orElseThrow(() -> new RuntimeException("Class Level with ID " + id + " not found"));
    }

    // ✅ Delete a class level
    public void deleteClassLevel(UUID id) {
        if (!classLevelRepository.existsById(id)) {
            throw new RuntimeException("Class Level with ID " + id + " does not exist");
        }
        classLevelRepository.deleteById(id);
    }

    // ✅ Convert Entity to DTO
    private ClassLevelDTO convertToDTO(ClassLevel classLevel) {
        ClassLevelDTO dto = new ClassLevelDTO();
        dto.setId(classLevel.getId());
        dto.setFormName(classLevel.getFormName()); // ✅ Use classId instead of formName
        return dto;
    }
}
