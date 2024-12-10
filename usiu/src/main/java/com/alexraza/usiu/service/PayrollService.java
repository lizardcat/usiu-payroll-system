package com.alexraza.usiu.service;

import com.alexraza.usiu.model.Employee;
import com.alexraza.usiu.model.Payslip;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    public Payslip calculatePayslip(Employee employee, String month) {
        double baseSalary = employee.getPosition().getBaseSalary();
        double allowances = calculateAllowances(employee);
        double bonuses = calculateBonuses(employee);
        double deductions = calculateDeductions(employee);
        double netSalary = baseSalary + allowances + bonuses - deductions;

        return new Payslip(employee, baseSalary, allowances, bonuses, deductions, netSalary);
    }

    private double calculateAllowances(Employee employee) {
        double baseSalary = employee.getPosition().getBaseSalary();
        double housingAllowance = baseSalary * 0.20;
        double transportAllowance = baseSalary * 0.10;
        return housingAllowance + transportAllowance;
    }

    private double calculateBonuses(Employee employee) {
        int performanceRating = employee.getPerformanceRating(); // Assuming this field exists
        double baseBonus = 5000;
        double bonusMultiplier = 0.1 * performanceRating;
        return baseBonus * bonusMultiplier;
    }

    private double calculateDeductions(Employee employee) {
        double baseSalary = employee.getPosition().getBaseSalary();
        return calculatePAYE(baseSalary) + calculateNSSF() + calculateNHIF(baseSalary);
    }

    private double calculatePAYE(double baseSalary) {
        if (baseSalary <= 24000) {
            return 0.1 * baseSalary;
        } else if (baseSalary <= 32333) {
            return (0.1 * 24000) + (0.25 * (baseSalary - 24000));
        } else {
            return (0.1 * 24000) + (0.25 * (32333 - 24000)) + (0.30 * (baseSalary - 32333));
        }
    }

    private double calculateNSSF() {
        return 200; // Fixed NSSF contribution
    }

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
