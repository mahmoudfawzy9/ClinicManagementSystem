package com.mahmoud.clinicmanagementsystem.repository;

import com.mahmoud.clinicmanagementsystem.model.HasRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HasRoleRepository extends JpaRepository<HasRole, String> {
}