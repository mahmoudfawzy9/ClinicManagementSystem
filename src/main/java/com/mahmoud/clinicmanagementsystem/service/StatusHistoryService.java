package com.mahmoud.clinicmanagementsystem.service;

import com.mahmoud.clinicmanagementsystem.dto.StatusHistoryDto;
import com.mahmoud.clinicmanagementsystem.dto.converter.StatusHistoryDtoConverter;
import com.mahmoud.clinicmanagementsystem.exception.StatusHistoryNotFoundException;
import com.mahmoud.clinicmanagementsystem.helper.DateHelper;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessLogMessage;
import com.mahmoud.clinicmanagementsystem.helper.message.BusinessMessage;
import com.mahmoud.clinicmanagementsystem.model.StatusHistory;
import com.mahmoud.clinicmanagementsystem.repository.StatusHistoryRepository;
import com.mahmoud.clinicmanagementsystem.request.satushistory.CreateStatusHistoryRequest;
import com.mahmoud.clinicmanagementsystem.request.satushistory.UpdateStatusHistoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatusHistoryService {
    private final StatusHistoryRepository statusHistoryRepository;
    private final StatusService statusService;
    private final AppointmentService appointmentService;
    private final StatusHistoryDtoConverter converter;

    public StatusHistoryService(StatusHistoryRepository statusHistoryRepository,
                                StatusService statusService,
                                AppointmentService appointmentService,
                                StatusHistoryDtoConverter converter) {
        this.statusHistoryRepository = statusHistoryRepository;
        this.statusService = statusService;
        this.appointmentService = appointmentService;
        this.converter = converter;
    }

    public void createStatusHistory(final CreateStatusHistoryRequest request) {
        StatusHistory statusHistory = new StatusHistory();

        statusHistory.setTime(DateHelper.getCurrentDateTime());
        statusHistory.setDescription(request.getDescription());
        statusHistory.setStatus(statusService.findStatusByStatusId(request.getStatusId()));
        statusHistory.setAppointment(appointmentService.findAppointmentByAppointmentId(request.getAppointmentId()));

        statusHistoryRepository.save(statusHistory);
        log.info(BusinessLogMessage.StatusHistory.STATUS_HISTORY_CREATED);
    }

    public void updateStatusHistory(final String id, final UpdateStatusHistoryRequest request) {
        StatusHistory statusHistory = findStatusHistoryByStatusHistoryId(id);

        statusHistory.setDescription(request.getDescription());
        statusHistory.setStatus(statusService.findStatusByStatusId(request.getStatusId()));
        statusHistory.setAppointment(appointmentService.findAppointmentByAppointmentId(request.getAppointmentId()));

        statusHistoryRepository.save(statusHistory);
        log.info(BusinessLogMessage.StatusHistory.STATUS_HISTORY_UPDATED + id);
    }

    public void deleteStatusHistory(final String id) {
        statusHistoryRepository.delete(findStatusHistoryByStatusHistoryId(id));
        log.info(BusinessLogMessage.StatusHistory.STATUS_HISTORY_DELETED + id);
    }

    public StatusHistoryDto findStatusHistoryById(final String id) {
        StatusHistory statusHistory = findStatusHistoryByStatusHistoryId(id);

        log.info(BusinessLogMessage.StatusHistory.STATUS_HISTORY_FOUND + id);
        return converter.convert(statusHistory);
    }

    public List<StatusHistoryDto> findAllStatusHistory() {
        List<StatusHistory> statusHistoryList = statusHistoryRepository.findAll();

        if (statusHistoryList.isEmpty()) {
            log.error(BusinessLogMessage.StatusHistory.STATUS_HISTORY_LIST_EMPTY);
            throw new StatusHistoryNotFoundException(BusinessMessage.StatusHistory.STATUS_HISTORY_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.StatusHistory.STATUS_HISTORY_LIST_FOUND);
        return converter.convert(statusHistoryList);
    }

    private StatusHistory findStatusHistoryByStatusHistoryId(final String id) {
        return statusHistoryRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.StatusHistory.STATUS_HISTORY_NOT_FOUND + id);
            throw new StatusHistoryNotFoundException(BusinessMessage.StatusHistory.STATUS_HISTORY_NOT_FOUND);
        });
    }
}
