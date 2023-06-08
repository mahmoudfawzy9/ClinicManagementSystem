package com.mahmoud.clinicmanagementsystem.dto;

import java.util.List;

public record StatusDto(
    String id,
    String name,
    List<StatusAppointmentDto> appointments,
    List<StatusStatusHistoryDto> statusHistories
) {
}
