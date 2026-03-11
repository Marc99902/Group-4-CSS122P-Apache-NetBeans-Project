package com.mycompany.group4_css122p.model;

import java.io.Serializable;

/**
 * Employee.java - Base Model Class for Employee
 * 
 * This class represents a regular employee in the system.
 * It contains all the basic employee information and implements
 * Serializable for data persistence (saving/loading to file).
 * 
 * OOP CONCEPTS USED:
 * - ENCAPSULATION: All fields are private, accessed via getters/setters
 * - INHERITANCE: Manager class extends this class
 * - POLYMORPHISM: getDetails() method is overridden in Manager
 * 
 * PERFORMANCE STATUS RULES:
 * - EE (Exceeds Expectation): Rating >= 90
 * - ME (Meets Expectation): Rating >= 75 and < 90
 * - PIP (Performance Improvement Plan): Rating < 75
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class Employee implements Serializable {
    
    // ============================================================
    // CONSTANTS - Performance Status Codes
    // ============================================================
    public static final String STATUS_EE = "EE";    // Exceeds Expectation
    public static final String STATUS_ME = "ME";    // Meets Expectation
    public static final String STATUS_PIP = "PIP";  // Performance Improvement Plan
    
    // ============================================================
    // INSTANCE VARIABLES - Employee Data (Private for Encapsulation)
    // ============================================================
    private String id;              // Unique employee ID (e.g., E001, M001)
    private String firstName;       // Employee's first name
    private String lastName;        // Employee's last name
    private String email;           // Email address
    private String phone;           // Phone number
    private String department;      // Department (IT, HR, Finance, etc.)
    private String position;        // Job position/title
    private double performanceRating; // Performance score (0-100)
    private double salary;          // Monthly salary
    private String hireDate;        // Date hired (YYYY-MM-DD format)
    
    // ============================================================
    // CONSTRUCTORS
    // ============================================================
    
    /**
     * Default Constructor - Creates empty Employee object
     * Used when creating new employees step by step
     */
    public Employee() {
        // Initialize with empty values
    }
    
    /**
     * Full Parameter Constructor - Creates Employee with all data
     * 
     * @param id Employee ID
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param phone Phone number
     * @param department Department name
     * @param position Job position
     * @param performanceRating Performance rating (0-100)
     * @param salary Monthly salary
     * @param hireDate Hire date (YYYY-MM-DD)
     */
    public Employee(String id, String firstName, String lastName, 
                    String email, String phone, String department, 
                    String position, double performanceRating, 
                    double salary, String hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.performanceRating = performanceRating;
        this.salary = salary;
        this.hireDate = hireDate;
    }
    
    // ============================================================
    // GETTER METHODS - Access private fields (Encapsulation)
    // ============================================================
    
    public String getId() { 
        return id; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    /**
     * Returns full name by combining first and last name
     * @return Full name (e.g., "Juan Dela Cruz")
     */
    public String getFullName() { 
        return firstName + " " + lastName; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public String getPhone() { 
        return phone; 
    }
    
    public String getDepartment() { 
        return department; 
    }
    
    public String getPosition() { 
        return position; 
    }
    
    public double getPerformanceRating() { 
        return performanceRating; 
    }
    
    public double getSalary() { 
        return salary; 
    }
    
    public String getHireDate() { 
        return hireDate; 
    }
    
    // ============================================================
    // SETTER METHODS - Modify private fields (Encapsulation)
    // ============================================================
    
    public void setId(String id) { 
        this.id = id; 
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    
    public void setDepartment(String department) { 
        this.department = department; 
    }
    
    public void setPosition(String position) { 
        this.position = position; 
    }
    
    public void setPerformanceRating(double performanceRating) { 
        this.performanceRating = performanceRating; 
    }
    
    public void setSalary(double salary) { 
        this.salary = salary; 
    }
    
    public void setHireDate(String hireDate) { 
        this.hireDate = hireDate; 
    }
    
    // ============================================================
    // BUSINESS LOGIC METHODS
    // ============================================================
    
    /**
     * Calculates and returns the performance status based on rating
     * 
     * STATUS CALCULATION:
     * - EE (Exceeds Expectation): Rating >= 90
     * - ME (Meets Expectation): Rating >= 75 and < 90
     * - PIP (Performance Improvement Plan): Rating < 75
     * 
     * @return Performance status code (EE, ME, or PIP)
     */
    public String getPerformanceStatus() {
        if (performanceRating >= 90) {
            return STATUS_EE + " (Exceeds Expectation)";
        } else if (performanceRating >= 75) {
            return STATUS_ME + " (Meets Expectation)";
        } else {
            return STATUS_PIP + " (Performance Improvement Plan)";
        }
    }
    
    /**
     * Returns just the status code (EE, ME, PIP) without description
     * Useful for filtering and searching
     * @return Status code only
     */
    public String getPerformanceStatusCode() {
        if (performanceRating >= 90) {
            return STATUS_EE;
        } else if (performanceRating >= 75) {
            return STATUS_ME;
        } else {
            return STATUS_PIP;
        }
    }
    
    /**
     * Calculates annual salary (12 months + 13th month pay)
     * @return Annual salary amount
     */
    public double getAnnualSalary() {
        return salary * 13; // 12 months + 13th month pay
    }
    
    /**
     * POLYMORPHISM: This method can be overridden by subclasses
     * Returns formatted details string for display
     * 
     * @return Formatted employee details
     */
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== EMPLOYEE DETAILS ===\n\n");
        details.append("ID: ").append(id).append("\n");
        details.append("Name: ").append(getFullName()).append("\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Phone: ").append(phone).append("\n");
        details.append("Department: ").append(department).append("\n");
        details.append("Position: ").append(position).append("\n");
        details.append("Hire Date: ").append(hireDate).append("\n\n");
        details.append("Performance Rating: ").append(performanceRating).append("\n");
        details.append("Performance Status: ").append(getPerformanceStatus()).append("\n\n");
        details.append("Monthly Salary: $").append(String.format("%.2f", salary)).append("\n");
        details.append("Annual Salary: $").append(String.format("%.2f", getAnnualSalary())).append("\n");
        details.append("\nEmployee Type: Regular Employee");
        
        return details.toString();
    }
    
    // ============================================================
    // OVERRIDE METHODS
    // ============================================================
    
    /**
     * Returns string representation of Employee
     * Useful for debugging and logging
     * @return String with basic employee info
     */
    @Override
    public String toString() {
        return "Employee [" + id + "] " + getFullName() + 
               " - " + department + " - " + position;
    }
    
    /**
     * Compares two Employee objects for equality
     * Based on employee ID (unique identifier)
     * @param obj Object to compare
     * @return true if same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Same object reference
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Employee employee = (Employee) obj;
        return id != null && id.equals(employee.id);  // Compare by ID
    }
    
    /**
     * Generates hash code based on employee ID
     * Required when overriding equals()
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
