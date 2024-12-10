package com.alexraza.usiu.service;

import com.alexraza.usiu.model.Department;
import com.alexraza.usiu.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get a department by ID
    public Department getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);
    }

    // Add a new department
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Update an existing department
    public Department updateDepartment(Long id, Department departmentDetails) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setName(departmentDetails.getName());
            department.setDescription(departmentDetails.getDescription());
            return departmentRepository.save(department);
        }
        return null;
    }

    // Delete a department by ID
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    public long getTotalDepartments() {
        return departmentRepository.count();
    }

}
