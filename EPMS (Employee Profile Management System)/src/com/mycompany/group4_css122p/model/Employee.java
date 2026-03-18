package com.mycompany.group4_css122p.model;

/**
 * Employee.java - Base Model Class for Employee
 * 
 * This class represents a regular employee in the system.
 * It extends Person and adds employee-specific attributes.
 * 
 * OOP CONCEPTS USED:
 * - ENCAPSULATION: All fields are private, accessed via getters/setters
 * - INHERITANCE: Extends Person class, Manager class extends this class
 * - POLYMORPHISM: getDetails() and getRole() methods are overridden
 * 
 * PERFORMANCE STATUS RULES (as per documentation):
 * - EE (Exceeds Expectation): Rating >= 90
 * - ME (Meets Expectation): Rating >= 70 and < 90
 * - PIP (Performance Improvement Plan): Rating < 70
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class Employee extends Person {
    
    // ============================================================
    // CONSTANTS - Performance Status Codes
    // ============================================================
    public static final String STATUS_EE = "EE";    // Exceeds Expectation
    public static final String STATUS_ME = "ME";    // Meets Expectation
    public static final String STATUS_PIP = "PIP";  // Performance Improvement Plan
    
    // ============================================================
    // INSTANCE VARIABLES - Employee Data (Private for Encapsulation)
    // ============================================================
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
        super();  // Call parent default constructor
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
        // Call parent constructor to set Person fields
        super(id, firstName, lastName, email, phone);
        
        // Set Employee-specific fields
        this.department = department;
        this.position = position;
        this.performanceRating = performanceRating;
        this.salary = salary;
        this.hireDate = hireDate;
    }
    
    // ============================================================
    // GETTER METHODS - Access private fields (Encapsulation)
    // ============================================================
    
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
     * STATUS CALCULATION (as per documentation):
     * - EE (Exceeds Expectation): Rating >= 90
     * - ME (Meets Expectation): Rating >= 70 and < 90
     * - PIP (Performance Improvement Plan): Rating < 70
     * 
     * @return Performance status code with description (EE, ME, or PIP)
     */
    public String getPerformanceStatus() {
        if (performanceRating >= 90) {
            return STATUS_EE + " (Exceeds Expectation)";
        } else if (performanceRating >= 70) {
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
        } else if (performanceRating >= 70) {
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
    
    // ============================================================
    // ABSTRACT METHOD IMPLEMENTATIONS (from Person)
    // ============================================================
    
    /**
     * IMPLEMENTATION of abstract getRole() from Person
     * Returns the role of this employee
     * 
     * @return Role description
     */
    @Override
    public String getRole() {
        return position + " in " + department;
    }
    
    /**
     * POLYMORPHISM: Implementation of abstract getDetails() from Person
     * Returns formatted details string for display
     * Can be overridden by subclasses (Manager)
     * 
     * @return Formatted employee details
     */
    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== EMPLOYEE DETAILS ===\n\n");
        details.append("ID: ").append(getId()).append("\n");
        details.append("Name: ").append(getFullName()).append("\n");
        details.append("Email: ").append(getEmail()).append("\n");
        details.append("Phone: ").append(getPhone()).append("\n");
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
        return "Employee [" + getId() + "] " + getFullName() + 
               " - " + department + " - " + position;
    }
}
