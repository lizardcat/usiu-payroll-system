package com.alexraza.usiu.controller;

import com.alexraza.usiu.model.Department;
import com.alexraza.usiu.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Display the department management page
    @GetMapping
    public String getDepartmentsPage(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("newDepartment", new Department()); // To bind with the form
        return "departments"; // Refers to departments.html
    }

    // Add a new department
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("newDepartment") Department department) {
        departmentRepository.save(department);
        return "redirect:/departments"; // Redirect to the department list page after adding
    }

    // Delete a department by ID
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
        return "redirect:/departments";
    }
}
