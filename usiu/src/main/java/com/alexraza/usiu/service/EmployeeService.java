package com.alexraza.usiu.service;

import com.alexraza.usiu.model.Payslip;
import com.alexraza.usiu.model.Employee;
import com.alexraza.usiu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Decimal format for 2 decimal places
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Get an employee by ID
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    // Bonuses calculation
    private double calculateBonuses(Employee employee) {
        double performanceBonus = employee.getPerformanceRating() >= 4 ? 10000 : 5000; // High performance bonus
        double attendanceBonus = 2000; // Fixed attendance bonus
        return performanceBonus + attendanceBonus;
    }

    // Allowances calculation
    private double calculateAllowances(Employee employee) {
        double housingAllowance = 0.20 * employee.getPosition().getBaseSalary(); // 20% of base salary for housing
        double transportAllowance = 0.10 * employee.getPosition().getBaseSalary(); // 10% for transport
        double medicalAllowance = 0.05 * employee.getPosition().getBaseSalary(); // 5% for medical
        return housingAllowance + transportAllowance + medicalAllowance;
    }

    // Deductions calculation
    private double calculateDeductions(Employee employee) {
        double paye = calculatePAYE(employee.getPosition().getBaseSalary()); // PAYE based on the base salary
        double nhif = calculateNHIF(employee.getPosition().getBaseSalary()); // NHIF based on the base salary
        double nssf = 200; // Fixed NSSF contribution
        double pensionContribution = 0.05 * employee.getPosition().getBaseSalary(); // Pension contribution
        return paye + nhif + nssf + pensionContribution;
    }

    // Helper Method to Calculate PAYE
    private double calculatePAYE(double baseSalary) {
        double paye = 0.0;

        // Sample Kenyan PAYE brackets
        if (baseSalary <= 24000) {
            paye = 0.1 * baseSalary;
        } else if (baseSalary <= 32333) {
            paye = (0.1 * 24000) + (0.25 * (baseSalary - 24000));
        } else {
            paye = (0.1 * 24000) + (0.25 * (32333 - 24000)) + (0.30 * (baseSalary - 32333));
        }

        return paye;
    }

    // Helper Method to Calculate NHIF Contribution
    private double calculateNHIF(double baseSalary) {
        double nhifContribution = 0.0;

        // Sample NHIF contribution rates based on salary bands
        if (baseSalary <= 5999) {
            nhifContribution = 150;
        } else if (baseSalary <= 7999) {
            nhifContribution = 300;
        } else if (baseSalary <= 11999) {
            nhifContribution = 400;
        } else if (baseSalary <= 14999) {
            nhifContribution = 500;
        } else if (baseSalary <= 19999) {
            nhifContribution = 600;
        } else if (baseSalary <= 24999) {
            nhifContribution = 750;
        } else if (baseSalary <= 29999) {
            nhifContribution = 850;
        } else if (baseSalary <= 34999) {
            nhifContribution = 900;
        } else if (baseSalary <= 39999) {
            nhifContribution = 950;
        } else if (baseSalary <= 44999) {
            nhifContribution = 1000;
        } else if (baseSalary <= 49999) {
            nhifContribution = 1100;
        } else if (baseSalary <= 59999) {
            nhifContribution = 1200;
        } else if (baseSalary <= 69999) {
            nhifContribution = 1300;
        } else if (baseSalary <= 79999) {
            nhifContribution = 1400;
        } else if (baseSalary <= 89999) {
            nhifContribution = 1500;
        } else if (baseSalary <= 99999) {
            nhifContribution = 1600;
        } else {
            nhifContribution = 1700;
        }

        return nhifContribution;
    }

    // Get total number of employees
    public long getTotalEmployees() {
        return employeeRepository.count();
    }

    // Get total payroll cost
    public double calculateTotalPayrollCost() {
        return employeeRepository.findAll().stream()
                .mapToDouble(employee -> {
                    double baseSalary = employee.getPosition().getBaseSalary();
                    double allowances = calculateAllowances(employee);
                    double bonuses = calculateBonuses(employee);
                    double deductions = calculateDeductions(employee);
                    return Double.parseDouble(df.format(baseSalary + allowances + bonuses - deductions));
                })
                .sum();
    }

    // Generate Payslip for Employee
    public Payslip generatePayslip(Employee employee) {
        double baseSalary = employee.getPosition().getBaseSalary();
        double allowances = calculateAllowances(employee);
        double bonuses = calculateBonuses(employee);
        double deductions = calculateDeductions(employee);
        double netSalary = baseSalary + allowances + bonuses - deductions;

        return new Payslip(employee, baseSalary, allowances, bonuses, deductions, netSalary);
    }
}

