package com.mahmoud.clinicmanagementsystem.repository;

import com.mahmoud.clinicmanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByNameIgnoreCase(String name);
}