package com.emtech.students_microservice.client;

import com.emtech.students_microservice.dto1.ClassLevelDTO;
import com.emtech.students_microservice.dto1.GradeSystemDTO;
import com.emtech.students_microservice.dto1.StreamDTO;
import com.emtech.students_microservice.dto1.SubjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "configuration-service", url = "http://localhost:8093")
public interface ConfigurationServiceClient {

    @GetMapping("/api/class-levels/{id}")
    ClassLevelDTO getClassLevel(@PathVariable("id") String id);

    @GetMapping("/api/class-levels/by-form/{formName}")
    ClassLevelDTO getClassLevelByFormName(@PathVariable("formName") String formName);

    @GetMapping("/api/streams/{id}")
    StreamDTO getStream(@PathVariable("id") UUID id);

    @GetMapping("/api/streams/stream/{streamName}")
    StreamDTO getStreamByStreamName(@PathVariable("streamName") String streamName);

    @GetMapping("/api/grade/{id}")
    List<GradeSystemDTO> getGradesById(@PathVariable("id") String id);

    @GetMapping("/api/grade-systems")
    List<GradeSystemDTO> getGrades();

    @GetMapping("/api/subjects/{id}")
    List<SubjectDTO> getSubjectsById(@PathVariable("id") String id);

    @GetMapping("/api/subjects")
    List<SubjectDTO> getSubjects();
}
