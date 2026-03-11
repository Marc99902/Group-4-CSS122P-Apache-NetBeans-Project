package com.mycompany.group4_css122p.factory;

import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

/**
 * EmployeeFactory.java - Factory Pattern Implementation
 * 
 * This class implements the Factory Design Pattern which provides
 * a centralized way to create Employee and Manager objects.
 * 
 * DESIGN PATTERN: FACTORY PATTERN
 * - Encapsulates object creation logic
 * - Provides a single point for creating different types of employees
 * - Makes it easy to add new employee types in the future
 * 
 * BENEFITS:
 * 1. Centralized object creation
 * 2. Easy to maintain and extend
 * 3. Hides complex creation logic
 * 4. Consistent object initialization
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class EmployeeFactory {
    
    // ============================================================
    // CONSTANTS - Employee Type Codes
    // ============================================================
    
    /** Type code for Regular Employee */
    public static final String TYPE_REGULAR = "REGULAR";
    
    /** Type code for Manager */
    public static final String TYPE_MANAGER = "MANAGER";
    
    // ============================================================
    // FACTORY METHODS
    // ============================================================
    
    /**
     * Creates a Regular Employee with all required fields
     * 
     * This is a convenience method that calls the main createEmployee method
     * with default values for manager-specific fields.
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
     * @return New Employee object
     */
    public static Employee createEmployee(
            String id, String firstName, String lastName, 
            String email, String phone, String department, 
            String position, double performanceRating, 
            double salary, String hireDate) {
        
        // Create and return a regular Employee object
        return new Employee(id, firstName, lastName, email, phone, 
                          department, position, performanceRating, 
                          salary, hireDate);
    }
    
    /**
     * Creates a Manager with all required fields including manager-specific data
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
     * @param teamSize Number of team members managed
     * @param managementLevel Management level (Junior, Mid, Senior, Executive)
     * @param bonusPercentage Bonus percentage
     * @return New Manager object
     */
    public static Manager createManager(
            String id, String firstName, String lastName, 
            String email, String phone, String department, 
            String position, double performanceRating, 
            double salary, String hireDate,
            int teamSize, String managementLevel, double bonusPercentage) {
        
        // Create and return a Manager object
        return new Manager(id, firstName, lastName, email, phone, 
                         department, position, performanceRating, 
                         salary, hireDate, teamSize, managementLevel, 
                         bonusPercentage);
    }
    
    /**
     * Main Factory Method - Creates employee based on type
     * 
     * This method uses method overloading to handle both Regular and Manager
     * employee creation through a single interface.
     * 
     * OVERLOAD 1: For Regular Employees (without manager params)
     * 
     * @param type Employee type (TYPE_REGULAR or TYPE_MANAGER)
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
     * @return New Employee object (either Employee or Manager)
     */
    public static Employee createEmployee(
            String type,
            String id, String firstName, String lastName, 
            String email, String phone, String department, 
            String position, double performanceRating, 
            double salary, String hireDate) {
        
        // Check type and create appropriate object
        if (TYPE_MANAGER.equalsIgnoreCase(type)) {
            // For manager type without manager params, use defaults
            return new Manager(id, firstName, lastName, email, phone, 
                             department, position, performanceRating, 
                             salary, hireDate, 0, "Junior", 0.0);
        } else {
            // Default to regular employee
            return new Employee(id, firstName, lastName, email, phone, 
                              department, position, performanceRating, 
                              salary, hireDate);
        }
    }
    
    /**
     * Main Factory Method - Creates employee based on type (with manager params)
     * 
     * OVERLOAD 2: For Managers (with manager-specific params)
     * 
     * @param type Employee type (TYPE_REGULAR or TYPE_MANAGER)
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
     * @param teamSize Number of team members (for managers)
     * @param managementLevel Management level (for managers)
     * @param bonusPercentage Bonus percentage (for managers)
     * @return New Employee object (either Employee or Manager)
     */
    public static Employee createEmployee(
            String type,
            String id, String firstName, String lastName, 
            String email, String phone, String department, 
            String position, double performanceRating, 
            double salary, String hireDate,
            int teamSize, String managementLevel, double bonusPercentage) {
        
        // Check type and create appropriate object
        if (TYPE_MANAGER.equalsIgnoreCase(type)) {
            // Create Manager with all parameters
            return new Manager(id, firstName, lastName, email, phone, 
                             department, position, performanceRating, 
                             salary, hireDate, teamSize, managementLevel, 
                             bonusPercentage);
        } else {
            // Create Regular Employee (ignore manager params)
            return new Employee(id, firstName, lastName, email, phone, 
                              department, position, performanceRating, 
                              salary, hireDate);
        }
    }
    
    // ============================================================
    // UTILITY METHODS
    // ============================================================
    
    /**
     * Validates if the given type is valid
     * @param type Type string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidType(String type) {
        return TYPE_REGULAR.equalsIgnoreCase(type) || 
               TYPE_MANAGER.equalsIgnoreCase(type);
    }
    
    /**
     * Returns array of valid employee types
     * @return Array of type strings
     */
    public static String[] getValidTypes() {
        return new String[] { TYPE_REGULAR, TYPE_MANAGER };
    }
    
    /**
     * Returns display name for employee type
     * @param type Type code
     * @return Human-readable type name
     */
    public static String getTypeDisplayName(String type) {
        if (TYPE_REGULAR.equalsIgnoreCase(type)) {
            return "Regular Employee";
        } else if (TYPE_MANAGER.equalsIgnoreCase(type)) {
            return "Manager";
        }
        return "Unknown";
    }
}
