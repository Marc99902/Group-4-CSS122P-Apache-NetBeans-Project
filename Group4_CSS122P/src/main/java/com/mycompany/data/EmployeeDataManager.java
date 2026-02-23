package com.mycompany.group4_css122p.data;

import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDataManager handles file operations for employee data
 * Demonstrates file I/O operations for data persistence
 * @author Kage
 */
public class EmployeeDataManager {
    private static final String DATA_FILE = "employees.txt";
    private List<Employee> employees;
    
    public EmployeeDataManager() {
        this.employees = new ArrayList<>();
        loadEmployees();
    }
    
    // Add employee to list and save to file
    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }
    
    // Remove employee from list and save to file
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        saveEmployees();
    }
    
    // Remove employee by ID
    public boolean removeEmployeeById(String id) {
        Employee toRemove = findEmployeeById(id);
        if (toRemove != null) {
            employees.remove(toRemove);
            saveEmployees();
            return true;
        }
        return false;
    }
    
    // Update employee
    public void updateEmployee(Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(updatedEmployee.getId())) {
                employees.set(i, updatedEmployee);
                saveEmployees();
                return;
            }
        }
    }
    
    // Find employee by ID
    public Employee findEmployeeById(String id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }
    
    // Get all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
    
    // Filter by department
    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getDepartment().equalsIgnoreCase(department)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // Filter by performance status
    public List<Employee> getEmployeesByPerformanceStatus(String status) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPerformanceStatus().contains(status)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // Filter by position
    public List<Employee> getEmployeesByPosition(String position) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPosition().equalsIgnoreCase(position)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // Search by name
    public List<Employee> searchByName(String name) {
        List<Employee> result = new ArrayList<>();
        String searchLower = name.toLowerCase();
        for (Employee emp : employees) {
            if (emp.getFullName().toLowerCase().contains(searchLower)) {
                result.add(emp);
            }
        }
        return result;
    }
    
    // Get unique departments
    public List<String> getDepartments() {
        List<String> departments = new ArrayList<>();
        for (Employee emp : employees) {
            if (!departments.contains(emp.getDepartment())) {
                departments.add(emp.getDepartment());
            }
        }
        return departments;
    }
    
    // Get unique positions
    public List<String> getPositions() {
        List<String> positions = new ArrayList<>();
        for (Employee emp : employees) {
            if (!positions.contains(emp.getPosition())) {
                positions.add(emp.getPosition());
            }
        }
        return positions;
    }
    
    // Get employee count
    public int getEmployeeCount() {
        return employees.size();
    }
    
    // Save employees to file
    private void saveEmployees() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Employee emp : employees) {
                if (emp instanceof Manager) {
                    writer.println("MANAGER," + emp.toCSV());
                } else {
                    writer.println("EMPLOYEE," + emp.toCSV());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }
    
    // Load employees from file
    private void loadEmployees() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee emp = parseEmployeeFromLine(line);
                if (emp != null) {
                    employees.add(emp);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
    }
    
    // Parse employee from CSV line
    private Employee parseEmployeeFromLine(String line) {
        try {
            String[] parts = line.split(",", 2);
            String type = parts[0];
            String data = parts[1];
            
            if (type.equals("MANAGER")) {
                return Manager.fromCSV(data);
            } else {
                return Employee.fromCSV(data);
            }
        } catch (Exception e) {
            System.err.println("Error parsing employee: " + e.getMessage());
            return null;
        }
    }
    
    // Clear all data
    public void clearAll() {
        employees.clear();
        saveEmployees();
    }
}