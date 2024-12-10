package com.alexraza.usiu.model;

import com.alexraza.usiu.model.Employee;

// Payslip Class
public class Payslip {
    private Employee employee;
    private double baseSalary;
    private double allowances;
    private double bonuses;
    private double deductions;
    private double netSalary;

    // Constructor
    public Payslip(Employee employee, double baseSalary, double allowances, double bonuses, double deductions, double netSalary) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.allowances = allowances;
        this.bonuses = bonuses;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    // Getters
    public Employee getEmployee() {
        return employee;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getAllowances() {
        return allowances;
    }

    public double getBonuses() {
        return bonuses;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }
}
