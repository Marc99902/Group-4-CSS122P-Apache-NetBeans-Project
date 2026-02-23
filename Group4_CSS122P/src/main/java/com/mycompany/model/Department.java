package com.mycompany.group4_css122p.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department class demonstrating AGGREGATION
 * A Department contains multiple Employees (has-a relationship)
 * @author Kage
 */
public class Department {
    private String departmentId;
    private String departmentName;
    private String location;
    private String departmentHead;
    
    // AGGREGATION - Department has a list of Employees
    private List<Employee> employees;
    
    public Department(String departmentId, String departmentName, String location, String departmentHead) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
        this.departmentHead = departmentHead;
        this.employees = new ArrayList<>();
    }
    
    // Methods to manage employees in this department (AGGREGATION)
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    
    public int getEmployeeCount() {
        return employees.size();
    }
    
    // Calculate average performance rating of department
    public double getAveragePerformanceRating() {
        if (employees.isEmpty()) return 0.0;
        double sum = 0;
        for (Employee emp : employees) {
            sum += emp.getPerformanceRating();
        }
        return sum / employees.size();
    }
    
    // Get employees by performance status
    public List<Employee> getEmployeesByStatus(String status) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPerformanceStatus().contains(status)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // Getters and Setters
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDepartmentHead() { return departmentHead; }
    public void setDepartmentHead(String departmentHead) { this.departmentHead = departmentHead; }
    
    @Override
    public String toString() {
        return String.format("Department: %s (ID: %s), Location: %s, Head: %s, Employees: %d",
                departmentName, departmentId, location, departmentHead, employees.size());
    }
}