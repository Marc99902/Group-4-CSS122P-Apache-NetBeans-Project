package com.mycompany.group4_css122p.data;

import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final Path DATA_FILE = resolveDataFilePath();
    
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
    private String lastErrorMessage;
    
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
        lastErrorMessage = null;
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
        lastErrorMessage = null;

        // Check if employee with same ID already exists
        if (findEmployeeById(employee.getId()) != null) {
            lastErrorMessage = "Employee ID already exists.";
            return false;  // ID already exists
        }
        
        // Add to list
        employees.add(employee);

        if (!saveData()) {
            employees.remove(employees.size() - 1);
            return false;
        }

        return true;
    }
    
    /**
     * Updates an existing employee
     * 
     * @param updatedEmployee Employee with updated information
     * @return true if updated successfully, false if not found
     */
    public boolean updateEmployee(Employee updatedEmployee) {
        lastErrorMessage = null;

        // Find the employee to update
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(updatedEmployee.getId())) {
                // Replace with updated employee
                Employee originalEmployee = employees.set(i, updatedEmployee);

                if (!saveData()) {
                    employees.set(i, originalEmployee);
                    return false;
                }

                return true;
            }
        }

        lastErrorMessage = "Employee not found.";
        return false;  // Employee not found
    }
    
    /**
     * Removes an employee by ID
     * 
     * @param id Employee ID to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeEmployeeById(String id) {
        lastErrorMessage = null;

        // Find and remove employee
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getId().equals(id)) {
                employees.remove(i);

                if (!saveData()) {
                    employees.add(i, emp);
                    return false;
                }

                return true;
            }
        }

        lastErrorMessage = "Employee not found.";
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
            if (emp instanceof Manager manager) {
                managers.add(manager);
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

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public Path getDataFilePath() {
        return DATA_FILE;
    }
    
    // ============================================================
    // DATA PERSISTENCE - Save & Load
    // ============================================================
    
    /**
     * Saves all employee data to file using serialization
     * This ensures data persists between application runs
     */
    public boolean saveData() {
        lastErrorMessage = null;

        try {
            Files.createDirectories(DATA_FILE.getParent());
        } catch (IOException e) {
            lastErrorMessage = "Unable to prepare the data folder: " + e.getMessage();
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(DATA_FILE))) {
            
            // Write the entire employee list to file
            oos.writeObject(employees);

            System.out.println("Data saved successfully! (" + employees.size() + " employees)");

            return true;
        } catch (IOException e) {
            lastErrorMessage = "Error saving data: " + e.getMessage();
            System.err.println(lastErrorMessage);
            return false;
        }
    }
    
    /**
     * Loads employee data from file
     * Called automatically during initialization
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = DATA_FILE.toFile();
        
        // Check if file exists
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with empty database.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(DATA_FILE))) {
            
            // Read the employee list from file
            Object obj = ois.readObject();
            
            if (obj instanceof List) {
                employees = (List<Employee>) obj;
                System.out.println("Data loaded successfully! (" + employees.size() + " employees)");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with empty database.");
        } catch (IOException | ClassNotFoundException e) {
            lastErrorMessage = "Error loading data: " + e.getMessage();
            System.err.println(lastErrorMessage);
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
        File file = DATA_FILE.toFile();
        if (file.exists()) {
            file.delete();
        }

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

    private static Path resolveDataFilePath() {
        try {
            Path codeSourcePath = Paths.get(EmployeeDataManager.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI());
            Path baseDirectory = Files.isDirectory(codeSourcePath)
                ? codeSourcePath
                : codeSourcePath.getParent();

            if (baseDirectory != null
                    && baseDirectory.getFileName() != null
                    && "bin".equalsIgnoreCase(baseDirectory.getFileName().toString())) {
                Path projectRoot = baseDirectory.getParent();
                if (projectRoot != null) {
                    return projectRoot.resolve("employees.dat");
                }
            }

            if (baseDirectory != null) {
                return baseDirectory.resolve("employees.dat");
            }
        } catch (URISyntaxException e) {
            System.err.println("Unable to resolve data file location: " + e.getMessage());
        }

        return Paths.get("employees.dat").toAbsolutePath();
    }
}
