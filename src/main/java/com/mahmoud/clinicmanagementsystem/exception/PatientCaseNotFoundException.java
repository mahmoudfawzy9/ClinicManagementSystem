package com.mahmoud.clinicmanagementsystem.exception;

public class PatientCaseNotFoundException extends RuntimeException {
    public PatientCaseNotFoundException(String message) {
        super(message);
    }
}
