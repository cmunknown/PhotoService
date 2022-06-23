package com.example.photoservice.exception;

public class NotSupportedFormat extends RuntimeException {
    public NotSupportedFormat(String message) {
        super(message);
    }
}
