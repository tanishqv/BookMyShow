package com.scaler.BookMyShow.exceptions;

public class ShowTimeConflictException extends RuntimeException {
    public ShowTimeConflictException(String message) {
        super(message);
    }
}
