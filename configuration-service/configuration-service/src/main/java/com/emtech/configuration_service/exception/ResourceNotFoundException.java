package com.emtech.configuration_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    // ✅ Add this constructor to accept a message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
