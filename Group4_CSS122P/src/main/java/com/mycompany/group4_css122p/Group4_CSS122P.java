package com.mycompany.group4_css122p;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.factory.EmployeeFactory;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;
import java.util.List;
import java.util.Scanner;

/**
 * Employee Profile Management System - Console Version
 * @author Kage
 */
public class Group4_CSS122P {

    private static EmployeeDataManager dataManager;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        dataManager = new EmployeeDataManager();
        scanner = new Scanner(System.in);
        
        System.out.println("==============================================");
        System.out.println("   EMPLOYEE PROFILE MANAGEMENT SYSTEM COMPANY");
        System.out.println("   Console Test Application");
        System.out.println("==============================================");
        
        // Add sample data if empty
        if (dataManager.getEmployeeCount() == 0) {
            addSampleData();
        }
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    filterByDepartment();
                    break;
                case 5:
                    filterByPerformanceStatus();
                    break;
                case 6:
                    updateEmployee();
                    break;
                case 7:
                    deleteEmployee();
                    break;
                case 8:
                    showStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using Employee Profile Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n------------------- MENU -------------------");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by Name");
        System.out.println("4. Filter by Department");
        System.out.println("5. Filter by Performance Status");
        System.out.println("6. Update Employee");
        System.out.println("7. Delete Employee");
        System.out.println("8. Show Statistics");
        System.out.println("0. Exit");
        System.out.println("--------------------------------------------");
    }
    
    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        
        System.out.println("Select Employee Type:");
        System.out.println("1. Regular Employee");
        System.out.println("2. Manager");
        int type = getIntInput("Choice: ");
        
        String id = getStringInput("Employee ID: ");
        
        // Check if ID exists
        if (dataManager.findEmployeeById(id) != null) {
            System.out.println("Error: Employee ID already exists!");
            return;
        }
        
        String firstName = getStringInput("First Name: ");
        String lastName = getStringInput("Last Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");
        String department = getStringInput("Department: ");
        String position = getStringInput("Position: ");
        double rating = getDoubleInput("Performance Rating (0-100): ");
        double salary = getDoubleInput("Salary: ");
        String hireDate = getStringInput("Hire Date (YYYY-MM-DD): ");
        
        Employee employee;
        
        if (type == 2) {
            int teamSize = getIntInput("Team Size: ");
            String level = getStringInput("Management Level: ");
            double bonus = getDoubleInput("Bonus Percentage: ");
            
            employee = EmployeeFactory.createEmployee(
                EmployeeFactory.TYPE_MANAGER,
                id, firstName, lastName, email, phone,
                department, position, rating, salary, hireDate,
                teamSize, level, bonus
            );
        } else {
            employee = EmployeeFactory.createEmployee(
                EmployeeFactory.TYPE_REGULAR,
                id, firstName, lastName, email, phone,
                department, position, rating, salary, hireDate
            );
        }
        
        dataManager.addEmployee(employee);
        System.out.println("Employee added successfully!");
    }
    
    private static void viewAllEmployees() {
        System.out.println("\n--- All Employees ---");
        List<Employee> employees = dataManager.getAllEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        printEmployeeTable(employees);
    }
    
    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");
        String name = getStringInput("Enter name to search: ");
        
        List<Employee> results = dataManager.searchByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No employees found matching '" + name + "'");
        } else {
            System.out.println("Found " + results.size() + " employee(s):");
            printEmployeeTable(results);
        }
    }
    
    private static void filterByDepartment() {
        System.out.println("\n--- Filter by Department ---");
        
        List<String> departments = dataManager.getDepartments();
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }
        
        System.out.println("Available Departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i));
        }
        
        int choice = getIntInput("Select department: ");
        if (choice >= 1 && choice <= departments.size()) {
            String dept = departments.get(choice - 1);
            List<Employee> employees = dataManager.getEmployeesByDepartment(dept);
            System.out.println("\nEmployees in " + dept + ":");
            printEmployeeTable(employees);
        }
    }
    
    private static void filterByPerformanceStatus() {
        System.out.println("\n--- Filter by Performance Status ---");
        System.out.println("1. EE (Exceeds Expectation)");
        System.out.println("2. ME (Meets Expectation)");
        System.out.println("3. PIP (Performance Improvement Plan)");
        
        int choice = getIntInput("Select status: ");
        String status = "";
        
        switch (choice) {
            case 1: status = "EE"; break;
            case 2: status = "ME"; break;
            case 3: status = "PIP"; break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        List<Employee> employees = dataManager.getEmployeesByPerformanceStatus(status);
        System.out.println("\nEmployees with status " + status + ":");
        printEmployeeTable(employees);
    }
    
    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        String id = getStringInput("Enter Employee ID to update: ");
        
        Employee employee = dataManager.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        
        System.out.println("Current details: " + employee);
        System.out.println("Enter new values (press Enter to keep current value):");
        
        String firstName = getStringInputOrDefault("First Name", employee.getFirstName());
        String lastName = getStringInputOrDefault("Last Name", employee.getLastName());
        String email = getStringInputOrDefault("Email", employee.getEmail());
        String phone = getStringInputOrDefault("Phone", employee.getPhone());
        String department = getStringInputOrDefault("Department", employee.getDepartment());
        String position = getStringInputOrDefault("Position", employee.getPosition());
        double rating = getDoubleInputOrDefault("Performance Rating", employee.getPerformanceRating());
        double salary = getDoubleInputOrDefault("Salary", employee.getSalary());
        
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setPerformanceRating(rating);
        employee.setSalary(salary);
        
        dataManager.updateEmployee(employee);
        System.out.println("Employee updated successfully!");
    }
    
    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        String id = getStringInput("Enter Employee ID to delete: ");
        
        Employee employee = dataManager.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        
        System.out.println("Employee to delete: " + employee.getFullName());
        String confirm = getStringInput("Are you sure? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes")) {
            if (dataManager.removeEmployeeById(id)) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Error deleting employee.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void showStatistics() {
        System.out.println("\n--- Employee Statistics ---");
        
        List<Employee> allEmployees = dataManager.getAllEmployees();
        int total = allEmployees.size();
        
        System.out.println("Total Employees: " + total);
        
        if (total > 0) {
            int eeCount = dataManager.getEmployeesByPerformanceStatus("EE").size();
            int meCount = dataManager.getEmployeesByPerformanceStatus("ME").size();
            int pipCount = dataManager.getEmployeesByPerformanceStatus("PIP").size();
            
            System.out.println("\nPerformance Distribution:");
            System.out.println("  EE (Exceeds Expectation): " + eeCount);
            System.out.println("  ME (Meets Expectation): " + meCount);
            System.out.println("  PIP (Performance Improvement Plan): " + pipCount);
            
            double avgRating = 0;
            for (Employee emp : allEmployees) {
                avgRating += emp.getPerformanceRating();
            }
            System.out.println("\nAverage Performance Rating: " + String.format("%.2f", avgRating / total));
            
            System.out.println("\nDepartments: " + dataManager.getDepartments().size());
            System.out.println("Positions: " + dataManager.getPositions().size());
        }
    }
    
    private static void printEmployeeTable(List<Employee> employees) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-15s %-8s %-15s%n",
                "ID", "Name", "Department", "Position", "Rating", "Status");
        System.out.println("-----------------------------------------------------------------------------");
        
        for (Employee emp : employees) {
            System.out.printf("%-8s %-20s %-15s %-15s %-8.1f %-15s%n",
                    emp.getId(),
                    emp.getFullName(),
                    emp.getDepartment(),
                    emp.getPosition(),
                    emp.getPerformanceRating(),
                    emp.getPerformanceStatus());
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Total: " + employees.size() + " employee(s)");
    }
    
    private static void addSampleData() {
        System.out.println("Adding sample data...");
        
        // Add regular employees
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_REGULAR,
            "E001", "Juan", "Dela Cruz", "juan@company.com", "09123456789",
            "IT", "Software Developer", 92.5, 45000, "2023-01-15"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_REGULAR,
            "E002", "Maria", "Santos", "maria@company.com", "09234567890",
            "HR", "HR Specialist", 85.0, 35000, "2023-02-20"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_REGULAR,
            "E003", "Pedro", "Reyes", "pedro@company.com", "09345678901",
            "IT", "System Analyst", 65.5, 40000, "2023-03-10"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_REGULAR,
            "E004", "Ana", "Garcia", "ana@company.com", "09456789012",
            "Finance", "Accountant", 78.0, 38000, "2023-04-05"
        ));
        
        // Add managers
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_MANAGER,
            "M001", "Carlos", "Lim", "carlos@company.com", "09567890123",
            "IT", "IT Manager", 95.0, 80000, "2022-01-10",
            10, "Senior", 20.0
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_MANAGER,
            "M002", "Sofia", "Tan", "sofia@company.com", "09678901234",
            "HR", "HR Manager", 88.5, 65000, "2022-06-15",
            5, "Mid", 15.0
        ));
        
        System.out.println("Sample data added successfully!");
    }
    
    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private static String getStringInputOrDefault(String prompt, String defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInputOrDefault(String prompt, double defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return defaultValue;
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}