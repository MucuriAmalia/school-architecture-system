package com.emtech.configuration_service.dto;

import com.emtech.configuration_service.model.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class StreamDTO {
    private UUID id;
    private String streamName;
}
