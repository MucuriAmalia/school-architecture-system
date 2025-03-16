package com.emtech.configuration_service.repository;

import com.emtech.configuration_service.model.ClassLevel;
import com.emtech.configuration_service.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {
    Optional<Stream> findByStreamName(String streamName);

     Optional<Stream> findById(UUID id);

    void deleteById(UUID id);

    List<Stream> id(UUID id);
}
