package com.mahmoud.clinicmanagementsystem.repository;

import com.mahmoud.clinicmanagementsystem.model.PatientCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCaseRepository extends JpaRepository<PatientCase, String> {
}