package com.alexraza.usiu.repository;

import com.alexraza.usiu.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by their department ID
    List<Employee> findByDepartmentId(Long departmentId);

    // Find employees by their position
    List<Employee> findByPositionTitle(String title);
}
