package com.mahmoud.clinicmanagementsystem.service;

import com.mahmoud.clinicmanagementsystem.dto.ClinicDto;
import com.mahmoud.clinicmanagementsystem.dto.converter.ClinicDtoConverter;
import com.mahmoud.clinicmanagementsystem.exception.ClinicAlreadyExistException;
import com.mahmoud.clinicmanagementsystem.exception.ClinicNotFoundException;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessLogMessage;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessMessage;
import com.mahmoud.clinicmanagementsystem.model.Clinic;
import com.mahmoud.clinicmanagementsystem.repository.ClinicRepository;
import com.mahmoud.clinicmanagementsystem.request.clinic.CreateClinicRequest;
import com.mahmoud.clinicmanagementsystem.request.clinic.UpdateClinicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final ClinicDtoConverter converter;

    public ClinicService(ClinicRepository clinicRepository, ClinicDtoConverter converter) {
        this.clinicRepository = clinicRepository;
        this.converter = converter;
    }

    public void createClinic(final CreateClinicRequest request) {
        Clinic clinic = new Clinic();

        checkIfClinicExists(request.getName());

        clinic.setName(request.getName());
        clinic.setAddress(request.getAddress());
        clinic.setDetail(request.getDetail());

        clinicRepository.save(clinic);
        log.info(BusinessLogMessage.Clinic.CLINIC_CREATED);
    }

    public void updateClinic(final String id, final UpdateClinicRequest request) {
        Clinic clinic = findClinicByClinicId(id);

        if (!clinic.getName().equals(request.getName())) {
            checkIfClinicExists(request.getName());
        }

        clinic.setName(request.getName());
        clinic.setAddress(request.getAddress());
        clinic.setDetail(request.getDetail());

        clinicRepository.save(clinic);
        log.info(BusinessLogMessage.Clinic.CLINIC_UPDATED + id);
    }

    public void deleteClinic(final String id) {
        clinicRepository.delete(findClinicByClinicId(id));
        log.info(BusinessLogMessage.Clinic.CLINIC_DELETED + id);
    }

    public ClinicDto findClinicById(final String id) {
        Clinic clinic = findClinicByClinicId(id);

        log.info(BusinessLogMessage.Clinic.CLINIC_FOUND + id);
        return converter.convert(clinic);
    }

    public List<ClinicDto> findAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();

        if (clinics.isEmpty()) {
            log.error(BusinessLogMessage.Clinic.CLINIC_LIST_EMPTY);
            throw new ClinicNotFoundException(BusinessMessage.Clinic.CLINIC_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Clinic.CLINIC_LIST_FOUND);
        return converter.convert(clinics);
    }

    protected Clinic findClinicByClinicId(final String id) {
        return clinicRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.Clinic.CLINIC_NOT_FOUND + id);
            throw new ClinicNotFoundException(BusinessMessage.Clinic.CLINIC_NOT_FOUND);
        });
    }

    private void checkIfClinicExists(final String name) {
        if (clinicRepository.existsByNameIgnoreCase(name)) {
            log.error(BusinessLogMessage.Clinic.CLINIC_ALREADY_EXISTS + name);
            throw new ClinicAlreadyExistException(BusinessMessage.Clinic.CLINIC_ALREADY_EXISTS);
        }
    }
}
