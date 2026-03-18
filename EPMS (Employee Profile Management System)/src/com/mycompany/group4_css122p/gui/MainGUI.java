package com.mycompany.group4_css122p.gui;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.factory.EmployeeFactory;
import com.mycompany.group4_css122p.gui.theme.ProfessionalTheme;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

/**
 * MainGUI.java - Primary GUI Application (Employee Management Dashboard)
 * 
 * This is the main graphical user interface for the Employee Profile
 * Management System. It provides a comprehensive dashboard for managing
 * employee records with a professional blue theme.
 * 
 * FEATURES:
 * - Employee data table with sorting and selection
 * - Search functionality by name
 * - Filter by department and performance status
 * - Add, Edit, Delete, View operations
 * - Quick statistics display
 * - Link to Statistics Dashboard (Second GUI)
 * 
 * GUI COMPONENTS:
 * - JTable: Displays employee data
 * - JTextField: Search input
 * - JComboBox: Filter dropdowns
 * - JButton: Action buttons
 * - JPanel: Organized layout containers
 * 
 * LAYOUT: BorderLayout with nested panels
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class MainGUI extends JFrame {
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    /** Data manager for all employee operations */
    private EmployeeDataManager dataManager;
    
    /** Table to display employee data */
    private JTable employeeTable;
    
    /** Table model for managing table data */
    private DefaultTableModel tableModel;
    
    /** Search field for name search */
    private JTextField searchField;
    
    /** Department filter dropdown */
    private JComboBox<String> departmentFilter;
    
    /** Status filter dropdown */
    private JComboBox<String> statusFilter;
    
    /** Status bar for displaying messages */
    private JLabel statusBar;
    
    /** Statistics labels for quick stats panel */
    private JLabel totalEmployeesLabel;
    private JLabel avgRatingLabel;
    private JLabel topPerformersLabel;
    private JLabel totalPayrollLabel;
    
    /** Reference to statistics dashboard (second GUI) */
    private StatisticsDashboard statsDashboard;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Constructor - Initializes the main GUI
     */
    public MainGUI() {
        // Initialize data manager
        dataManager = new EmployeeDataManager();
        
        // Add sample data if empty
        if (dataManager.getEmployeeCount() == 0) {
            addSampleData();
        }
        
        // Initialize components
        initializeComponents();
        
        // Setup layout
        setupLayout();
        
        // Setup event handlers
        setupEventHandlers();
        
        // Load initial data
        refreshTable();
        updateStatistics();
    }
    
    // ============================================================
    // INITIALIZATION
    // ============================================================
    
    /**
     * Creates and configures all GUI components
     */
    private void initializeComponents() {
        // Window settings
        setTitle("Employee Profile Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        // Table model with columns
        String[] columns = {"ID", "Name", "Department", "Position", "Rating", "Status", "Salary", "Type"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Read-only table
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Double.class;  // Rating column for sorting
                return String.class;
            }
        };
        
        // Create table
        employeeTable = new JTable(tableModel);
        employeeTable.setAutoCreateRowSorter(true);
        ProfessionalTheme.styleTable(employeeTable);
        
        // Set column widths
        employeeTable.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        employeeTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Name
        employeeTable.getColumnModel().getColumn(2).setPreferredWidth(100);  // Department
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(120);  // Position
        employeeTable.getColumnModel().getColumn(4).setPreferredWidth(60);   // Rating
        employeeTable.getColumnModel().getColumn(5).setPreferredWidth(150);  // Status
        employeeTable.getColumnModel().getColumn(6).setPreferredWidth(100);  // Salary
        employeeTable.getColumnModel().getColumn(7).setPreferredWidth(80);   // Type
        
        // Search field
        searchField = new JTextField(20);
        ProfessionalTheme.styleTextField(searchField);
        searchField.setToolTipText("Search by name...");
        
        // Filter dropdowns
        departmentFilter = new JComboBox<>();
        ProfessionalTheme.styleComboBox(departmentFilter);
        departmentFilter.addItem("All Departments");
        
        statusFilter = new JComboBox<>(new String[]{
            "All Status",
            "EE (Exceeds Expectation)",
            "ME (Meets Expectation)",
            "PIP (Performance Improvement Plan)"
        });
        ProfessionalTheme.styleComboBox(statusFilter);
        
        // Status bar
        statusBar = new JLabel("Ready");
        statusBar.setFont(ProfessionalTheme.FONT_SMALL);
        statusBar.setForeground(ProfessionalTheme.FOREGROUND_MUTED);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Statistics labels
        totalEmployeesLabel = createStatsLabel("0");
        avgRatingLabel = createStatsLabel("0.0");
        topPerformersLabel = createStatsLabel("0");
        totalPayrollLabel = createStatsLabel("$0.00");
    }
    
    /**
     * Creates a styled statistics label
     */
    private JLabel createStatsLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(ProfessionalTheme.ACCENT_PRIMARY);
        return label;
    }
    
    // ============================================================
    // LAYOUT SETUP
    // ============================================================
    
    /**
     * Arranges all components in the window
     */
    private void setupLayout() {
        // Main content pane
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBackground(ProfessionalTheme.BACKGROUND_DARKEST);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // ============================================================
        // TOP PANEL - Title and Search/Filter
        // ============================================================
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(ProfessionalTheme.BACKGROUND_DARKEST);
        
        // Title
        JLabel titleLabel = new JLabel("Employee Management Dashboard");
        titleLabel.setFont(ProfessionalTheme.FONT_TITLE);
        titleLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        topPanel.add(titleLabel, BorderLayout.WEST);
        
        // Search and filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ProfessionalTheme.BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        filterPanel.add(searchLabel);
        filterPanel.add(searchField);
        
        JButton searchBtn = new JButton("Search");
        ProfessionalTheme.styleButton(searchBtn, "primary");
        filterPanel.add(searchBtn);
        
        filterPanel.add(new JLabel("  "));  // Spacer
        
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        filterPanel.add(deptLabel);
        filterPanel.add(departmentFilter);
        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        filterPanel.add(statusLabel);
        filterPanel.add(statusFilter);
        
        JButton clearBtn = new JButton("Clear");
        ProfessionalTheme.styleButton(clearBtn, "secondary");
        filterPanel.add(clearBtn);
        
        topPanel.add(filterPanel, BorderLayout.CENTER);
        contentPane.add(topPanel, BorderLayout.NORTH);
        
        // ============================================================
        // CENTER PANEL - Table and Statistics
        // ============================================================
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(ProfessionalTheme.BACKGROUND_DARKEST);
        
        // Table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        ProfessionalTheme.styleScrollPane(tableScrollPane);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(ProfessionalTheme.BORDER_COLOR),
            "Employee List",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            ProfessionalTheme.FONT_BOLD,
            ProfessionalTheme.FOREGROUND_SECONDARY
        ));
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Quick statistics panel
        JPanel statsPanel = createQuickStatsPanel();
        centerPanel.add(statsPanel, BorderLayout.SOUTH);
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // ============================================================
        // EAST PANEL - Action Buttons
        // ============================================================
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ProfessionalTheme.BORDER_COLOR),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        buttonPanel.setPreferredSize(new Dimension(180, 0));
        
        // Panel title
        JLabel actionsTitle = new JLabel("Actions");
        actionsTitle.setFont(ProfessionalTheme.FONT_HEADER);
        actionsTitle.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        actionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(actionsTitle);
        buttonPanel.add(Box.createVerticalStrut(20));
        
        // Action buttons
        JButton addBtn = createActionButton("Add Employee", "success");
        JButton editBtn = createActionButton("Edit Employee", "primary");
        JButton deleteBtn = createActionButton("Delete Employee", "danger");
        JButton viewBtn = createActionButton("View Details", "default");
        JButton refreshBtn = createActionButton("Refresh", "secondary");
        JButton statsBtn = createActionButton("Statistics Dashboard", "primary");
        JButton exitBtn = createActionButton("Exit", "secondary");
        
        buttonPanel.add(addBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(editBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(deleteBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(viewBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(refreshBtn);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(new JSeparator());
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(statsBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitBtn);
        
        contentPane.add(buttonPanel, BorderLayout.EAST);
        
        // ============================================================
        // SOUTH PANEL - Status Bar
        // ============================================================
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        statusPanel.setBorder(BorderFactory.createLineBorder(ProfessionalTheme.BORDER_COLOR));
        statusPanel.add(statusBar, BorderLayout.WEST);
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        
        setContentPane(contentPane);
        
        // ============================================================
        // BUTTON ACTION LISTENERS
        // ============================================================
        addBtn.addActionListener(e -> showAddEmployeeDialog());
        editBtn.addActionListener(e -> showEditEmployeeDialog());
        deleteBtn.addActionListener(e -> deleteEmployee());
        viewBtn.addActionListener(e -> viewEmployeeDetails());
        refreshBtn.addActionListener(e -> {
            refreshTable();
            updateStatistics();
            setStatus("Table refreshed");
        });
        statsBtn.addActionListener(e -> openStatisticsDashboard());
        exitBtn.addActionListener(e -> System.exit(0));
        
        searchBtn.addActionListener(e -> searchEmployees());
        clearBtn.addActionListener(e -> clearFilters());
    }
    
    /**
     * Creates the quick statistics panel
     */
    private JPanel createQuickStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
        panel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ProfessionalTheme.BORDER_COLOR),
                "Quick Statistics",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                ProfessionalTheme.FONT_BOLD,
                ProfessionalTheme.FOREGROUND_SECONDARY
            ),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        panel.add(createStatCard("Total Employees", totalEmployeesLabel));
        panel.add(createStatCard("Average Rating", avgRatingLabel));
        panel.add(createStatCard("Top Performers", topPerformersLabel));
        panel.add(createStatCard("Monthly Payroll", totalPayrollLabel));
        
        return panel;
    }
    
    /**
     * Creates a single statistics card
     */
    private JPanel createStatCard(String title, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(ProfessionalTheme.BACKGROUND_MEDIUM);
        card.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(ProfessionalTheme.FONT_SMALL);
        titleLabel.setForeground(ProfessionalTheme.FOREGROUND_MUTED);
        card.add(titleLabel, BorderLayout.NORTH);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Creates an action button with consistent styling
     */
    private JButton createActionButton(String text, String type) {
        JButton button = new JButton(text);
        ProfessionalTheme.styleButton(button, type);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
    
    // ============================================================
    // EVENT HANDLERS
    // ============================================================
    
    /**
     * Sets up additional event handlers
     */
    private void setupEventHandlers() {
        // Double-click on table row to view details
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    viewEmployeeDetails();
                }
            }
        });
        
        // Filter dropdowns
        departmentFilter.addActionListener(e -> filterEmployees());
        statusFilter.addActionListener(e -> filterEmployees());
        
        // Enter key in search field
        searchField.addActionListener(e -> searchEmployees());
    }
    
    // ============================================================
    // TABLE OPERATIONS
    // ============================================================
    
    /**
     * Refreshes the table with all employees
     */
    public void refreshTable() {
        // Clear table
        tableModel.setRowCount(0);
        
        // Get all employees
        List<Employee> employees = dataManager.getAllEmployees();
        
        // Add rows
        for (Employee emp : employees) {
            addEmployeeToTable(emp);
        }
        
        // Update department filter
        updateDepartmentFilter();
        
        setStatus("Showing " + employees.size() + " employees");
    }
    
    /**
     * Adds a single employee row to the table
     */
    private void addEmployeeToTable(Employee emp) {
        String type = emp instanceof Manager ? "Manager" : "Regular";
        
        Object[] row = {
            emp.getId(),
            emp.getFullName(),
            emp.getDepartment(),
            emp.getPosition(),
            emp.getPerformanceRating(),
            emp.getPerformanceStatus(),
            String.format("$%,.2f", emp.getSalary()),
            type
        };
        tableModel.addRow(row);
    }
    
    /**
     * Updates department filter dropdown
     */
    private void updateDepartmentFilter() {
        String currentSelection = (String) departmentFilter.getSelectedItem();
        
        departmentFilter.removeAllItems();
        departmentFilter.addItem("All Departments");
        
        List<String> departments = dataManager.getDepartments();
        for (String dept : departments) {
            departmentFilter.addItem(dept);
        }
        
        // Restore selection if still exists
        if (currentSelection != null) {
            departmentFilter.setSelectedItem(currentSelection);
        }
    }
    
    /**
     * Searches employees by name
     */
    private void searchEmployees() {
        String searchTerm = searchField.getText().trim();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Employee> results = dataManager.searchByName(searchTerm);
        
        for (Employee emp : results) {
            addEmployeeToTable(emp);
        }
        
        setStatus("Found " + results.size() + " employee(s) matching '" + searchTerm + "'");
    }
    
    /**
     * Filters employees by department and status
     */
    private void filterEmployees() {
        String deptFilter = (String) departmentFilter.getSelectedItem();
        String statFilter = (String) statusFilter.getSelectedItem();
        
        // Get base list
        List<Employee> employees;
        if (!"All Departments".equals(deptFilter)) {
            employees = dataManager.getEmployeesByDepartment(deptFilter);
        } else {
            employees = dataManager.getAllEmployees();
        }
        
        // Apply status filter
        tableModel.setRowCount(0);
        int count = 0;
        
        for (Employee emp : employees) {
            if (!"All Status".equals(statFilter)) {
                String statusCode = emp.getPerformanceStatusCode();
                if (!statFilter.startsWith(statusCode)) {
                    continue;
                }
            }
            addEmployeeToTable(emp);
            count++;
        }
        
        setStatus("Showing " + count + " filtered employee(s)");
    }
    
    /**
     * Clears all filters
     */
    private void clearFilters() {
        searchField.setText("");
        departmentFilter.setSelectedIndex(0);
        statusFilter.setSelectedIndex(0);
        refreshTable();
    }
    
    // ============================================================
    // EMPLOYEE OPERATIONS
    // ============================================================
    
    /**
     * Shows dialog to add new employee
     */
    private void showAddEmployeeDialog() {
        EmployeeDialog dialog = new EmployeeDialog(this, dataManager, null);
        dialog.setVisible(true);
        
        if (dialog.isSaved()) {
            refreshTable();
            updateStatistics();
            setStatus("Employee added successfully");
        }
    }
    
    /**
     * Shows dialog to edit selected employee
     */
    private void showEditEmployeeDialog() {
        int selectedRow = employeeTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showWarning("Please select an employee to edit.");
            return;
        }
        
        String id = getSelectedEmployeeId(selectedRow);
        Employee employee = dataManager.findEmployeeById(id);
        
        if (employee != null) {
            EmployeeDialog dialog = new EmployeeDialog(this, dataManager, employee);
            dialog.setVisible(true);
            
            if (dialog.isSaved()) {
                refreshTable();
                updateStatistics();
                setStatus("Employee updated successfully");
            }
        }
    }
    
    /**
     * Deletes selected employee
     */
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showWarning("Please select an employee to delete.");
            return;
        }
        
        int modelRow = employeeTable.convertRowIndexToModel(selectedRow);
        String id = (String) tableModel.getValueAt(modelRow, 0);
        String name = (String) tableModel.getValueAt(modelRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete employee:\n" + name + " (" + id + ")?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (dataManager.removeEmployeeById(id)) {
                refreshTable();
                updateStatistics();
                setStatus("Employee deleted successfully");
            } else {
                showError("Error deleting employee.");
            }
        }
    }
    
    /**
     * Shows detailed information for selected employee
     */
    private void viewEmployeeDetails() {
        int selectedRow = employeeTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showWarning("Please select an employee to view.");
            return;
        }
        
        String id = getSelectedEmployeeId(selectedRow);
        Employee employee = dataManager.findEmployeeById(id);
        
        if (employee != null) {
            // Create custom dialog for details
            JTextArea textArea = new JTextArea(employee.getDetails());
            textArea.setFont(ProfessionalTheme.FONT_MONO);
            textArea.setBackground(ProfessionalTheme.BACKGROUND_DARK);
            textArea.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
            textArea.setEditable(false);
            textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            ProfessionalTheme.styleScrollPane(scrollPane);
            
            JOptionPane.showMessageDialog(this, scrollPane,
                "Employee Details - " + employee.getFullName(),
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ============================================================
    // STATISTICS DASHBOARD (SECOND GUI)
    // ============================================================
    
    /**
     * Opens or brings focus to the Statistics Dashboard
     * This is the SECOND GUI that connects to the main GUI
     */
    private void openStatisticsDashboard() {
        if (statsDashboard == null || !statsDashboard.isVisible()) {
            statsDashboard = new StatisticsDashboard(this, dataManager);
            statsDashboard.setVisible(true);
        } else {
            statsDashboard.toFront();
            statsDashboard.requestFocus();
        }
        setStatus("Statistics Dashboard opened");
    }
    
    // ============================================================
    // STATISTICS UPDATE
    // ============================================================
    
    /**
     * Updates the quick statistics panel
     */
    private void updateStatistics() {
        Map<String, Object> stats = dataManager.getStatistics();
        
        totalEmployeesLabel.setText(String.valueOf(stats.get("totalEmployees")));
        avgRatingLabel.setText(String.format("%.1f", stats.get("averageRating")));
        topPerformersLabel.setText(String.valueOf(stats.get("eeCount")));
        totalPayrollLabel.setText(String.format("$%,.0f", stats.get("totalMonthlyPayroll")));
    }
    
    // ============================================================
    // UTILITY METHODS
    // ============================================================
    
    /**
     * Sets status bar message
     */
    private void setStatus(String message) {
        statusBar.setText(message);
    }

    /**
     * Resolves the selected table row to the underlying model row and returns its employee ID.
     */
    private String getSelectedEmployeeId(int selectedRow) {
        int modelRow = employeeTable.convertRowIndexToModel(selectedRow);
        return (String) tableModel.getValueAt(modelRow, 0);
    }
    
    /**
     * Shows warning dialog
     */
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Shows error dialog
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // ============================================================
    // SAMPLE DATA
    // ============================================================
    
    /**
     * Adds sample employees for demonstration
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
        
        dataManager.addEmployee(EmployeeFactory.createEmployee(
            "E006", "Lisa", "Mendoza", "lisa@company.com", "09678901234",
            "IT", "QA Tester", 72.0, 35000, "2023-06-18"
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
    // MAIN METHOD
    // ============================================================
    
    /**
     * Application entry point
     */
    public static void main(String[] args) {
        // Apply dark theme before creating any components
        ProfessionalTheme.applyTheme();
        
        // Launch GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
  