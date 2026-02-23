package com.mycompany.group4_css122p.model;

/**
 * Manager class demonstrating INHERITANCE from Employee
 * POLYMORPHISM through method overriding
 * @author Kage
 */
public class Manager extends Employee {
    private int teamSize;
    private String managementLevel;
    private double bonusPercentage;
    
    public Manager(String id, String firstName, String lastName, String email,
                   String phone, String department, String position,
                   double performanceRating, double salary, String hireDate,
                   int teamSize, String managementLevel, double bonusPercentage) {
        super(id, firstName, lastName, email, phone, department, position,
              performanceRating, salary, hireDate);
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        this.bonusPercentage = bonusPercentage;
    }
    
    // POLYMORPHISM - Override getRole from Employee
    @Override
    public String getRole() {
        return "Manager (" + managementLevel + ")";
    }
    
    @Override
    public String getDetails() {
        return super.getDetails() + 
               String.format(", Team Size: %d, Bonus: %.1f%%", teamSize, bonusPercentage);
    }
    
    // Manager-specific method
    public double calculateTotalCompensation() {
        return getSalary() + (getSalary() * bonusPercentage / 100);
    }
    
    // Getters and Setters
    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }
    
    public String getManagementLevel() { return managementLevel; }
    public void setManagementLevel(String managementLevel) { this.managementLevel = managementLevel; }
    
    public double getBonusPercentage() { return bonusPercentage; }
    public void setBonusPercentage(double bonusPercentage) { this.bonusPercentage = bonusPercentage; }
    
    @Override
    public String toCSV() {
        return super.toCSV() + "," + teamSize + "," + managementLevel + "," + bonusPercentage;
    }
    
    public static Manager fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Manager(
            parts[0], parts[1], parts[2], parts[3], parts[4],
            parts[5], parts[6], Double.parseDouble(parts[7]),
            Double.parseDouble(parts[8]), parts[9],
            Integer.parseInt(parts[10]), parts[11], Double.parseDouble(parts[12])
        );
    }
}