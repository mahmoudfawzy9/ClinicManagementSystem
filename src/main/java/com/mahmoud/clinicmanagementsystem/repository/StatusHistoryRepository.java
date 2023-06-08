package com.mahmoud.clinicmanagementsystem.repository;

import com.mahmoud.clinicmanagementsystem.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, String> {
}