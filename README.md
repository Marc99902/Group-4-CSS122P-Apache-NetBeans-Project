# Employee Profile Management System

**Group 4 - CSS122P (Object-Oriented Programming)**  
**BSIT 1st Year Project**

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Project Structure](#project-structure)
4. [How to Run](#how-to-run)
5. [OOP Concepts Used](#oop-concepts-used)
6. [Design Patterns](#design-patterns)
7. [Screenshots Guide](#screenshots-guide)
8. [Troubleshooting](#troubleshooting)

---

## Project Overview

This is a comprehensive Employee Profile Management System built with Java Swing GUI and Console interface. The system allows users to manage employee records including adding, editing, deleting, searching, and filtering employees. It features a modern dark theme UI and supports both Regular Employees and Managers.

### Authors
- Group 4: Balugay, Blaza, Resurrecion, Tenoria, Tomaro

---

## Features

### GUI Application (MainGUI.java)
- **Employee Management Dashboard** - View all employees in a table
- **Search Functionality** - Search employees by name
- **Filter Options** - Filter by department and performance status
- **Add/Edit/Delete** - Full CRUD operations
- **View Details** - Detailed employee information display
- **Quick Statistics** - Real-time statistics display
- **Dark Theme** - Modern dark mode UI

### Statistics Dashboard (StatisticsDashboard.java)
- **Key Metrics** - Total employees, managers, departments
- **Performance Charts** - Visual distribution of performance status
- **Department Breakdown** - Employee count per department
- **Top Performers** - List of high-performing employees
- **Financial Statistics** - Payroll and salary analytics

### Console Application (ConsoleApp.java)
- Text-based menu system
- All features available in GUI
- Useful for quick operations
- No graphical environment needed

---

## Project Structure

```
Group4_CSS122P/
├── .vscode/
│   ├── settings.json      # VSCode workspace settings
│   └── launch.json        # Debug configurations
├── src/
│   └── com/
│       └── mycompany/
│           └── group4_css122p/
│               ├── ConsoleApp.java              # Console application entry
│               ├── model/
│               │   ├── Employee.java            # Employee model class
│               │   └── Manager.java             # Manager subclass
│               ├── data/
│               │   └── EmployeeDataManager.java # Data management class
│               ├── factory/
│               │   └── EmployeeFactory.java     # Factory pattern class
│               └── gui/
│                   ├── MainGUI.java             # Main GUI application
│                   ├── StatisticsDashboard.java # Second GUI (Statistics)
│                   ├── EmployeeDialog.java      # Add/Edit dialog
│                   └── theme/
│                       └── DarkTheme.java       # Dark theme styling
├── bin/                   # Compiled .class files (auto-generated)
├── employees.dat          # Data file (auto-generated)
└── README.md             # This file
```

---

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Visual Studio Code (recommended)
- Java Extension Pack for VSCode

### Method 1: Using VSCode (Recommended)

1. **Open the project folder in VSCode**
   ```
   File > Open Folder > Select "Group4_CSS122P"
   ```

2. **Wait for Java extensions to load**
   - VSCode will automatically detect the Java project
   - Wait for "Java Projects" to appear in Explorer

3. **Run the GUI Application**
   - Open `src/com/mycompany/group4_css122p/gui/MainGUI.java`
   - Click the "Run" button (play icon) above the `main` method
   - OR press F5 and select "Launch MainGUI"

4. **Run the Console Application**
   - Open `src/com/mycompany/group4_css122p/ConsoleApp.java`
   - Click the "Run" button above the `main` method
   - OR press F5 and select "Launch ConsoleApp"

5. **Using Debug Panel**
   - Click the Run and Debug icon in the left sidebar (Ctrl+Shift+D)
   - Select "Launch MainGUI" or "Launch ConsoleApp" from dropdown
   - Press F5 to start debugging

### Method 2: Using Command Line

1. **Navigate to project directory**
   ```bash
   cd Group4_CSS122P
   ```

2. **Compile the project**
   ```bash
   javac -d bin -sourcepath src src/com/mycompany/group4_css122p/gui/MainGUI.java
   ```

3. **Run the GUI Application**
   ```bash
   java -cp bin com.mycompany.group4_css122p.gui.MainGUI
   ```

4. **Run the Console Application**
   ```bash
   java -cp bin com.mycompany.group4_css122p.ConsoleApp
   ```

---

## OOP Concepts Used

### 1. Encapsulation
- All fields in `Employee` and `Manager` classes are `private`
- Access controlled through public getter and setter methods
- Data validation can be added in setters

```java
// Example from Employee.java
private String id;
private double salary;

public String getId() { return id; }
public void setId(String id) { this.id = id; }
public double getSalary() { return salary; }
public void setSalary(double salary) { this.salary = salary; }
```

### 2. Inheritance
- `Manager` class extends `Employee` class
- Manager inherits all Employee properties and methods
- Manager adds specific attributes (teamSize, managementLevel, bonusPercentage)

```java
// From Manager.java
public class Manager extends Employee {
    private int teamSize;
    private String managementLevel;
    private double bonusPercentage;
    // ...
}
```

### 3. Polymorphism
- Method overriding: `getDetails()` and `getAnnualSalary()` in Manager
- Same method name, different implementations
- Runtime polymorphism through method calls

```java
// In Employee.java
public double getAnnualSalary() {
    return salary * 13;  // 12 months + 13th month
}

// In Manager.java (overridden)
@Override
public double getAnnualSalary() {
    double baseAnnual = getSalary() * 13;
    double bonus = baseAnnual * (bonusPercentage / 100.0);
    return baseAnnual + bonus;  // Includes bonus
}
```

### 4. Abstraction
- Complex data operations hidden in `EmployeeDataManager`
- Simple interface for adding, updating, deleting employees
- File I/O operations abstracted from user

---

## Design Patterns

### 1. Factory Pattern
- `EmployeeFactory` class creates Employee and Manager objects
- Centralizes object creation logic
- Easy to extend for new employee types

```java
// Usage example
Employee emp = EmployeeFactory.createEmployee(
    EmployeeFactory.TYPE_REGULAR,
    "E001", "Juan", "Dela Cruz", ...
);

Manager mgr = EmployeeFactory.createEmployee(
    EmployeeFactory.TYPE_MANAGER,
    "M001", "Carlos", "Lim", ...
);
```

### 2. Singleton Pattern (Optional Extension)
- `EmployeeDataManager` could be implemented as Singleton
- Ensures only one instance exists
- Shared across the application

---

## Performance Status System

Employees are categorized based on their performance rating:

| Status Code | Description | Rating Range |
|-------------|-------------|--------------|
| EE | Exceeds Expectation | 90 - 100 |
| ME | Meets Expectation | 75 - 89 |
| PIP | Performance Improvement Plan | 0 - 74 |

---

## Screenshots Guide

### Main GUI Features

1. **Dashboard View**
   - Employee table with all records
   - Search and filter controls
   - Quick statistics cards
   - Action buttons panel

2. **Add Employee Dialog**
   - Form for entering employee details
   - Dynamic fields for Manager type
   - Input validation

3. **Statistics Dashboard**
   - Key metrics display
   - Performance distribution chart
   - Department breakdown
   - Top performers list

### Console Application Menu

```
------------------- MAIN MENU -------------------
  [1] Add Employee
  [2] View All Employees
  [3] Search Employee by Name
  [4] Filter by Department
  [5] Filter by Performance Status
  [6] Update Employee
  [7] Delete Employee
  [8] Show Statistics
  [0] Exit
-------------------------------------------------
```

---

## Troubleshooting

### Issue: "Java not recognized"
**Solution:** Install JDK and set JAVA_HOME environment variable

### Issue: "Cannot find main class"
**Solution:** 
1. Check that you're in the correct directory
2. Ensure all files are compiled: `javac -d bin -sourcepath src src/com/mycompany/group4_css122p/gui/MainGUI.java`
3. Run with correct classpath: `java -cp bin com.mycompany.group4_css122p.gui.MainGUI`

### Issue: "ClassNotFoundException"
**Solution:** Make sure all .java files are compiled before running

### Issue: "GUI looks different (light theme)"
**Solution:** The dark theme is applied automatically. If it appears light, your system might be overriding it. This is normal and doesn't affect functionality.

### Issue: "Data not saving"
**Solution:** 
1. Check write permissions in project folder
2. Look for `employees.dat` file in project root
3. Data is saved automatically after each operation

---

## File Descriptions

| File | Description |
|------|-------------|
| `Employee.java` | Base model class with employee properties |
| `Manager.java` | Subclass extending Employee with manager-specific fields |
| `EmployeeFactory.java` | Factory pattern for creating employee objects |
| `EmployeeDataManager.java` | Handles all data operations and persistence |
| `MainGUI.java` | Main GUI application (First GUI) |
| `StatisticsDashboard.java` | Statistics dashboard (Second GUI) |
| `EmployeeDialog.java` | Dialog for adding/editing employees |
| `DarkTheme.java` | Dark theme styling and colors |
| `ConsoleApp.java` | Console-based application |
| `employees.dat` | Binary data file (auto-generated) |

---

## Notes for Instructors

### Code Quality Features
- Comprehensive comments explaining all functionality
- Consistent code formatting and naming conventions
- Error handling for user inputs
- Data validation before operations
- Clean separation of concerns (MVC-like structure)

### Extensibility
- Easy to add new employee types via Factory pattern
- Theme system allows easy UI customization
- Data manager can be extended for database support
- Modular design allows feature additions

---

## License

This project is created for educational purposes as part of the CSS122P course requirements.

---

**End of README**
