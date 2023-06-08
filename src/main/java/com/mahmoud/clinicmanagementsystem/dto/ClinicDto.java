package com.mahmoud.clinicmanagementsystem.dto;

import java.util.List;

public record ClinicDto(
    String id,
    String name,
    String address,
    String detail,
    List<ClinicDepartmentDto> departments
) {
}
