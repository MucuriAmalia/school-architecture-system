package com.emtech.configuration_service.repository;

import com.emtech.configuration_service.model.GradeSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeSystemRepository extends JpaRepository<GradeSystem, Long> {
}
