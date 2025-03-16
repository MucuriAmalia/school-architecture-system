package com.emtech.configuration_service.service;

import com.emtech.configuration_service.dto.StreamDTO;
import com.emtech.configuration_service.model.ClassLevel;
import com.emtech.configuration_service.model.Stream;
import com.emtech.configuration_service.repository.StreamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StreamService {
    private final StreamRepository streamRepository;

    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    public List<StreamDTO> getAllStreams() {
        return streamRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<StreamDTO> getStreamById(UUID id) {
        return streamRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<StreamDTO> getStreamByStreamName(String streamName) {
        return streamRepository.findByStreamName(streamName).map(this::convertToDTO);
    }

    public StreamDTO createStream(StreamDTO streamDTO) {
        Stream stream = new Stream();

        stream.setStreamName(streamDTO.getStreamName());

        // Save and return DTO
        Stream savedStream = streamRepository.save(stream);
        return convertToDTO(savedStream);

//        Stream stream = convertToEntity(streamDTO);
//        stream.setStreamName(streamDTO.getStreamName());
//        return convertToDTO(streamRepository.save(stream));
    }

    public StreamDTO updateStream(UUID id, StreamDTO streamDTO) {
        Stream existingStream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));

        existingStream.setStreamName(streamDTO.getStreamName());

        Stream updatedStream = streamRepository.save(existingStream);

        return convertToDTO(updatedStream);
    }


    public void deleteStream(UUID id) {
        streamRepository.deleteById(id);
    }

    private StreamDTO convertToDTO(Stream stream) {
        return new StreamDTO(stream.getId(), stream.getStreamName());
    }

    private Stream convertToEntity(StreamDTO streamDTO) {
        Stream stream = new Stream();
        stream.setId(streamDTO.getId());
        stream.setStreamName(streamDTO.getStreamName());  // ✅ FIXED: No more object conversion
        return stream;
    }
}
