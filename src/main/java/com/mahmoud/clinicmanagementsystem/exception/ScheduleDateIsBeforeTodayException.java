package com.mahmoud.clinicmanagementsystem.exception;

public class ScheduleDateIsBeforeTodayException extends RuntimeException {
    public ScheduleDateIsBeforeTodayException(String message) {
        super(message);
    }
}
