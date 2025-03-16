package com.emtech.configuration_service.repository;

import com.emtech.configuration_service.model.ClassLevel;
import com.emtech.configuration_service.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassLevelRepository extends JpaRepository<ClassLevel, UUID> {
    Optional<ClassLevel> findByFormName(String formName);

//     Optional findById(UUID id);

    boolean existsById(UUID id);
}
