package com.mycompany.group4_css122p.model;

/**
 * Manager.java - Subclass of Employee for Managers
 * 
 * This class extends Employee and adds manager-specific attributes:
 * - teamSize: Number of people managed
 * - managementLevel: Level of management (Junior, Mid, Senior, Executive)
 * - bonusPercentage: Bonus percentage based on performance
 * 
 * OOP CONCEPTS USED:
 * - INHERITANCE: Extends Employee class (IS-A relationship)
 * - POLYMORPHISM: Overrides getDetails(), getRole(), and getAnnualSalary()
 * - ENCAPSULATION: Private fields with getters/setters
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class Manager extends Employee {
    
    // ============================================================
    // INSTANCE VARIABLES - Manager-Specific Data
    // ============================================================
    
    /** Number of employees in the manager's team */
    private int teamSize;
    
    /** Management level (Junior, Mid, Senior, Executive) */
    private String managementLevel;
    
    /** Bonus percentage applied to annual salary (e.g., 20.0 = 20%) */
    private double bonusPercentage;
    
    // ============================================================
    // CONSTRUCTORS
    // ============================================================
    
    /**
     * Default Constructor
     */
    public Manager() {
        super();  // Call parent default constructor
    }
    
    /**
     * Full Constructor with all Employee and Manager fields
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
     * @param hireDate Hire date
     * @param teamSize Number of team members
     * @param managementLevel Management level
     * @param bonusPercentage Bonus percentage
     */
    public Manager(String id, String firstName, String lastName, 
                   String email, String phone, String department, 
                   String position, double performanceRating, 
                   double salary, String hireDate,
                   int teamSize, String managementLevel, double bonusPercentage) {
        // Call parent constructor to set Employee fields
        super(id, firstName, lastName, email, phone, department, 
              position, performanceRating, salary, hireDate);
        
        // Set Manager-specific fields
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        this.bonusPercentage = bonusPercentage;
    }
    
    // ============================================================
    // GETTER METHODS
    // ============================================================
    
    public int getTeamSize() { 
        return teamSize; 
    }
    
    public String getManagementLevel() { 
        return managementLevel; 
    }
    
    public double getBonusPercentage() { 
        return bonusPercentage; 
    }
    
    // ============================================================
    // SETTER METHODS
    // ============================================================
    
    public void setTeamSize(int teamSize) { 
        this.teamSize = teamSize; 
    }
    
    public void setManagementLevel(String managementLevel) { 
        this.managementLevel = managementLevel; 
    }
    
    public void setBonusPercentage(double bonusPercentage) { 
        this.bonusPercentage = bonusPercentage; 
    }
    
    // ============================================================
    // BUSINESS LOGIC METHODS - POLYMORPHISM (Overrides)
    // ============================================================
    
    /**
     * POLYMORPHISM: Overrides getAnnualSalary() from Employee
     * Calculates annual salary including manager bonus
     * 
     * Formula: (Monthly Salary x 13) + (Base Annual x Bonus%)
     * 
     * @return Annual salary with bonus included
     */
    @Override
    public double getAnnualSalary() {
        double baseAnnual = getSalary() * 13;  // 12 months + 13th month
        double bonus = baseAnnual * (bonusPercentage / 100.0);
        return baseAnnual + bonus;
    }
    
    /**
     * Calculates just the bonus amount
     * @return Bonus amount
     */
    public double getBonusAmount() {
        double baseAnnual = getSalary() * 13;
        return baseAnnual * (bonusPercentage / 100.0);
    }
    
    /**
     * POLYMORPHISM: Overrides getRole() from Employee
     * Returns the management role description
     * 
     * @return Management role description
     */
    @Override
    public String getRole() {
        return managementLevel + " Manager of " + getDepartment();
    }
    
    /**
     * POLYMORPHISM: Overrides getDetails() from Employee
     * Returns formatted details including manager-specific info
     * 
     * @return Formatted manager details
     */
    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== MANAGER DETAILS ===\n\n");
        details.append("ID: ").append(getId()).append("\n");
        details.append("Name: ").append(getFullName()).append("\n");
        details.append("Email: ").append(getEmail()).append("\n");
        details.append("Phone: ").append(getPhone()).append("\n");
        details.append("Department: ").append(getDepartment()).append("\n");
        details.append("Position: ").append(getPosition()).append("\n");
        details.append("Hire Date: ").append(getHireDate()).append("\n\n");
        details.append("Performance Rating: ").append(getPerformanceRating()).append("\n");
        details.append("Performance Status: ").append(getPerformanceStatus()).append("\n\n");
        details.append("=== MANAGEMENT INFO ===\n");
        details.append("Management Level: ").append(managementLevel).append("\n");
        details.append("Team Size: ").append(teamSize).append(" employees\n\n");
        details.append("=== COMPENSATION ===\n");
        details.append("Monthly Salary: $").append(String.format("%.2f", getSalary())).append("\n");
        details.append("Bonus Percentage: ").append(bonusPercentage).append("%\n");
        details.append("Bonus Amount: $").append(String.format("%.2f", getBonusAmount())).append("\n");
        details.append("Annual Salary (with bonus): $").append(String.format("%.2f", getAnnualSalary())).append("\n");
        details.append("\nEmployee Type: Manager");
        
        return details.toString();
    }
    
    /**
     * Returns management responsibility description
     * @return String describing management role
     */
    public String getResponsibilityDescription() {
        return managementLevel + " Manager overseeing " + teamSize + " team members";
    }
    
    // ============================================================
    // OVERRIDE METHODS
    // ============================================================
    
    /**
     * Returns string representation of Manager
     * @return String with manager info
     */
    @Override
    public String toString() {
        return "Manager [" + getId() + "] " + getFullName() + 
               " - " + getDepartment() + " - " + managementLevel + " Manager";
    }
}
