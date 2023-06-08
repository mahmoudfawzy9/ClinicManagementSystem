package com.mahmoud.clinicmanagementsystem.exception;

public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
