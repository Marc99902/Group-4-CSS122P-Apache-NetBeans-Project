package com.mycompany.group4_css122p.data;

import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

import java.io.*;
import java.util.*;

/**
 * EmployeeDataManager.java - Data Management Class
 * 
 * This class handles all data operations for employees including:
 * - Adding, updating, deleting employees
 * - Searching and filtering employees
 * - Saving and loading data from file (persistence)
 * - Statistics and reporting
 * 
 * DESIGN PATTERN: SINGLETON PATTERN (optional extension)
 * This class could be implemented as a Singleton to ensure only
 * one instance exists throughout the application.
 * 
 * DATA PERSISTENCE:
 * - Uses Java Serialization to save/load employee data
 * - Data is stored in a binary file (employees.dat)
 * - Automatically loads data on startup
 * - Automatically saves data on modification
 * 
 * COLLECTIONS USED:
 * - ArrayList<Employee>: Stores all employee objects
 * - HashSet<String>: Ensures unique departments and positions
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class EmployeeDataManager {
    
    // ============================================================
    // CONSTANTS
    // ============================================================
    
    /** Filename for data persistence */
    private static final String DATA_FILE = "employees.dat";
    
    /** Performance status codes */
    private static final String STATUS_EE = "EE";
    private static final String STATUS_ME = "ME";
    private static final String STATUS_PIP = "PIP";
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    /** 
     * Main storage for all employees
     * ArrayList provides fast iteration and dynamic sizing
     */
    private List<Employee> employees;
    
    /** 
     * Tracks if data has been modified since last save
     * Used to optimize save operations
     */
    private boolean dataModified;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Constructor - Initializes data manager and loads existing data
     */
    public EmployeeDataManager() {
        // Initialize the employees list
        employees = new ArrayList<>();
        
        // Try to load existing data from file
        loadData();
        
        // Mark as not modified (just loaded)
        dataModified = false;
    }
    
    // ============================================================
    // CRUD OPERATIONS - Create, Read, Update, Delete
    // ============================================================
    
    /**
     * Adds a new employee to the system
     * 
     * @param employee Employee object to add
     * @return true if added successfully, false if ID already exists
     */
    public boolean addEmployee(Employee employee) {
        // Check if employee with same ID already exists
        if (findEmployeeById(employee.getId()) != null) {
            return false;  // ID already exists
        }
        
        // Add to list
        employees.add(employee);
        
        // Mark data as modified and save
        dataModified = true;
        saveData();
        
        return true;
    }
    
    /**
     * Updates an existing employee
     * 
     * @param updatedEmployee Employee with updated information
     * @return true if updated successfully, false if not found
     */
    public boolean updateEmployee(Employee updatedEmployee) {
        // Find the employee to update
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(updatedEmployee.getId())) {
                // Replace with updated employee
                employees.set(i, updatedEmployee);
                
                // Mark as modified and save
                dataModified = true;
                saveData();
                
                return true;
            }
        }
        
        return false;  // Employee not found
    }
    
    /**
     * Removes an employee by ID
     * 
     * @param id Employee ID to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeEmployeeById(String id) {
        // Find and remove employee
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getId().equals(id)) {
                iterator.remove();
                
                // Mark as modified and save
                dataModified = true;
                saveData();
                
                return true;
            }
        }
        
        return false;  // Employee not found
    }
    
    /**
     * Finds an employee by ID
     * 
     * @param id Employee ID to search for
     * @return Employee object if found, null otherwise
     */
    public Employee findEmployeeById(String id) {
        // Loop through all employees to find matching ID
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return emp;  // Found!
            }
        }
        
        return null;  // Not found
    }
    
    /**
     * Gets all employees
     * 
     * @return List of all employees (new list to prevent external modification)
     */
    public List<Employee> getAllEmployees() {
        // Return a new ArrayList to protect internal list
        return new ArrayList<>(employees);
    }
    
    /**
     * Gets the total count of employees
     * 
     * @return Number of employees
     */
    public int getEmployeeCount() {
        return employees.size();
    }
    
    // ============================================================
    // SEARCH OPERATIONS
    // ============================================================
    
    /**
     * Searches employees by name (first or last name)
     * Case-insensitive partial match
     * 
     * @param name Name to search for
     * @return List of matching employees
     */
    public List<Employee> searchByName(String name) {
        // Convert search term to lowercase for case-insensitive search
        String searchLower = name.toLowerCase();
        
        List<Employee> results = new ArrayList<>();
        
        for (Employee emp : employees) {
            // Check if first name or last name contains search term
            if (emp.getFirstName().toLowerCase().contains(searchLower) ||
                emp.getLastName().toLowerCase().contains(searchLower) ||
                emp.getFullName().toLowerCase().contains(searchLower)) {
                results.add(emp);
            }
        }
        
        return results;
    }
    
    /**
     * Gets employees by department
     * 
     * @param department Department name
     * @return List of employees in that department
     */
    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> results = new ArrayList<>();
        
        for (Employee emp : employees) {
            if (emp.getDepartment().equalsIgnoreCase(department)) {
                results.add(emp);
            }
        }
        
        return results;
    }
    
    /**
     * Gets employees by performance status
     * 
     * @param status Status code (EE, ME, or PIP)
     * @return List of employees with that status
     */
    public List<Employee> getEmployeesByPerformanceStatus(String status) {
        List<Employee> results = new ArrayList<>();
        
        for (Employee emp : employees) {
            // Compare status codes (first 2 characters of status string)
            if (emp.getPerformanceStatusCode().equals(status)) {
                results.add(emp);
            }
        }
        
        return results;
    }
    
    /**
     * Gets all managers
     * 
     * @return List of Manager objects
     */
    public List<Manager> getAllManagers() {
        List<Manager> managers = new ArrayList<>();
        
        for (Employee emp : employees) {
            // Check if employee is a Manager instance
            if (emp instanceof Manager) {
                managers.add((Manager) emp);  // Cast to Manager
            }
        }
        
        return managers;
    }
    
    // ============================================================
    // FILTER & AGGREGATION METHODS
    // ============================================================
    
    /**
     * Gets all unique departments
     * Uses HashSet to ensure uniqueness
     * 
     * @return Sorted list of department names
     */
    public List<String> getDepartments() {
        // Use HashSet to get unique departments
        Set<String> deptSet = new HashSet<>();
        
        for (Employee emp : employees) {
            deptSet.add(emp.getDepartment());
        }
        
        // Convert to list and sort alphabetically
        List<String> departments = new ArrayList<>(deptSet);
        Collections.sort(departments);
        
        return departments;
    }
    
    /**
     * Gets all unique positions
     * 
     * @return Sorted list of position titles
     */
    public List<String> getPositions() {
        // Use HashSet to get unique positions
        Set<String> posSet = new HashSet<>();
        
        for (Employee emp : employees) {
            posSet.add(emp.getPosition());
        }
        
        // Convert to list and sort
        List<String> positions = new ArrayList<>(posSet);
        Collections.sort(positions);
        
        return positions;
    }
    
    /**
     * Calculates average performance rating
     * 
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
     * Calculates total monthly payroll
     * 
     * @return Total salary for all employees
     */
    public double getTotalMonthlyPayroll() {
        double total = 0;
        for (Employee emp : employees) {
            total += emp.getSalary();
        }
        return total;
    }
    
    /**
     * Gets highest paid employee
     * 
     * @return Employee with highest salary, or null if empty
     */
    public Employee getHighestPaidEmployee() {
        if (employees.isEmpty()) {
            return null;
        }
        
        Employee highest = employees.get(0);
        for (Employee emp : employees) {
            if (emp.getSalary() > highest.getSalary()) {
                highest = emp;
            }
        }
        
        return highest;
    }
    
    /**
     * Gets top performers (rating >= 90)
     * 
     * @return List of top performing employees
     */
    public List<Employee> getTopPerformers() {
        return getEmployeesByPerformanceStatus(STATUS_EE);
    }
    
    // ============================================================
    // DATA PERSISTENCE - Save & Load
    // ============================================================
    
    /**
     * Saves all employee data to file using serialization
     * This ensures data persists between application runs
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            
            // Write the entire employee list to file
            oos.writeObject(employees);
            
            // Mark as saved
            dataModified = false;
            
            System.out.println("Data saved successfully! (" + employees.size() + " employees)");
            
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Loads employee data from file
     * Called automatically during initialization
     */
    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(DATA_FILE);
        
        // Check if file exists
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with empty database.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            
            // Read the employee list from file
            Object obj = ois.readObject();
            
            if (obj instanceof List) {
                employees = (List<Employee>) obj;
                System.out.println("Data loaded successfully! (" + employees.size() + " employees)");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with empty database.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
            // Start with empty list if load fails
            employees = new ArrayList<>();
        }
    }
    
    /**
     * Clears all data (use with caution)
     * Also deletes the data file
     */
    public void clearAllData() {
        employees.clear();
        
        // Delete the data file
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        
        dataModified = true;
        System.out.println("All data cleared!");
    }
    
    // ============================================================
    // STATISTICS METHODS
    // ============================================================
    
    /**
     * Generates comprehensive statistics report
     * 
     * @return Map containing various statistics
     */
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic counts
        stats.put("totalEmployees", employees.size());
        stats.put("totalManagers", getAllManagers().size());
        stats.put("totalDepartments", getDepartments().size());
        stats.put("totalPositions", getPositions().size());
        
        // Performance distribution
        stats.put("eeCount", getEmployeesByPerformanceStatus(STATUS_EE).size());
        stats.put("meCount", getEmployeesByPerformanceStatus(STATUS_ME).size());
        stats.put("pipCount", getEmployeesByPerformanceStatus(STATUS_PIP).size());
        
        // Financial stats
        stats.put("averageRating", getAveragePerformanceRating());
        stats.put("totalMonthlyPayroll", getTotalMonthlyPayroll());
        stats.put("averageSalary", employees.isEmpty() ? 0 : getTotalMonthlyPayroll() / employees.size());
        
        // Highest paid
        Employee highestPaid = getHighestPaidEmployee();
        stats.put("highestPaid", highestPaid != null ? highestPaid.getFullName() : "N/A");
        stats.put("highestSalary", highestPaid != null ? highestPaid.getSalary() : 0);
        
        return stats;
    }
}
