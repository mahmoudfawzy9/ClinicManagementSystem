package com.mahmoud.clinicmanagementsystem.service;

import com.mahmoud.clinicmanagementsystem.dto.AppointmentDto;
import com.mahmoud.clinicmanagementsystem.dto.converter.AppointmentDtoConverter;
import com.mahmoud.clinicmanagementsystem.exception.AppointmentNotFoundException;
import com.mahmoud.clinicmanagementsystem.helper.DateHelper;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessLogMessage;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessMessage;
import com.mahmoud.clinicmanagementsystem.model.Appointment;
import com.mahmoud.clinicmanagementsystem.repository.AppointmentRepository;
import com.mahmoud.clinicmanagementsystem.request.appointment.CreateAppointmentRequest;
import com.mahmoud.clinicmanagementsystem.request.appointment.UpdateAppointmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final StatusService statusService;
    private final PatientCaseService patientCaseService;
    private final InDepartmentService inDepartmentService;
    private final AppointmentDtoConverter converter;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              StatusService statusService,
                              PatientCaseService patientCaseService,
                              InDepartmentService inDepartmentService,
                              AppointmentDtoConverter converter) {
        this.appointmentRepository = appointmentRepository;
        this.statusService = statusService;
        this.patientCaseService = patientCaseService;
        this.inDepartmentService = inDepartmentService;
        this.converter = converter;
    }

    public void createAppointment(final CreateAppointmentRequest request) {
        Appointment appointment = new Appointment();

        appointment.setCreatedTime(DateHelper.getCurrentDateTime());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(DateHelper.getEndTime(request.getStartTime()));
        appointment.setStatus(statusService.findStatusByStatusId(request.getStatusId()));
        appointment.setPatientCase(patientCaseService.findPatientCaseByPatientCaseId(request.getPatientCaseId()));
        appointment.setInDepartment(inDepartmentService.findInDepartmentByInDepartmentId(request.getInDepartmentId()));

        appointmentRepository.save(appointment);
        log.info(BusinessLogMessage.Appointment.APPOINTMENT_CREATED);
    }

    public void updateAppointment(final String id, final UpdateAppointmentRequest request) {
        Appointment appointment = findAppointmentByAppointmentId(id);

        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(DateHelper.getEndTime(request.getStartTime()));
        appointment.setStatus(statusService.findStatusByStatusId(request.getStatusId()));
        appointment.setPatientCase(patientCaseService.findPatientCaseByPatientCaseId(request.getPatientCaseId()));
        appointment.setInDepartment(inDepartmentService.findInDepartmentByInDepartmentId(request.getInDepartmentId()));

        appointmentRepository.save(appointment);
        log.info(BusinessLogMessage.Appointment.APPOINTMENT_UPDATED + id);
    }

    public void deleteAppointment(final String id) {
        appointmentRepository.delete(findAppointmentByAppointmentId(id));
        log.info(BusinessLogMessage.Appointment.APPOINTMENT_DELETED + id);
    }

    public AppointmentDto findAppointmentById(final String id) {
        Appointment appointment = findAppointmentByAppointmentId(id);

        log.info(BusinessLogMessage.Appointment.APPOINTMENT_FOUND + id);
        return converter.convert(appointment);
    }

    public List<AppointmentDto> findAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.isEmpty()) {
            log.error(BusinessLogMessage.Appointment.APPOINTMENT_LIST_EMPTY);
            throw new AppointmentNotFoundException(BusinessMessage.Appointment.APPOINTMENT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Appointment.APPOINTMENT_LIST_FOUND);
        return converter.convert(appointments);
    }

    protected Appointment findAppointmentByAppointmentId(final String id) {
        return appointmentRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.Appointment.APPOINTMENT_NOT_FOUND + id);
            throw new AppointmentNotFoundException(BusinessMessage.Appointment.APPOINTMENT_NOT_FOUND);
        });
    }
}
