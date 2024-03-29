package com.mahmoud.clinicmanagementsystem.dto;

import java.time.LocalDateTime;

public record StatusStatusHistoryDto(
    String id,
    LocalDateTime time,
    String description,
    String appointmentId
) {
}
