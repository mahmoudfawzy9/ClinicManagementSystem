package com.mahmoud.clinicmanagementsystem.dto.converter;

import com.mahmoud.clinicmanagementsystem.dto.PatientDocumentDto;
import com.mahmoud.clinicmanagementsystem.model.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientDocumentDtoConverter {
    public PatientDocumentDto convert(Document from) {
        return new PatientDocumentDto(
                from.getId(),
                from.getDocumentInternalId(),
                from.getName(),
                from.getUrl(),
                from.getDetail(),
                from.getDocumentType() != null ? from.getDocumentType().getId() : null,
                from.getAppointment() != null ? from.getAppointment().getId() : null,
                from.getPatientCase() != null ? from.getPatientCase().getId() : null,
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<PatientDocumentDto> convert(List<Document> from) {
        return from.stream().map(this::convert).toList();
    }
}
