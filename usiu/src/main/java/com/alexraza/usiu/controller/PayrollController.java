package com.alexraza.usiu.controller;

import com.alexraza.usiu.model.Employee;
import com.alexraza.usiu.model.Payslip;
import com.alexraza.usiu.service.PayrollService;
import com.alexraza.usiu.service.EmployeeService;
import com.alexraza.usiu.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PayrollService payrollService;

    // Mapping to serve the Thymeleaf template for payroll calculations
    @GetMapping
    public String getPayrollPage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "payroll"; // Returns the payroll.html Thymeleaf template
    }

    // Method to calculate payroll for a given employee
    @PostMapping("/calculate")
    public String calculatePayroll(@RequestParam("employeeId") Long employeeId,
                                   @RequestParam("month") String month,
                                   Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            model.addAttribute("error", "Employee not found for ID: " + employeeId);
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "payroll";
        }

        Payslip payslip = payrollService.calculatePayslip(employee, month);

        model.addAttribute("payrollDetails", payslip);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "payroll";
    }


    // Method to generate and display a payslip
    @GetMapping("/generate-payslip/{employeeId}")
    public String generatePaySlip(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            model.addAttribute("error", "Employee not found");
            return "error";
        }

        // Calculate salary details
        double baseSalary = employee.getPosition().getBaseSalary();
        double allowances = calculateAllowances(employee);
        double bonuses = calculateBonuses(employee);
        double deductions = calculateDeductions(employee);
        double netSalary = baseSalary + allowances + bonuses - deductions;

        // Create Payslip instance
        Payslip payslip = new Payslip(employee, baseSalary, allowances, bonuses, deductions, netSalary);

        model.addAttribute("payslip", payslip);
        return "payslip"; // Redirect to a dedicated payslip view
    }

    // Helper methods to calculate allowances, bonuses, and deductions
    private double calculateAllowances(Employee employee) {
        double baseSalary = employee.getPosition().getBaseSalary();
        double housingAllowance = baseSalary * 0.20; // Housing allowance: 20% of base salary
        double transportAllowance = baseSalary * 0.10; // Transport allowance: 10% of base salary
        return housingAllowance + transportAllowance;
    }

    private double calculateBonuses(Employee employee) {
        int performanceRating = employee.getPerformanceRating(); // Assuming a field for performance rating
        double baseBonus = 5000; // Base bonus
        double bonusMultiplier = 0.1 * performanceRating; // Bonus increases with rating
        return baseBonus * bonusMultiplier;
    }

    private double calculateDeductions(Employee employee) {
        double baseSalary = employee.getPosition().getBaseSalary();
        double paye = calculatePAYE(baseSalary);
        double nssfContribution = 200; // Fixed NSSF contribution
        double nhifContribution = calculateNHIF(baseSalary);
        return paye + nssfContribution + nhifContribution;
    }

    // PAYE calculation based on Kenyan tax brackets
    private double calculatePAYE(double baseSalary) {
        double paye = 0.0;
        if (baseSalary <= 24000) {
            paye = 0.1 * baseSalary;
        } else if (baseSalary <= 32333) {
            paye = (0.1 * 24000) + (0.25 * (baseSalary - 24000));
        } else {
            paye = (0.1 * 24000) + (0.25 * (32333 - 24000)) + (0.30 * (baseSalary - 32333));
        }
        return paye;
    }

    // NHIF contribution calculation based on salary bands
    private double calculateNHIF(double baseSalary) {
        if (baseSalary <= 5999) return 150;
        if (baseSalary <= 7999) return 300;
        if (baseSalary <= 11999) return 400;
        if (baseSalary <= 14999) return 500;
        if (baseSalary <= 19999) return 600;
        if (baseSalary <= 24999) return 750;
        if (baseSalary <= 29999) return 850;
        if (baseSalary <= 34999) return 900;
        if (baseSalary <= 39999) return 950;
        if (baseSalary <= 44999) return 1000;
        if (baseSalary <= 49999) return 1100;
        if (baseSalary <= 59999) return 1200;
        if (baseSalary <= 69999) return 1300;
        if (baseSalary <= 79999) return 1400;
        if (baseSalary <= 89999) return 1500;
        if (baseSalary <= 99999) return 1600;
        return 1700;
    }
}
