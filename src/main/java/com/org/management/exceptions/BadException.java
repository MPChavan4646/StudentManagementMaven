package com.org.management.exceptions;

public class BadException extends RuntimeException {
    public BadException(String message) {
        super(message);
    }
}