package com.emtech.configuration_service.repository;

import com.emtech.configuration_service.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
