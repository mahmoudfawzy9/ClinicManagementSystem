package com.mahmoud.clinicmanagementsystem.repository;

import com.mahmoud.clinicmanagementsystem.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}