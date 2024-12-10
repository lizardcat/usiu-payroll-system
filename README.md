# **USIU-Africa Payroll System**

## **Overview**
The USIU-Africa Payroll System is a web-based application designed to simplify and streamline payroll management for a hypothetical university in Kenya. The system automates payroll processes, including salary computations, statutory deductions, and payslip generation, while ensuring compliance with Kenyan regulations. It is built using Java for backend logic, Thymeleaf templates with Bootstrap for the user interface, and Oracle Apex with MySQL for database management.

This project aims to enhance efficiency, minimize manual errors, and provide a user-friendly experience for administrators, HR personnel, and finance staff.

---

## **Key Features**
- **Employee Management**: Manage employee profiles with details such as name, department, job role, salary structure, and tax information.
- **Payroll Calculation**: Automatically calculate salaries based on predefined structures, including allowances, bonuses, and statutory deductions like NHIF and NSSF contributions.
- **Tax Computation**: Accurately compute PAYE deductions based on Kenyan tax brackets.
- **Payslip Generation**: Generate digital payslips with detailed salary breakdowns, deductions, and net pay.
- **Role-Based Access Control**: Secure data with role-specific permissions for administrators, HR personnel, finance staff, and employees.

---

## **Technologies Used**
### **Backend**
- **Java**: Handles business logic, payroll calculations, and backend services.
- **Spring Boot**: Provides the framework for dependency injection and web application setup.

### **Frontend**
- **Thymeleaf Templates**: Enables dynamic content rendering and seamless integration with backend services.
- **Bootstrap**: Ensures a responsive and user-friendly interface.

### **Database**
- **MySQL**: Primary database for storing employee records, payroll data, and tax information.
- **Oracle Apex**: Used for additional administrative functionalities and reporting.

### **Build Tools**
- **Maven**: Manages dependencies and builds the project.

---

## **Setup Instructions**
### **Prerequisites**
1. **Java Development Kit (JDK 21 or higher)**: Ensure Java is installed and set up on your system.
2. **IDE (I used IntelliJ)**: I think this should work with any IDE, but who knows
3. **MySQL Server**: Set up the database to store payroll and employee information. I used MySQL Workbench.
4. **Apache Maven**: For managing dependencies and building the application.

### **Steps to Set Up**
1. **Clone the Repository**
   ```bash
   git clone https://github.com/lizardcat/usiu-payroll-system.git
   cd usiu-payroll-system
   ```
2. **Set Up the Database**
    - Create a MySQL database and configure the connection details in `application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/usiu_payroll
      spring.datasource.username=admin
      spring.datasource.password=admin
      ```
3. **Install Dependencies**
   ```bash
   mvn install
   ```
4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
5. **Access the Application**
   Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

---

## **Usage**
1. **Log In**:
    - Admin credentials: `admin / password` (You can change these in SecurityConfig file)
2. **Manage Employees**:
    - Add, edit, or delete employee profiles.
3. **Calculate Payroll**:
    - Navigate to the Payroll module, select an employee, and specify the month to compute payroll.
4. **Generate Payslips**:
    - View payslips with a breakdown of salary, deductions, and net pay.
5. **Manage Positions and Departments**:
    - Add, edit, or remove job roles and organizational departments.

---

## **System Architecture**
- The system follows an MVC architecture:
    - **Model**: Represents employee, department, and payroll data.
    - **View**: Dynamic Thymeleaf templates styled with Bootstrap.
    - **Controller**: Handles requests and connects the frontend with backend services.

---

## **Screenshots**

### **Login Page**
![Login page](images/login_page.png)

### **Dashboard**
![Dashboard](images/dashboard_page.png)

### **Manage Employees**
![Employee management](images/employee_management_page.png)

### **Manage Departments**
![Department management](images/department_management_page.png)

### **Manage Positions**
![Position management](images/position_management_page.png)

### **Payroll Calculation**
![Payroll calculation](images/payroll_calculations_page.png)

### **Payslip**
![Sample payslip](images/payslip_sample.png)

## **Future Enhancements**
- Implement additional reporting features for payroll analytics.
- Add support for exporting payroll data to Excel or PDF formats.
- Introduce two-factor authentication for added security.
- Expand tax computation to accommodate changes in Kenyan regulatory requirements.

---

## **Contributing**
I welcome contributions! Please fork the repository, make your changes, and submit a pull request.

---

## **License**
The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software.
