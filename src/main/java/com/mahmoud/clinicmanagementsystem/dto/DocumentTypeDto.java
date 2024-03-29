package com.mahmoud.clinicmanagementsystem.dto;

import java.util.List;

public record DocumentTypeDto(
    String id,
    String name,
    List<DocumentTypeDocumentDto> documents
) {
}
