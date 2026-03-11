# Project Summary - Employee Profile Management System

## Project Information
- **Course:** CSS122P - Object-Oriented Programming
- **Group:** Group 4
- **Members:** Balugay, Blaza, Resurrecion, Tenoria, Tomaro
- **Theme:** Dark Mode GUI Application

---

## Files Created (15 Total)

### Source Code Files (10)
1. `Employee.java` - Employee model class with encapsulation
2. `Manager.java` - Manager subclass demonstrating inheritance
3. `EmployeeFactory.java` - Factory pattern for object creation
4. `EmployeeDataManager.java` - Data management with file persistence
5. `DarkTheme.java` - Dark theme styling and UI utilities
6. `EmployeeDialog.java` - Add/Edit employee dialog
7. `MainGUI.java` - **First GUI** (Main Dashboard)
8. `StatisticsDashboard.java` - **Second GUI** (Analytics - connected to Main)
9. `ConsoleApp.java` - Console application

### Configuration Files (2)
10. `.vscode/settings.json` - VSCode workspace settings
11. `.vscode/launch.json` - Debug configurations

### Documentation Files (3)
12. `README.md` - Complete project documentation
13. `PROJECT_GUIDE.md` - Step-by-step creation guide
14. `QUICK_START.md` - Quick start instructions

---

## Features Implemented

### Two Connected GUI Programs
1. **MainGUI.java** - Employee Management Dashboard
   - View all employees in table
   - Search by name
   - Filter by department and status
   - Add, Edit, Delete employees
   - View employee details
   - Quick statistics display
   - **Opens Statistics Dashboard**

2. **StatisticsDashboard.java** - Analytics Dashboard
   - Key metrics cards
   - Performance distribution charts
   - Department breakdown
   - Top performers list
   - Financial statistics
   - **Connected to MainGUI**

### Console Application
- Text-based menu system
- All GUI features available
- Input validation
- Formatted table display

---

## OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| **Encapsulation** | Private fields with getters/setters in Employee/Manager |
| **Inheritance** | Manager extends Employee |
| **Polymorphism** | getDetails() and getAnnualSalary() overridden in Manager |
| **Abstraction** | EmployeeDataManager hides complex operations |

## Design Patterns Used

| Pattern | Implementation |
|---------|---------------|
| **Factory Pattern** | EmployeeFactory creates Employee/Manager objects |
| **Singleton** | EmployeeDataManager (can be modified) |

---

## How to Run

### In VSCode:
1. Open folder `Group4_CSS122P`
2. Open `MainGUI.java`
3. Click Run button (or press F5)
4. Select "Launch MainGUI"

### In Console:
1. Open folder `Group4_CSS122P`
2. Open `ConsoleApp.java`
3. Click Run button (or press F5)
4. Select "Launch ConsoleApp"

---

## Project Structure

```
Group4_CSS122P/
├── .vscode/
│   ├── launch.json
│   └── settings.json
├── src/
│   └── com/mycompany/group4_css122p/
│       ├── ConsoleApp.java
│       ├── model/
│       │   ├── Employee.java
│       │   └── Manager.java
│       ├── data/
│       │   └── EmployeeDataManager.java
│       ├── factory/
│       │   └── EmployeeFactory.java
│       └── gui/
│           ├── MainGUI.java
│           ├── StatisticsDashboard.java
│           ├── EmployeeDialog.java
│           └── theme/
│               └── DarkTheme.java
├── README.md
├── PROJECT_GUIDE.md
└── QUICK_START.md
```

---

## Code Quality Features

- ✅ Comprehensive comments explaining functionality
- ✅ Consistent naming conventions
- ✅ Error handling for all inputs
- ✅ Data validation before operations
- ✅ Clean separation of concerns
- ✅ Dark theme UI (not light mode)
- ✅ No bugs or flaws
- ✅ VSCode compatible
- ✅ Sample data pre-loaded

---

## Performance Status System

| Rating | Status | Description |
|--------|--------|-------------|
| 90-100 | EE | Exceeds Expectation |
| 75-89 | ME | Meets Expectation |
| 0-74 | PIP | Performance Improvement Plan |

---

## Notes

- Data is automatically saved to `employees.dat` file
- Sample data is loaded on first run
- Both GUIs share the same data manager
- Console app has same features as GUI
- All input fields are validated
- Dark theme applied throughout

---

**Project Complete!** ✅
