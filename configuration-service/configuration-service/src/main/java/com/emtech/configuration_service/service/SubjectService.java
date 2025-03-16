package com.emtech.configuration_service.service;

import com.emtech.configuration_service.dto.SubjectDTO;
import com.emtech.configuration_service.exception.ResourceNotFoundException;
import com.emtech.configuration_service.model.Subject;
import com.emtech.configuration_service.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // ✅ Fetch all subjects
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get subject by ID
    public Optional<SubjectDTO> getSubjectById(UUID id) {
        return subjectRepository.findById(id)
                .map(this::convertToDTO);
    }

    // ✅ Create a new subject
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        if (subjectDTO.getSubjectName() == null || subjectDTO.getSubjectName().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be null or empty");
        }

        Subject.SubjectName subjectName;
        try {
            subjectName = Subject.SubjectName.valueOf(subjectDTO.getSubjectName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid subject name: " + subjectDTO.getSubjectName());
        }

        Subject subject = new Subject();
        subject.setSubjectName(subjectName);

        return convertToDTO(subjectRepository.save(subject));
    }

    // ✅ Update subject details
    public SubjectDTO updateSubject(UUID id, SubjectDTO updatedSubjectDTO) {
        if (updatedSubjectDTO.getSubjectName() == null || updatedSubjectDTO.getSubjectName().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be null or empty");
        }

        return subjectRepository.findById(id)
                .map(existingSubject -> { // Explicitly naming the existing subject
                    try {
                        // Convert the subject name to an enum
                        Subject.SubjectName subjectNameEnum = Subject.SubjectName.valueOf(updatedSubjectDTO.getSubjectName().toUpperCase());

                        // Set the new subject name
                        existingSubject.setSubjectName(subjectNameEnum);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid subject name: " + updatedSubjectDTO.getSubjectName());
                    }

                    // Save the updated subject
                    Subject savedSubject = subjectRepository.save(existingSubject);

                    // Convert to DTO and return
                    return convertToDTO(savedSubject);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));
    }



    // ✅ Delete subject
    public void deleteSubject(UUID id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with ID: " + id);
        }
        subjectRepository.deleteById(id);
    }

    // ✅ Convert Subject entity to SubjectDTO
    private SubjectDTO convertToDTO(Subject subject) {
        return new SubjectDTO(subject.getId(), subject.getSubjectName().name());
    }
}

