package com.alexraza.usiu.controller;

import com.alexraza.usiu.repository.DepartmentRepository;
import com.alexraza.usiu.service.DepartmentService;
import com.alexraza.usiu.service.EmployeeService;
import com.alexraza.usiu.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
class WebController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String index(Model model) {
        // Fetching total count of employees and departments
        long totalEmployees = employeeService.getTotalEmployees();
        long totalDepartments = departmentService.getTotalDepartments();
        double totalPayrollCost = employeeService.calculateTotalPayrollCost();

        // Adding these counts to the model to pass to Thymeleaf
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalDepartments", totalDepartments);
        model.addAttribute("totalPayrollCost", totalPayrollCost);
        model.addAttribute("title", "Payroll System Dashboard");

        return "index"; // Refers to index.html
    }

    /*
    @GetMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("title", "Manage Departments");
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments";
    }

  /*  @GetMapping("/positions")
    public String positions(Model model) {
        model.addAttribute("title", "Manage Positions");
        model.addAttribute("positions", positionRepository.findAll());
        return "positions";
    } */
}
