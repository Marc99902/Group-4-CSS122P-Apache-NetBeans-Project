package com.mycompany.group4_css122p.gui;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Main JFrame for Employee Profile Management System
 * @author Kage
 */
public class MainFrame extends JFrame {
    private EmployeeDataManager dataManager;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> departmentFilter;
    private JComboBox<String> statusFilter;
    
    public MainFrame() {
        dataManager = new EmployeeDataManager();
        
        // Add sample data if empty
        if (dataManager.getEmployeeCount() == 0) {
            addSampleData();
        }
        
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        refreshTable();
    }
    
    private void initializeComponents() {
        setTitle("Employee Profile Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Table setup
        String[] columns = {"ID", "Name", "Department", "Position", "Rating", "Status", "Salary"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Read-only table
            }
        };
        employeeTable = new JTable(tableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Search field
        searchField = new JTextField(20);
        
        // Filter combos
        departmentFilter = new JComboBox<>();
        departmentFilter.addItem("All Departments");
        
        statusFilter = new JComboBox<>();
        statusFilter.addItem("All Status");
        statusFilter.addItem("EE (Exceeds Expectation)");
        statusFilter.addItem("ME (Meets Expectation)");
        statusFilter.addItem("PIP (Performance Improvement Plan)");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Top panel with search and filters
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Search & Filter"));
        
        topPanel.add(new JLabel("Search Name:"));
        topPanel.add(searchField);
        topPanel.add(new JButton("Search") {{ addActionListener(e -> searchEmployees()); }});
        
        topPanel.add(new JLabel("Department:"));
        topPanel.add(departmentFilter);
        departmentFilter.addActionListener(e -> filterEmployees());
        
        topPanel.add(new JLabel("Status:"));
        topPanel.add(statusFilter);
        statusFilter.addActionListener(e -> filterEmployees());
        
        add(topPanel, BorderLayout.NORTH);
        
        // Center panel with table
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Employee List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Right panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.setPreferredSize(new Dimension(150, 0));
        
        JButton addButton = new JButton("Add Employee");
        JButton editButton = new JButton("Edit Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton viewButton = new JButton("View Details");
        JButton refreshButton = new JButton("Refresh");
        JButton clearFilterButton = new JButton("Clear Filters");
        JButton statsButton = new JButton("Statistics");
        JButton exitButton = new JButton("Exit");
        
        addButton.addActionListener(e -> showAddEmployeeDialog());
        editButton.addActionListener(e -> showEditEmployeeDialog());
        deleteButton.addActionListener(e -> deleteEmployee());
        viewButton.addActionListener(e -> viewEmployeeDetails());
        refreshButton.addActionListener(e -> refreshTable());
        clearFilterButton.addActionListener(e -> clearFilters());
        statsButton.addActionListener(e -> showStatistics());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearFilterButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.EAST);
        
        // Status bar
        JLabel statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        add(statusBar, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        // Double-click to view details
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    viewEmployeeDetails();
                }
            }
        });
    }
    
    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Employee> employees = dataManager.getAllEmployees();
        
        for (Employee emp : employees) {
            Object[] row = {
                emp.getId(),
                emp.getFullName(),
                emp.getDepartment(),
                emp.getPosition(),
                emp.getPerformanceRating(),
                emp.getPerformanceStatus(),
                String.format("$%.2f", emp.getSalary())
            };
            tableModel.addRow(row);
        }
        
        updateDepartmentFilter();
    }
    
    private void updateDepartmentFilter() {
        String currentSelection = (String) departmentFilter.getSelectedItem();
        departmentFilter.removeAllItems();
        departmentFilter.addItem("All Departments");
        
        List<String> departments = dataManager.getDepartments();
        for (String dept : departments) {
            departmentFilter.addItem(dept);
        }
        
        if (currentSelection != null) {
            departmentFilter.setSelectedItem(currentSelection);
        }
    }
    
    private void searchEmployees() {
        String searchTerm = searchField.getText().trim();
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Employee> employees = dataManager.searchByName(searchTerm);
        
        for (Employee emp : employees) {
            Object[] row = {
                emp.getId(),
                emp.getFullName(),
                emp.getDepartment(),
                emp.getPosition(),
                emp.getPerformanceRating(),
                emp.getPerformanceStatus(),
                String.format("$%.2f", emp.getSalary())
            };
            tableModel.addRow(row);
        }
    }
    
    private void filterEmployees() {
        String deptFilter = (String) departmentFilter.getSelectedItem();
        String statFilter = (String) statusFilter.getSelectedItem();
        
        List<Employee> employees;
        
        if (!deptFilter.equals("All Departments")) {
            employees = dataManager.getEmployeesByDepartment(deptFilter);
        } else {
            employees = dataManager.getAllEmployees();
        }
        
        tableModel.setRowCount(0);
        
        for (Employee emp : employees) {
            if (!statFilter.equals("All Status") && !emp.getPerformanceStatus().contains(statFilter.substring(0, 2))) {
                continue;
            }
            
            Object[] row = {
                emp.getId(),
                emp.getFullName(),
                emp.getDepartment(),
                emp.getPosition(),
                emp.getPerformanceRating(),
                emp.getPerformanceStatus(),
                String.format("$%.2f", emp.getSalary())
            };
            tableModel.addRow(row);
        }
    }
    
    private void clearFilters() {
        searchField.setText("");
        departmentFilter.setSelectedIndex(0);
        statusFilter.setSelectedIndex(0);
        refreshTable();
    }
    
    private void showAddEmployeeDialog() {
        EmployeeDialog dialog = new EmployeeDialog(this, dataManager, null);
        dialog.setVisible(true);
        refreshTable();
    }
    
    private void showEditEmployeeDialog() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        Employee employee = dataManager.findEmployeeById(id);
        
        if (employee != null) {
            EmployeeDialog dialog = new EmployeeDialog(this, dataManager, employee);
            dialog.setVisible(true);
            refreshTable();
        }
    }
    
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete employee: " + name + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (dataManager.removeEmployeeById(id)) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting employee.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void viewEmployeeDetails() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to view.", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        Employee employee = dataManager.findEmployeeById(id);
        
        if (employee != null) {
            StringBuilder details = new StringBuilder();
            details.append("=== EMPLOYEE DETAILS ===\n\n");
            details.append("ID: ").append(employee.getId()).append("\n");
            details.append("Name: ").append(employee.getFullName()).append("\n");
            details.append("Email: ").append(employee.getEmail()).append("\n");
            details.append("Phone: ").append(employee.getPhone()).append("\n");
            details.append("Department: ").append(employee.getDepartment()).append("\n");
            details.append("Position: ").append(employee.getPosition()).append("\n");
            details.append("Role: ").append(employee.getRole()).append("\n");
            details.append("Performance Rating: ").append(employee.getPerformanceRating()).append("\n");
            details.append("Performance Status: ").append(employee.getPerformanceStatus()).append("\n");
            details.append("Salary: $").append(String.format("%.2f", employee.getSalary())).append("\n");
            details.append("Hire Date: ").append(employee.getHireDate()).append("\n");
            
            if (employee instanceof Manager) {
                Manager mgr = (Manager) employee;
                details.append("\n=== MANAGER DETAILS ===\n");
                details.append("Team Size: ").append(mgr.getTeamSize()).append("\n");
                details.append("Management Level: ").append(mgr.getManagementLevel()).append("\n");
                details.append("Bonus: ").append(mgr.getBonusPercentage()).append("%\n");
                details.append("Total Compensation: $").append(String.format("%.2f", mgr.calculateTotalCompensation())).append("\n");
            }
            
            JOptionPane.showMessageDialog(this, details.toString(), 
                "Employee Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void showStatistics() {
        List<Employee> allEmployees = dataManager.getAllEmployees();
        
        int total = allEmployees.size();
        int eeCount = dataManager.getEmployeesByPerformanceStatus("EE").size();
        int meCount = dataManager.getEmployeesByPerformanceStatus("ME").size();
        int pipCount = dataManager.getEmployeesByPerformanceStatus("PIP").size();
        
        double avgRating = 0;
        for (Employee emp : allEmployees) {
            avgRating += emp.getPerformanceRating();
        }
        avgRating = total > 0 ? avgRating / total : 0;
        
        StringBuilder stats = new StringBuilder();
        stats.append("=== EMPLOYEE STATISTICS ===\n\n");
        stats.append("Total Employees: ").append(total).append("\n\n");
        stats.append("Performance Distribution:\n");
        stats.append("  EE (Exceeds Expectation): ").append(eeCount).append("\n");
        stats.append("  ME (Meets Expectation): ").append(meCount).append("\n");
        stats.append("  PIP (Performance Improvement Plan): ").append(pipCount).append("\n\n");
        stats.append("Average Performance Rating: ").append(String.format("%.2f", avgRating)).append("\n\n");
        stats.append("Departments: ").append(dataManager.getDepartments().size()).append("\n");
        stats.append("Positions: ").append(dataManager.getPositions().size());
        
        JOptionPane.showMessageDialog(this, stats.toString(), 
            "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void addSampleData() {
        // Add regular employees
        dataManager.addEmployee(new com.mycompany.group4_css122p.model.Employee(
            "E001", "Juan", "Dela Cruz", "juan@company.com", "09123456789",
            "IT", "Software Developer", 92.5, 45000, "2023-01-15"
        ));
        
        dataManager.addEmployee(new com.mycompany.group4_css122p.model.Employee(
            "E002", "Maria", "Santos", "maria@company.com", "09234567890",
            "HR", "HR Specialist", 85.0, 35000, "2023-02-20"
        ));
        
        dataManager.addEmployee(new com.mycompany.group4_css122p.model.Employee(
            "E003", "Pedro", "Reyes", "pedro@company.com", "09345678901",
            "IT", "System Analyst", 65.5, 40000, "2023-03-10"
        ));
        
        dataManager.addEmployee(new com.mycompany.group4_css122p.model.Employee(
            "E004", "Ana", "Garcia", "ana@company.com", "09456789012",
            "Finance", "Accountant", 78.0, 38000, "2023-04-05"
        ));
        
        // Add managers
        dataManager.addEmployee(new Manager(
            "M001", "Carlos", "Lim", "carlos@company.com", "09567890123",
            "IT", "IT Manager", 95.0, 80000, "2022-01-10",
            10, "Senior", 20.0
        ));
        
        dataManager.addEmployee(new Manager(
            "M002", "Sofia", "Tan", "sofia@company.com", "09678901234",
            "HR", "HR Manager", 88.5, 65000, "2022-06-15",
            5, "Mid", 15.0
        ));
    }
}