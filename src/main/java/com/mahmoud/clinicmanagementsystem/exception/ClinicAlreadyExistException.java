package com.mahmoud.clinicmanagementsystem.exception;

public class ClinicAlreadyExistException extends RuntimeException {
    public ClinicAlreadyExistException(String message) {
        super(message);
    }
}
