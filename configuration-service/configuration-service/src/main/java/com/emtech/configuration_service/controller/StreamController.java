package com.emtech.configuration_service.controller;

import com.emtech.configuration_service.dto.StreamDTO;
import com.emtech.configuration_service.service.StreamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/streams")
public class StreamController {
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/create")
    public StreamDTO createStream(@RequestBody StreamDTO streamDTO) {
        return streamService.createStream(streamDTO);
    }

    @GetMapping("/all")
    public List<StreamDTO> getAllStreams() {
        return streamService.getAllStreams();
    }

    @GetMapping("/{id}")
    public Optional<StreamDTO> getStreamById(@PathVariable UUID id) {
        return streamService.getStreamById(id);
    }


    @GetMapping("/stream/{streamName}")
    public Optional<StreamDTO> getStreamByStreamName(@PathVariable String streamName) {
        return streamService.getStreamByStreamName(streamName);
    }



    @PutMapping("/{id}")
    public StreamDTO updateStream(@PathVariable UUID id, @RequestBody StreamDTO streamDTO) {
        return streamService.updateStream(id, streamDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStream(@PathVariable UUID id) {
        streamService.deleteStream(id);
    }
}

