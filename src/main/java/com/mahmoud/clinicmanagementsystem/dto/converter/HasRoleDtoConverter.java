package com.mahmoud.clinicmanagementsystem.dto.converter;

import com.mahmoud.clinicmanagementsystem.dto.HasRoleDto;
import com.mahmoud.clinicmanagementsystem.model.HasRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HasRoleDtoConverter {
    public HasRoleDto convert(HasRole from) {
        return new HasRoleDto(
                from.getId(),
                from.getTimeFrom(),
                from.getTimeTo(),
                from.getIsActive(),
                from.getRole() != null ? from.getRole().getId() : null,
                from.getEmployee() != null ? from.getEmployee().getId() : null
        );
    }

    public List<HasRoleDto> convert(List<HasRole> from) {
        return from.stream().map(this::convert).toList();
    }
}
