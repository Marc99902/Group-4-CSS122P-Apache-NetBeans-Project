package com.mycompany.group4_css122p.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Department.java - Department Class with Aggregation
 * 
 * This class represents a department in the company.
 * It demonstrates the AGGREGATION relationship - a Department "has-a" 
 * collection of Employees. Employees can exist independently of the Department.
 * 
 * OOP CONCEPTS USED:
 * - AGGREGATION: Department has a List<Employee> (has-a relationship)
 * - ENCAPSULATION: All fields are private, accessed via getters/setters
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class Department implements Serializable {
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    private String departmentID;      // Unique department ID
    private String departmentName;    // Department name (e.g., IT, HR, Finance)
    private String location;          // Physical location/office
    private String departmentHead;    // ID of the department head (Manager)
    
    /**
     * AGGREGATION: List of employees in this department
     * This is a "has-a" relationship - Department has Employees
     * Employees can exist independently of the Department
     */
    private List<Employee> employees;
    
    // ============================================================
    // CONSTRUCTORS
    // ============================================================
    
    /**
     * Default Constructor
     */
    public Department() {
        this.employees = new ArrayList<>();
    }
    
    /**
     * Constructor with basic department info
     * 
     * @param departmentID Department ID
     * @param departmentName Department name
     * @param location Department location
     */
    public Department(String departmentID, String departmentName, String location) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.location = location;
        this.employees = new ArrayList<>();
    }
    
    /**
     * Full Constructor with department head
     * 
     * @param departmentID Department ID
     * @param departmentName Department name
     * @param location Department location
     * @param departmentHead ID of department head
     */
    public Department(String departmentID, String departmentName, 
                      String location, String departmentHead) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.location = location;
        this.departmentHead = departmentHead;
        this.employees = new ArrayList<>();
    }
    
    // ============================================================
    // GETTER METHODS
    // ============================================================
    
    public String getDepartmentID() {
        return departmentID;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getDepartmentHead() {
        return departmentHead;
    }
    
    /**
     * Gets the list of employees in this department
     * @return List of employees
     */
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);  // Return copy to protect encapsulation
    }
    
    // ============================================================
    // SETTER METHODS
    // ============================================================
    
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }
    
    // ============================================================
    // AGGREGATION METHODS - Employee Management
    // ============================================================
    
    /**
     * Adds an employee to this department
     * AGGREGATION: Employee exists independently, just added to collection
     * 
     * @param employee Employee to add
     * @return true if added successfully
     */
    public boolean addEmployee(Employee employee) {
        // Check if employee already exists in department
        for (Employee emp : employees) {
            if (emp.getId().equals(employee.getId())) {
                return false;  // Employee already in department
            }
        }
        employees.add(employee);
        return true;
    }
    
    /**
     * Removes an employee from this department
     * AGGREGATION: Employee object continues to exist after removal
     * 
     * @param employee Employee to remove
     * @return true if removed successfully
     */
    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }
    
    /**
     * Removes an employee by ID
     * @param employeeId Employee ID to remove
     * @return true if removed successfully
     */
    public boolean removeEmployeeById(String employeeId) {
        for (Employee emp : employees) {
            if (emp.getId().equals(employeeId)) {
                return employees.remove(emp);
            }
        }
        return false;
    }
    
    /**
     * Finds an employee by ID in this department
     * @param employeeId Employee ID to find
     * @return Employee if found, null otherwise
     */
    public Employee getEmployeeById(String employeeId) {
        for (Employee emp : employees) {
            if (emp.getId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }
    
    /**
     * Gets the count of employees in this department
     * @return Number of employees
     */
    public int getEmployeeCount() {
        return employees.size();
    }
    
    /**
     * Clears all employees from this department
     * AGGREGATION: Employees continue to exist, just removed from collection
     */
    public void clearEmployees() {
        employees.clear();
    }
    
    // ============================================================
    // STATISTICS METHODS
    // ============================================================
    
    /**
     * Calculates average performance rating of department employees
     * @return Average rating, or 0 if no employees
     */
    public double getAveragePerformanceRating() {
        if (employees.isEmpty()) {
            return 0.0;
        }
        
        double total = 0;
        for (Employee emp : employees) {
            total += emp.getPerformanceRating();
        }
        return total / employees.size();
    }
    
    /**
     * Calculates total monthly payroll for this department
     * @return Total monthly salary
     */
    public double getTotalMonthlyPayroll() {
        double total = 0;
        for (Employee emp : employees) {
            total += emp.getSalary();
        }
        return total;
    }
    
    /**
     * Gets employees by performance status
     * @param status Status code (EE, ME, PIP)
     * @return List of employees with that status
     */
    public List<Employee> getEmployeesByPerformanceStatus(String status) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPerformanceStatusCode().equals(status)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // ============================================================
    // OVERRIDE METHODS
    // ============================================================
    
    /**
     * Returns string representation of Department
     * @return Department info string
     */
    @Override
    public String toString() {
        return "Department [" + departmentID + "] " + departmentName + 
               " (" + employees.size() + " employees)";
    }
    
    /**
     * Returns detailed department information
     * @return Formatted department details
     */
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== DEPARTMENT DETAILS ===\n\n");
        details.append("Department ID: ").append(departmentID).append("\n");
        details.append("Department Name: ").append(departmentName).append("\n");
        details.append("Location: ").append(location).append("\n");
        details.append("Department Head: ").append(departmentHead != null ? departmentHead : "Not Assigned").append("\n\n");
        details.append("Total Employees: ").append(employees.size()).append("\n");
        details.append("Average Performance Rating: ").append(String.format("%.2f", getAveragePerformanceRating())).append("\n");
        details.append("Total Monthly Payroll: $").append(String.format("%.2f", getTotalMonthlyPayroll())).append("\n");
        
        return details.toString();
    }
}
