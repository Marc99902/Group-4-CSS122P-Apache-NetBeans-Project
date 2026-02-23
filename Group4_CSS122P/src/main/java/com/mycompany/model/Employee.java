package com.mycompany.group4_css122p.model;

/**
 * Employee class demonstrating INHERITANCE from Person
 * and ENCAPSULATION with private fields
 * @author Kage
 */
public class Employee extends Person {
    private String department;
    private String position;
    private double performanceRating;
    private String performanceStatus;
    private double salary;
    private String hireDate;
    
    public Employee(String id, String firstName, String lastName, String email, 
                    String phone, String department, String position, 
                    double performanceRating, double salary, String hireDate) {
        super(id, firstName, lastName, email, phone);
        this.department = department;
        this.position = position;
        this.performanceRating = performanceRating;
        this.salary = salary;
        this.hireDate = hireDate;
        calculatePerformanceStatus();
    }
    
    // POLYMORPHISM - Override abstract method from Person
    @Override
    public String getRole() {
        return "Regular Employee";
    }
    
    @Override
    public String getDetails() {
        return String.format("Department: %s, Position: %s, Rating: %.1f, Status: %s",
                department, position, performanceRating, performanceStatus);
    }
    
    // Method to calculate performance status based on rating
    public void calculatePerformanceStatus() {
        if (performanceRating >= 90 && performanceRating <= 100) {
            performanceStatus = "EE (Exceeds Expectation)";
        } else if (performanceRating >= 70 && performanceRating <= 89) {
            performanceStatus = "ME (Meets Expectation)";
        } else if (performanceRating < 70) {
            performanceStatus = "PIP (Performance Improvement Plan)";
        } else {
            performanceStatus = "Invalid Rating";
        }
    }
    
    // Getters and Setters - ENCAPSULATION
    public String getDepartment() { return department; }
    public void setDepartment(String department) { 
        this.department = department; 
    }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { 
        this.position = position; 
    }
    
    public double getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(double performanceRating) { 
        this.performanceRating = performanceRating;
        calculatePerformanceStatus();
    }
    
    public String getPerformanceStatus() { return performanceStatus; }
    
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }
    
    // Method to convert employee to CSV format for file storage
    public String toCSV() {
        return getId() + "," + getFirstName() + "," + getLastName() + "," +
               getEmail() + "," + getPhone() + "," + department + "," +
               position + "," + performanceRating + "," + salary + "," + hireDate;
    }
    
    // Static method to create Employee from CSV
    public static Employee fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Employee(
            parts[0], parts[1], parts[2], parts[3], parts[4],
            parts[5], parts[6], Double.parseDouble(parts[7]),
            Double.parseDouble(parts[8]), parts[9]
        );
    }
    
    @Override
    public String toString() {
        return super.toString() + ", " + getDetails();
    }
}