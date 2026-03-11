package com.mycompany.group4_css122p;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.factory.EmployeeFactory;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

import java.util.List;
import java.util.Scanner;

/**
 * ConsoleApp.java - Console Version of Employee Management System
 * 
 * This class provides a text-based user interface for managing employee
 * records through the command line/console. It's an alternative to the
 * GUI version and is useful for quick operations or environments without
 * graphical support.
 * 
 * FEATURES:
 * - Add new employees (Regular or Manager)
 * - View all employees in formatted table
 * - Search employees by name
 * - Filter by department or performance status
 * - Update employee information
 * - Delete employees
 * - View statistics
 * 
 * MENU OPTIONS:
 * 1. Add Employee
 * 2. View All Employees
 * 3. Search Employee by Name
 * 4. Filter by Department
 * 5. Filter by Performance Status
 * 6. Update Employee
 * 7. Delete Employee
 * 8. Show Statistics
 * 0. Exit
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class ConsoleApp {
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    /** Data manager for employee operations */
    private EmployeeDataManager dataManager;
    
    /** Scanner for reading user input */
    private Scanner scanner;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Constructor - Initializes the console application
     */
    public ConsoleApp() {
        dataManager = new EmployeeDataManager();
        scanner = new Scanner(System.in);
    }
    
    // ============================================================
    // MAIN METHOD - Application Entry Point
    // ============================================================
    
    /**
     * Main method - Starting point of console application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.run();
    }
    
    // ============================================================
    // MAIN APPLICATION LOOP
    // ============================================================
    
    /**
     * Runs the main application loop
     */
    public void run() {
        // Display welcome banner
        displayBanner();
        
        // Add sample data if database is empty
        if (dataManager.getEmployeeCount() == 0) {
            System.out.println("\nInitializing with sample data...");
            addSampleData();
            System.out.println("Sample data loaded successfully!\n");
        }
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            // Process user choice
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
                    System.out.println("\n==============================================");
                    System.out.println("  Thank you for using Employee Profile");
                    System.out.println("  Management System!");
                    System.out.println("==============================================\n");
                    break;
                default:
                    System.out.println("\n*** Invalid choice. Please try again. ***\n");
            }
        }
        
        // Close scanner
        scanner.close();
    }
    
    // ============================================================
    // DISPLAY METHODS
    // ============================================================
    
    /**
     * Displays the welcome banner
     */
    private void displayBanner() {
        System.out.println("==============================================");
        System.out.println("   EMPLOYEE PROFILE MANAGEMENT SYSTEM");
        System.out.println("   Console Application");
        System.out.println("   Group 4 - CSS122P");
        System.out.println("==============================================");
        System.out.println();
    }
    
    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println("------------------- MAIN MENU -------------------");
        System.out.println("  [1] Add Employee");
        System.out.println("  [2] View All Employees");
        System.out.println("  [3] Search Employee by Name");
        System.out.println("  [4] Filter by Department");
        System.out.println("  [5] Filter by Performance Status");
        System.out.println("  [6] Update Employee");
        System.out.println("  [7] Delete Employee");
        System.out.println("  [8] Show Statistics");
        System.out.println("  [0] Exit");
        System.out.println("-------------------------------------------------");
    }
    
    // ============================================================
    // MENU OPTION 1: ADD EMPLOYEE
    // ============================================================
    
    /**
     * Handles adding a new employee
     */
    private void addEmployee() {
        System.out.println("\n================ ADD NEW EMPLOYEE ================");
        
        // Select employee type
        System.out.println("\nSelect Employee Type:");
        System.out.println("  [1] Regular Employee");
        System.out.println("  [2] Manager");
        int type = getIntInput("Choice: ");
        
        // Get employee ID
        String id = getStringInput("\nEmployee ID: ");
        
        // Check if ID already exists
        if (dataManager.findEmployeeById(id) != null) {
            System.out.println("\n*** ERROR: Employee ID '" + id + "' already exists! ***\n");
            return;
        }
        
        // Get basic information
        System.out.println("\n--- Personal Information ---");
        String firstName = getStringInput("First Name: ");
        String lastName = getStringInput("Last Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");
        
        System.out.println("\n--- Work Information ---");
        String department = getStringInput("Department: ");
        String position = getStringInput("Position: ");
        double rating = getDoubleInput("Performance Rating (0-100): ");
        double salary = getDoubleInput("Monthly Salary: ");
        String hireDate = getStringInput("Hire Date (YYYY-MM-DD): ");
        
        Employee employee;
        
        // Create appropriate employee type
        if (type == 2) {
            System.out.println("\n--- Manager Information ---");
            int teamSize = getIntInput("Team Size: ");
            String level = getStringInput("Management Level (Junior/Mid/Senior/Executive): ");
            double bonus = getDoubleInput("Bonus Percentage: ");
            
            // Create Manager using Factory
            employee = EmployeeFactory.createEmployee(
                EmployeeFactory.TYPE_MANAGER,
                id, firstName, lastName, email, phone,
                department, position, rating, salary, hireDate,
                teamSize, level, bonus
            );
        } else {
            // Create Regular Employee using Factory
            employee = EmployeeFactory.createEmployee(
                EmployeeFactory.TYPE_REGULAR,
                id, firstName, lastName, email, phone,
                department, position, rating, salary, hireDate
            );
        }
        
        // Add to data manager
        if (dataManager.addEmployee(employee)) {
            System.out.println("\n*** Employee added successfully! ***\n");
        } else {
            System.out.println("\n*** ERROR: Failed to add employee! ***\n");
        }
    }
    
    // ============================================================
    // MENU OPTION 2: VIEW ALL EMPLOYEES
    // ============================================================
    
    /**
     * Displays all employees in a formatted table
     */
    private void viewAllEmployees() {
        System.out.println("\n================ ALL EMPLOYEES ================");
        
        List<Employee> employees = dataManager.getAllEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found in the database.\n");
            return;
        }
        
        printEmployeeTable(employees);
    }
    
    // ============================================================
    // MENU OPTION 3: SEARCH EMPLOYEE
    // ============================================================
    
    /**
     * Searches for employees by name
     */
    private void searchEmployee() {
        System.out.println("\n================ SEARCH EMPLOYEE ================");
        
        String name = getStringInput("Enter name to search: ");
        
        List<Employee> results = dataManager.searchByName(name);
        
        if (results.isEmpty()) {
            System.out.println("\nNo employees found matching '" + name + "'\n");
        } else {
            System.out.println("\nFound " + results.size() + " employee(s) matching '" + name + "':\n");
            printEmployeeTable(results);
        }
    }
    
    // ============================================================
    // MENU OPTION 4: FILTER BY DEPARTMENT
    // ============================================================
    
    /**
     * Filters employees by department
     */
    private void filterByDepartment() {
        System.out.println("\n================ FILTER BY DEPARTMENT ================");
        
        // Get available departments
        List<String> departments = dataManager.getDepartments();
        
        if (departments.isEmpty()) {
            System.out.println("\nNo departments available.\n");
            return;
        }
        
        // Display department list
        System.out.println("\nAvailable Departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + departments.get(i));
        }
        
        int choice = getIntInput("\nSelect department: ");
        
        if (choice >= 1 && choice <= departments.size()) {
            String dept = departments.get(choice - 1);
            List<Employee> employees = dataManager.getEmployeesByDepartment(dept);
            
            System.out.println("\n--- Employees in " + dept + " Department ---");
            printEmployeeTable(employees);
        } else {
            System.out.println("\n*** Invalid selection. ***\n");
        }
    }
    
    // ============================================================
    // MENU OPTION 5: FILTER BY PERFORMANCE STATUS
    // ============================================================
    
    /**
     * Filters employees by performance status
     */
    private void filterByPerformanceStatus() {
        System.out.println("\n================ FILTER BY PERFORMANCE STATUS ================");
        
        System.out.println("\nSelect Performance Status:");
        System.out.println("  [1] EE (Exceeds Expectation)");
        System.out.println("  [2] ME (Meets Expectation)");
        System.out.println("  [3] PIP (Performance Improvement Plan)");
        
        int choice = getIntInput("\nSelect status: ");
        String status = "";
        String statusName = "";
        
        switch (choice) {
            case 1:
                status = "EE";
                statusName = "Exceeds Expectation";
                break;
            case 2:
                status = "ME";
                statusName = "Meets Expectation";
                break;
            case 3:
                status = "PIP";
                statusName = "Performance Improvement Plan";
                break;
            default:
                System.out.println("\n*** Invalid choice. ***\n");
                return;
        }
        
        List<Employee> employees = dataManager.getEmployeesByPerformanceStatus(status);
        System.out.println("\n--- Employees with Status: " + statusName + " ---");
        printEmployeeTable(employees);
    }
    
    // ============================================================
    // MENU OPTION 6: UPDATE EMPLOYEE
    // ============================================================
    
    /**
     * Updates an existing employee's information
     */
    private void updateEmployee() {
        System.out.println("\n================ UPDATE EMPLOYEE ================");
        
        String id = getStringInput("Enter Employee ID to update: ");
        
        Employee employee = dataManager.findEmployeeById(id);
        if (employee == null) {
            System.out.println("\n*** Employee not found. ***\n");
            return;
        }
        
        System.out.println("\n--- Current Employee Details ---");
        System.out.println(employee.getDetails());
        System.out.println("\n--- Enter New Values (press Enter to keep current value) ---");
        
        // Get new values (or keep current if empty)
        String firstName = getStringInputOrDefault("First Name", employee.getFirstName());
        String lastName = getStringInputOrDefault("Last Name", employee.getLastName());
        String email = getStringInputOrDefault("Email", employee.getEmail());
        String phone = getStringInputOrDefault("Phone", employee.getPhone());
        String department = getStringInputOrDefault("Department", employee.getDepartment());
        String position = getStringInputOrDefault("Position", employee.getPosition());
        double rating = getDoubleInputOrDefault("Performance Rating", employee.getPerformanceRating());
        double salary = getDoubleInputOrDefault("Salary", employee.getSalary());
        
        // Update employee object
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setPerformanceRating(rating);
        employee.setSalary(salary);
        
        // Update manager-specific fields if applicable
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            int teamSize = getIntInputOrDefault("Team Size", manager.getTeamSize());
            String level = getStringInputOrDefault("Management Level", manager.getManagementLevel());
            double bonus = getDoubleInputOrDefault("Bonus Percentage", manager.getBonusPercentage());
            
            manager.setTeamSize(teamSize);
            manager.setManagementLevel(level);
            manager.setBonusPercentage(bonus);
        }
        
        // Save changes
        if (dataManager.updateEmployee(employee)) {
            System.out.println("\n*** Employee updated successfully! ***\n");
        } else {
            System.out.println("\n*** ERROR: Failed to update employee! ***\n");
        }
    }
    
    // ============================================================
    // MENU OPTION 7: DELETE EMPLOYEE
    // ============================================================
    
    /**
     * Deletes an employee from the system
     */
    private void deleteEmployee() {
        System.out.println("\n================ DELETE EMPLOYEE ================");
        
        String id = getStringInput("Enter Employee ID to delete: ");
        
        Employee employee = dataManager.findEmployeeById(id);
        if (employee == null) {
            System.out.println("\n*** Employee not found. ***\n");
            return;
        }
        
        System.out.println("\n--- Employee to Delete ---");
        System.out.println("ID: " + employee.getId());
        System.out.println("Name: " + employee.getFullName());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Position: " + employee.getPosition());
        
        String confirm = getStringInput("\nAre you sure you want to delete? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes")) {
            if (dataManager.removeEmployeeById(id)) {
                System.out.println("\n*** Employee deleted successfully! ***\n");
            } else {
                System.out.println("\n*** ERROR: Failed to delete employee! ***\n");
            }
        } else {
            System.out.println("\n*** Deletion cancelled. ***\n");
        }
    }
    
    // ============================================================
    // MENU OPTION 8: SHOW STATISTICS
    // ============================================================
    
    /**
     * Displays comprehensive statistics
     */
    private void showStatistics() {
        System.out.println("\n================ EMPLOYEE STATISTICS ================");
        
        List<Employee> allEmployees = dataManager.getAllEmployees();
        int total = allEmployees.size();
        
        if (total == 0) {
            System.out.println("\nNo employees in the database.\n");
            return;
        }
        
        // Get statistics
        int eeCount = dataManager.getEmployeesByPerformanceStatus("EE").size();
        int meCount = dataManager.getEmployeesByPerformanceStatus("ME").size();
        int pipCount = dataManager.getEmployeesByPerformanceStatus("PIP").size();
        int managerCount = dataManager.getAllManagers().size();
        
        // Display statistics
        System.out.println("\n--- General Statistics ---");
        System.out.println("Total Employees: " + total);
        System.out.println("  - Regular Employees: " + (total - managerCount));
        System.out.println("  - Managers: " + managerCount);
        System.out.println("Total Departments: " + dataManager.getDepartments().size());
        System.out.println("Total Positions: " + dataManager.getPositions().size());
        
        System.out.println("\n--- Performance Distribution ---");
        System.out.println("EE (Exceeds Expectation): " + eeCount + " (" + String.format("%.1f", eeCount * 100.0 / total) + "%)");
        System.out.println("ME (Meets Expectation): " + meCount + " (" + String.format("%.1f", meCount * 100.0 / total) + "%)");
        System.out.println("PIP (Performance Improvement Plan): " + pipCount + " (" + String.format("%.1f", pipCount * 100.0 / total) + "%)");
        
        System.out.println("\n--- Financial Statistics ---");
        System.out.println("Average Performance Rating: " + String.format("%.2f", dataManager.getAveragePerformanceRating()));
        System.out.println("Total Monthly Payroll: $" + String.format("%,.2f", dataManager.getTotalMonthlyPayroll()));
        System.out.println("Average Salary: $" + String.format("%,.2f", dataManager.getTotalMonthlyPayroll() / total));
        
        Employee highestPaid = dataManager.getHighestPaidEmployee();
        if (highestPaid != null) {
            System.out.println("Highest Paid: " + highestPaid.getFullName() + " ($" + String.format("%,.2f", highestPaid.getSalary()) + ")");
        }
        
        System.out.println();
    }
    
    // ============================================================
    // UTILITY METHODS
    // ============================================================
    
    /**
     * Prints a formatted table of employees
     * @param employees List of employees to print
     */
    private void printEmployeeTable(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.\n");
            return;
        }
        
        // Table header
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-18s %-8s %-22s %-12s%n",
                "ID", "Name", "Department", "Position", "Rating", "Status", "Type");
        System.out.println("--------------------------------------------------------------------------------------------");
        
        // Table rows
        for (Employee emp : employees) {
            String type = emp instanceof Manager ? "Manager" : "Regular";
            String status = emp.getPerformanceStatus();
            // Truncate status if too long
            if (status.length() > 22) {
                status = status.substring(0, 19) + "...";
            }
            
            System.out.printf("%-8s %-20s %-15s %-18s %-8.1f %-22s %-12s%n",
                    emp.getId(),
                    truncate(emp.getFullName(), 20),
                    truncate(emp.getDepartment(), 15),
                    truncate(emp.getPosition(), 18),
                    emp.getPerformanceRating(),
                    status,
                    type);
        }
        
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Total: " + employees.size() + " employee(s)\n");
    }
    
    /**
     * Truncates a string to specified length
     * @param str String to truncate
     * @param maxLength Maximum length
     * @return Truncated string
     */
    private String truncate(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Adds sample data for demonstration
     */
    private void addSampleData() {
        // Regular Employees
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E001", "Juan", "Dela Cruz", "juan@company.com", "09123456789",
            "IT", "Software Developer", 92.5, 45000, "2023-01-15"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E002", "Maria", "Santos", "maria@company.com", "09234567890",
            "HR", "HR Specialist", 85.0, 35000, "2023-02-20"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E003", "Pedro", "Reyes", "pedro@company.com", "09345678901",
            "IT", "System Analyst", 65.5, 40000, "2023-03-10"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E004", "Ana", "Garcia", "ana@company.com", "09456789012",
            "Finance", "Accountant", 78.0, 38000, "2023-04-05"
        ));
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E005", "Roberto", "Fernandez", "roberto@company.com", "09567890123",
            "Marketing", "Marketing Coordinator", 88.0, 36000, "2023-05-12"
        ));
        
        // Managers
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
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            EmployeeFactory.TYPE_MANAGER,
            "M003", "Miguel", "Rivera", "miguel@company.com", "09789012345",
            "Finance", "Finance Manager", 91.0, 75000, "2021-11-20",
            8, "Senior", 18.0
        ));
    }
    
    // ============================================================
    // INPUT HELPER METHODS
    // ============================================================
    
    /**
     * Gets string input from user
     * @param prompt Prompt message
     * @return User input
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Gets string input with default value
     * @param prompt Prompt message
     * @param defaultValue Default value if empty
     * @return User input or default value
     */
    private String getStringInputOrDefault(String prompt, String defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }
    
    /**
     * Gets integer input from user
     * @param prompt Prompt message
     * @return Integer value
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("*** Please enter a valid number. ***");
            }
        }
    }
    
    /**
     * Gets integer input with default value
     * @param prompt Prompt message
     * @param defaultValue Default value if empty
     * @return Integer value
     */
    private int getIntInputOrDefault(String prompt, int defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Gets double input from user
     * @param prompt Prompt message
     * @return Double value
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("*** Please enter a valid number. ***");
            }
        }
    }
    
    /**
     * Gets double input with default value
     * @param prompt Prompt message
     * @param defaultValue Default value if empty
     * @return Double value
     */
    private double getDoubleInputOrDefault(String prompt, double defaultValue) {
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
