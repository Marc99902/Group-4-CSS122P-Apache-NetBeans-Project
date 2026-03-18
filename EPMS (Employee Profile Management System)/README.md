# Employee Profile Management System

A Java desktop application for managing employee records within a company. This project demonstrates Object-Oriented Programming (OOP) concepts and design patterns.

## Project Information

- **Course**: CSS122P - Computer Programming 2 (Laboratory)
- **Group**: Group 4
- **Members**:
  - Balugay, Robi
  - Blaza, Jedrick
  - Resurrecion, Lorenzo
  - Tenoria, Marcus
  - Tomaro, Aaron
- **Submitted to**: Prof. Antonette D. Gabriel
- **Date**: March 2026

## Features

- **Graphical User Interface (GUI)**: Built with Java Swing (JFrame) with modern dark theme
- **File-based Data Persistence**: Employee records are saved to and loaded from a binary file
- **Performance Classification**: Automatic classification based on rating scores
- **Search and Filter**: By name, department, and performance status
- **Employee Types**: Support for Regular Employees and Managers
- **Statistics Dashboard**: Comprehensive analytics and reporting

## OOP Concepts Applied

### 1. ENCAPSULATION
- All class attributes are declared as `private`
- Access is controlled through public getter and setter methods
- Example: In Employee class, `private double performanceRating` is accessed via `getPerformanceRating()` and `setPerformanceRating()`

### 2. INHERITANCE
- `Employee` class extends `Person` (abstract class)
- `Manager` class extends `Employee`
- Creates an "is-a" relationship: Manager is an Employee, Employee is a Person

### 3. POLYMORPHISM
- Method overriding: `getRole()`, `getDetails()`, and `getAnnualSalary()` are overridden in subclasses
- Abstract methods in `Person` class are implemented differently by subclasses
- Runtime polymorphism allows treating Manager objects as Employee references

### 4. ABSTRACTION
- `Person` is an abstract class that cannot be instantiated directly
- Abstract methods `getRole()` and `getDetails()` define a contract for subclasses
- Implementation details are hidden from the user

### 5. AGGREGATION
- `Department` class contains a `List<Employee>` representing the "has-a" relationship
- A Department has many Employees
- Employees can exist independently of the Department

## Design Patterns

### Factory Pattern
Implemented through the `EmployeeFactory` class, which provides a centralized way to create different types of employee objects without exposing the instantiation logic to the client.

**Benefits:**
- Centralizes object creation logic
- Makes it easy to add new employee types in the future
- Reduces coupling between client code and concrete classes
- Provides a single point of control for object instantiation

## Performance Status Classification

The system automatically classifies employees based on their performance rating:

| Rating Range | Status Code | Description |
|--------------|-------------|-------------|
| 90 - 100 | EE | Exceeds Expectation |
| 70 - 89 | ME | Meets Expectation |
| Below 70 | PIP | Performance Improvement Plan |

## Project Structure

```
EmployeeManagementSystem/
├── src/
│   └── com/mycompany/group4_css122p/
│       ├── model/                    # Model Classes
│       │   ├── Person.java           # Abstract Class (Abstraction)
│       │   ├── Employee.java         # Inheritance + Encapsulation
│       │   ├── Manager.java          # Polymorphism + Inheritance
│       │   └── Department.java       # Aggregation
│       ├── factory/                  # Design Patterns
│       │   └── EmployeeFactory.java  # Factory Pattern
│       ├── data/                     # Data Management
│       │   └── EmployeeDataManager.java  # File I/O
│       └── gui/                      # GUI Components
│           ├── theme/
│           │   └── DarkTheme.java    # UI Styling
│           ├── MainGUI.java          # Main Dashboard
│           ├── EmployeeDialog.java   # Add/Edit Dialog
│           └── StatisticsDashboard.java  # Analytics GUI
└── README.md
```

## How to Compile and Run

### Using Command Line:

1. **Navigate to the source directory:**
   ```bash
   cd EmployeeManagementSystem/src
   ```

2. **Compile all Java files:**
   ```bash
   javac -d ../bin com/mycompany/group4_css122p/model/*.java \
         com/mycompany/group4_css122p/factory/*.java \
         com/mycompany/group4_css122p/data/*.java \
         com/mycompany/group4_css122p/gui/theme/*.java \
         com/mycompany/group4_css122p/gui/*.java
   ```

3. **Run the application:**
   ```bash
   cd ../bin
   java com.mycompany.group4_css122p.gui.MainGUI
   ```

### Using Apache NetBeans:

1. Open NetBeans IDE
2. Create a new Java Application project
3. Copy all source files to the `src` folder maintaining package structure
4. Right-click on `MainGUI.java` and select "Run File"

## UML Diagrams

### Class Diagram
The UML Class Diagram shows the relationships between classes:
- `Person` (Abstract) <- `Employee` <- `Manager`
- `Department` has `List<Employee>` (Aggregation)
- `EmployeeFactory` creates `Employee` and `Manager` objects
- `EmployeeDataManager` manages all employee data

### Use Case Diagram
Actors:
- **User**: Can add, edit, delete, search, filter employees
- **HR**: Can view details, filter by department, view statistics, generate reports

## Data Persistence

Employee data is automatically saved to a file named `employees.dat` using Java Serialization. The file is created in the same directory where the application is run.

## Sample Data

The application comes with pre-loaded sample data for demonstration:
- 6 Regular Employees (various departments and performance levels)
- 3 Managers (IT, HR, Finance departments)

## GUI Components

### Main Dashboard (MainGUI.java)
- Employee data table with sorting
- Search by name functionality
- Filter by department and performance status
- Action buttons: Add, Edit, Delete, View Details
- Quick statistics panel

### Employee Dialog (EmployeeDialog.java)
- Modal dialog for adding/editing employees
- Dynamic form that shows/hides manager fields
- Input validation
- Dark theme styling

### Statistics Dashboard (StatisticsDashboard.java)
- Key metrics cards
- Performance distribution chart
- Department breakdown
- Top performers list
- Payroll analysis

## License

This project is for educational purposes as part of the CSS122P course requirements.
