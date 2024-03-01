package com.example.exception;

public class CSVReadException extends Exception {
    // Constructor to initialize the exception with a message
    public CSVReadException(String message) {
        // Call the superclass constructor (Exception) with the provided message
        super(message);
    }
}
