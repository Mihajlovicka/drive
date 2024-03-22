package com.example.driveBack.exception;

public class ForeignApiRequestException extends RuntimeException {
    public ForeignApiRequestException(String message) {
        super(message);
    }
}

