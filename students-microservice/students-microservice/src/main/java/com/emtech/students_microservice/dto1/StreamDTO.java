package com.emtech.students_microservice.dto1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamDTO {
    private UUID id;
    private String StreamName; // Changed to String to decouple from the Stream entity
}
