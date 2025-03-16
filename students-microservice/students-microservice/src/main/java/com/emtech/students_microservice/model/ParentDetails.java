package com.emtech.students_microservice.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ParentDetails {
    private String fatherName;
    private String motherName;
    private String guardianName; // If applicable
    private String parentContact; // Renamed for consistency with DTO
    private String address;
}
