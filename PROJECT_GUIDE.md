# Employee Profile Management System - Project Guide

## How This Project Was Created

This guide explains the step-by-step process of creating this Java project from scratch.

---

## Phase 1: Project Planning

### 1.1 Define Requirements
- Employee management system with GUI
- Support for Regular Employees and Managers
- CRUD operations (Create, Read, Update, Delete)
- Search and filter functionality
- Statistics and reporting
- Dark theme UI
- Console application alternative

### 1.2 Design Class Structure
```
Model Layer:
- Employee (base class)
- Manager (extends Employee)

Data Layer:
- EmployeeDataManager (handles data operations)

Factory Layer:
- EmployeeFactory (creates employee objects)

GUI Layer:
- MainGUI (main dashboard)
- StatisticsDashboard (analytics)
- EmployeeDialog (add/edit form)
- DarkTheme (styling)

Console Layer:
- ConsoleApp (text-based interface)
```

---

## Phase 2: Creating Model Classes

### Step 1: Employee.java
**Purpose:** Base class for all employees

**Key Components:**
```java
// 1. Define private fields (Encapsulation)
private String id;
private String firstName;
private String lastName;
private String email;
private String phone;
private String department;
private String position;
private double performanceRating;
private double salary;
private String hireDate;

// 2. Create constructors
public Employee() { }  // Default
public Employee(String id, String firstName, ...) { }  // Full

// 3. Create getters and setters
public String getId() { return id; }
public void setId(String id) { this.id = id; }
// ... repeat for all fields

// 4. Add business logic
public String getPerformanceStatus() {
    if (performanceRating >= 90) return "EE (Exceeds Expectation)";
    else if (performanceRating >= 75) return "ME (Meets Expectation)";
    else return "PIP (Performance Improvement Plan)";
}

// 5. Override methods
@Override
public String toString() { ... }
@Override
public boolean equals(Object obj) { ... }
```

### Step 2: Manager.java
**Purpose:** Subclass for managers with additional attributes

**Key Components:**
```java
// 1. Extend Employee
public class Manager extends Employee {
    
    // 2. Add manager-specific fields
    private int teamSize;
    private String managementLevel;
    private double bonusPercentage;
    
    // 3. Override methods for polymorphism
    @Override
    public double getAnnualSalary() {
        double base = super.getAnnualSalary();
        double bonus = base * (bonusPercentage / 100);
        return base + bonus;
    }
    
    @Override
    public String getDetails() {
        // Include manager-specific info
    }
}
```

---

## Phase 3: Creating Data Management

### Step 3: EmployeeDataManager.java
**Purpose:** Handle all data operations and persistence

**Key Components:**
```java
public class EmployeeDataManager {
    private List<Employee> employees;  // In-memory storage
    private static final String DATA_FILE = "employees.dat";
    
    // CRUD Operations
    public boolean addEmployee(Employee employee) { ... }
    public boolean updateEmployee(Employee employee) { ... }
    public boolean removeEmployeeById(String id) { ... }
    public Employee findEmployeeById(String id) { ... }
    public List<Employee> getAllEmployees() { ... }
    
    // Search & Filter
    public List<Employee> searchByName(String name) { ... }
    public List<Employee> getEmployeesByDepartment(String dept) { ... }
    public List<Employee> getEmployeesByPerformanceStatus(String status) { ... }
    
    // Persistence
    public void saveData() { ... }  // Serialize to file
    public void loadData() { ... }  // Deserialize from file
    
    // Statistics
    public Map<String, Object> getStatistics() { ... }
}
```

---

## Phase 4: Creating Factory Pattern

### Step 4: EmployeeFactory.java
**Purpose:** Centralize object creation

**Key Components:**
```java
public class EmployeeFactory {
    public static final String TYPE_REGULAR = "REGULAR";
    public static final String TYPE_MANAGER = "MANAGER";
    
    // Method overloading for different creation scenarios
    public static Employee createEmployee(String id, String firstName, ...) {
        return new Employee(id, firstName, ...);
    }
    
    public static Employee createEmployee(String type, String id, ...) {
        if (TYPE_MANAGER.equals(type)) {
            return new Manager(id, ...);
        }
        return new Employee(id, ...);
    }
    
    public static Employee createEmployee(String type, String id, ..., 
            int teamSize, String level, double bonus) {
        // Create with manager-specific fields
    }
}
```

---

## Phase 5: Creating Dark Theme

### Step 5: DarkTheme.java
**Purpose:** Provide consistent dark styling

**Key Components:**
```java
public class DarkTheme {
    // Color palette
    public static final Color BACKGROUND_DARK = new Color(0x2D2D2D);
    public static final Color FOREGROUND_PRIMARY = new Color(0xFFFFFF);
    public static final Color ACCENT_PRIMARY = new Color(0x007ACC);
    
    // Fonts
    public static final Font FONT_DEFAULT = new Font("Segoe UI", Font.PLAIN, 12);
    
    // Global theme application
    public static void applyTheme() {
        UIManager.put("Panel.background", BACKGROUND_DARK);
        UIManager.put("Label.foreground", FOREGROUND_PRIMARY);
        // ... set all component defaults
    }
    
    // Component-specific styling
    public static void styleButton(JButton button, String type) { ... }
    public static void styleTextField(JTextField field) { ... }
    public static void styleTable(JTable table) { ... }
}
```

---

## Phase 6: Creating GUI Components

### Step 6: EmployeeDialog.java
**Purpose:** Dialog for adding/editing employees

**Structure:**
```java
public class EmployeeDialog extends JDialog {
    // Form fields
    private JTextField idField, firstNameField, ...;
    private JComboBox<String> typeComboBox;
    private JPanel managerPanel;  // Shown only for managers
    
    public EmployeeDialog(JFrame parent, EmployeeDataManager dm, Employee emp) {
        // Setup dialog
        // Initialize components
        // Setup layout
        // If editing, populate fields
    }
    
    private void setupLayout() {
        // Use GridBagLayout for form
        // Add all fields
        // Add manager panel (initially hidden)
    }
    
    private void saveEmployee() {
        // Validate inputs
        // Create/update employee
        // Save to data manager
    }
}
```

### Step 7: MainGUI.java (First GUI)
**Purpose:** Main dashboard for employee management

**Layout Structure:**
```
BorderLayout
├── NORTH: Search & Filter Panel
│   ├── Search field + button
│   ├── Department filter dropdown
│   └── Status filter dropdown
├──
├── CENTER: Table + Statistics
│   ├── JTable with employee data
│   └── Quick statistics panel
├──
├── EAST: Action Buttons
│   ├── Add Employee
│   ├── Edit Employee
│   ├── Delete Employee
│   ├── View Details
│   ├── Refresh
│   ├── Statistics Dashboard
│   └── Exit
└──
    └── SOUTH: Status Bar
```

**Key Methods:**
```java
public class MainGUI extends JFrame {
    private void initializeComponents() { ... }
    private void setupLayout() { ... }
    private void setupEventHandlers() { ... }
    
    // Table operations
    public void refreshTable() { ... }
    private void searchEmployees() { ... }
    private void filterEmployees() { ... }
    
    // Employee operations
    private void showAddEmployeeDialog() { ... }
    private void showEditEmployeeDialog() { ... }
    private void deleteEmployee() { ... }
    private void viewEmployeeDetails() { ... }
    
    // Second GUI connection
    private void openStatisticsDashboard() { ... }
}
```

### Step 8: StatisticsDashboard.java (Second GUI)
**Purpose:** Analytics and statistics display

**Layout Structure:**
```
JSplitPane (Horizontal)
├── LEFT: Key Metrics & Charts
│   ├── Key metrics cards (6 cards)
│   ├── Performance distribution chart
│   └── Department breakdown chart
└──
    └── RIGHT: Detailed Analysis
        ├── Top performers list
        ├── Performance status counts
        └── Highest paid employee
```

**Connection to MainGUI:**
```java
public class StatisticsDashboard extends JFrame {
    private MainGUI mainGUI;  // Reference to parent
    
    // Opened from MainGUI via button click
    private void openStatisticsDashboard() {
        if (statsDashboard == null || !statsDashboard.isVisible()) {
            statsDashboard = new StatisticsDashboard(this, dataManager);
            statsDashboard.setVisible(true);
        }
    }
}
```

---

## Phase 7: Creating Console Application

### Step 9: ConsoleApp.java
**Purpose:** Text-based interface

**Structure:**
```java
public class ConsoleApp {
    private EmployeeDataManager dataManager;
    private Scanner scanner;
    
    public void run() {
        displayBanner();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewAllEmployees(); break;
                // ... other cases
                case 0: running = false; break;
            }
        }
    }
    
    // Menu option methods
    private void addEmployee() { ... }
    private void viewAllEmployees() { ... }
    private void searchEmployee() { ... }
    // ... etc
}
```

---

## Phase 8: VSCode Configuration

### Step 10: Create .vscode/settings.json
```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "editor.formatOnSave": true,
    "editor.tabSize": 4
}
```

### Step 11: Create .vscode/launch.json
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch MainGUI",
            "request": "launch",
            "mainClass": "com.mycompany.group4_css122p.gui.MainGUI"
        },
        {
            "type": "java",
            "name": "Launch ConsoleApp",
            "request": "launch",
            "mainClass": "com.mycompany.group4_css122p.ConsoleApp",
            "console": "integratedTerminal"
        }
    ]
}
```

---

## Phase 9: Testing

### Test Cases to Verify:

1. **Add Employee**
   - Add regular employee
   - Add manager
   - Try duplicate ID (should fail)
   - Try invalid data (should validate)

2. **Edit Employee**
   - Edit regular employee
   - Edit manager
   - Change type (regular to manager)

3. **Delete Employee**
   - Delete with confirmation
   - Cancel deletion

4. **Search & Filter**
   - Search by name (partial match)
   - Filter by department
   - Filter by status
   - Combine filters

5. **Statistics**
   - Verify calculations
   - Check charts update

6. **Data Persistence**
   - Close and reopen app
   - Verify data saved

---

## Common Issues and Solutions

### Issue 1: "Package does not exist"
**Cause:** Incorrect package declaration or directory structure
**Solution:** Ensure package matches directory structure

### Issue 2: "Cannot find symbol"
**Cause:** Missing import or typo
**Solution:** Check imports and spelling

### Issue 3: "ClassCastException"
**Cause:** Casting object to wrong type
**Solution:** Use instanceof check before casting

### Issue 4: "NullPointerException"
**Cause:** Using null object
**Solution:** Add null checks

---

## Best Practices Applied

1. **Naming Conventions**
   - Classes: PascalCase (EmployeeDataManager)
   - Methods: camelCase (getEmployeeById)
   - Constants: UPPER_CASE (TYPE_MANAGER)

2. **Code Organization**
   - One class per file
   - Related classes in same package
   - Clear separation of concerns

3. **Comments**
   - Class-level JavaDoc
   - Method-level JavaDoc
   - Inline comments for complex logic

4. **Error Handling**
   - Try-catch blocks
   - Input validation
   - User-friendly error messages

---

## Extension Ideas

1. **Database Support**
   - Replace file storage with MySQL/PostgreSQL
   - Use JDBC for database operations

2. **Login System**
   - Add user authentication
   - Role-based access control

3. **Export Features**
   - Export to CSV/Excel
   - Generate PDF reports

4. **Advanced Search**
   - Multiple criteria search
   - Date range filters

5. **Email Notifications**
   - Send alerts for PIP employees
   - Birthday notifications

---

**End of Project Guide**
