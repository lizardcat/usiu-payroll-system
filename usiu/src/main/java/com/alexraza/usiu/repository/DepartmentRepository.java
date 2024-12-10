package com.alexraza.usiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexraza.usiu.model.Department; // Assuming entities are in a package named entity

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
