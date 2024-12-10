package com.alexraza.usiu.controller;

import com.alexraza.usiu.model.Employee;
import com.alexraza.usiu.service.EmployeeService;
import com.alexraza.usiu.service.PositionService;
import com.alexraza.usiu.service.DepartmentService;
import com.alexraza.usiu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    // GET endpoint to display employees page
    @GetMapping
    public String getEmployeePage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("newEmployee", new Employee());
        return "employees"; // Refers to employees.html
    }

    @GetMapping("/api")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // POST endpoint to add a new employee
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee newEmployee) {
        employeeService.addEmployee(newEmployee);
        return "redirect:/employees";
    }

    // Other CRUD methods (e.g., delete employee)
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}