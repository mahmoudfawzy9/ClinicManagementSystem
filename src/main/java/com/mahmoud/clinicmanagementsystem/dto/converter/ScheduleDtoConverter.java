package com.mahmoud.clinicmanagementsystem.dto.converter;

import com.mahmoud.clinicmanagementsystem.dto.ScheduleDto;
import com.mahmoud.clinicmanagementsystem.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleDtoConverter {
    public ScheduleDto convert(Schedule from) {
        return new ScheduleDto(
                from.getId(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<ScheduleDto> convert(List<Schedule> from) {
        return from.stream().map(this::convert).toList();
    }
}
