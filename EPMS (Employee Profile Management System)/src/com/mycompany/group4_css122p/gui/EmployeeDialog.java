package com.mycompany.group4_css122p.gui;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.factory.EmployeeFactory;
import com.mycompany.group4_css122p.gui.theme.ProfessionalTheme;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * EmployeeDialog.java - Dialog for Adding/Editing Employees
 * 
 * This class creates a modal dialog window for adding new employees
 * or editing existing ones. It supports both Regular Employees and Managers.
 * 
 * FEATURES:
 * - Dynamic form that shows/hides manager fields based on type
 * - Input validation for all fields
 * - Dark theme styling
 * - Auto-fill for edit mode
 * 
 * DIALOG MODES:
 * - ADD MODE: Pass null as employee parameter
 * - EDIT MODE: Pass existing Employee object
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class EmployeeDialog extends JDialog {
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    /** Reference to data manager for saving changes */
    private EmployeeDataManager dataManager;
    
    /** Employee being edited (null for add mode) */
    private Employee existingEmployee;
    
    /** Flag to track if save was successful */
    private boolean saved = false;
    
    // Form fields
    private JComboBox<String> typeComboBox;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextField ratingField;
    private JTextField salaryField;
    private JTextField hireDateField;
    
    // Manager-specific fields
    private JTextField teamSizeField;
    private JComboBox<String> levelComboBox;
    private JTextField bonusField;
    private JPanel managerPanel;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Creates the Employee Dialog
     * 
     * @param parent Parent frame (for modal positioning)
     * @param dataManager Data manager reference
     * @param employee Employee to edit (null for add mode)
     */
    public EmployeeDialog(JFrame parent, EmployeeDataManager dataManager, Employee employee) {
        // Call parent constructor with modal flag
        super(parent, employee == null ? "Add Employee" : "Edit Employee", true);
        
        this.dataManager = dataManager;
        this.existingEmployee = employee;
        
        // Initialize components
        initializeComponents();
        
        // Setup layout
        setupLayout();
        
        // Setup event handlers
        setupEventHandlers();
        
        // If editing, populate fields
        if (employee != null) {
            populateFields(employee);
        }
        
        // Dialog settings
        setSize(500, 650);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    // ============================================================
    // INITIALIZATION
    // ============================================================
    
    /**
     * Creates and initializes all form components
     */
    private void initializeComponents() {
        // Employee Type dropdown
        typeComboBox = new JComboBox<>(new String[]{
            "Regular Employee", "Manager"
        });
        ProfessionalTheme.styleComboBox(typeComboBox);
        
        // Basic fields
        idField = createStyledTextField();
        firstNameField = createStyledTextField();
        lastNameField = createStyledTextField();
        emailField = createStyledTextField();
        phoneField = createStyledTextField();
        departmentField = createStyledTextField();
        positionField = createStyledTextField();
        ratingField = createStyledTextField();
        salaryField = createStyledTextField();
        hireDateField = createStyledTextField();
        
        // Manager-specific fields
        teamSizeField = createStyledTextField();
        levelComboBox = new JComboBox<>(new String[]{
            "Junior", "Mid", "Senior", "Executive"
        });
        ProfessionalTheme.styleComboBox(levelComboBox);
        bonusField = createStyledTextField();
        
        // Manager panel (initially hidden for regular employees)
        managerPanel = new JPanel(new GridBagLayout());
        managerPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        managerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(ProfessionalTheme.ACCENT_PRIMARY),
            "Manager Information",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            ProfessionalTheme.FONT_BOLD,
            ProfessionalTheme.ACCENT_PRIMARY
        ));
    }
    
    /**
     * Helper method to create styled text field
     */
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        ProfessionalTheme.styleTextField(field);
        return field;
    }
    
    // ============================================================
    // LAYOUT SETUP
    // ============================================================
    
    /**
     * Arranges all components in the dialog
     */
    private void setupLayout() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // Employee Type
        addFormRow(formPanel, gbc, row++, "Employee Type:", typeComboBox);
        
        // ID
        addFormRow(formPanel, gbc, row++, "Employee ID:*", idField);
        
        // First Name
        addFormRow(formPanel, gbc, row++, "First Name:*", firstNameField);
        
        // Last Name
        addFormRow(formPanel, gbc, row++, "Last Name:*", lastNameField);
        
        // Email
        addFormRow(formPanel, gbc, row++, "Email:", emailField);
        
        // Phone
        addFormRow(formPanel, gbc, row++, "Phone:", phoneField);
        
        // Department
        addFormRow(formPanel, gbc, row++, "Department:*", departmentField);
        
        // Position
        addFormRow(formPanel, gbc, row++, "Position:*", positionField);
        
        // Performance Rating
        addFormRow(formPanel, gbc, row++, "Rating (0-100):*", ratingField);
        
        // Salary
        addFormRow(formPanel, gbc, row++, "Monthly Salary:*", salaryField);
        
        // Hire Date
        addFormRow(formPanel, gbc, row++, "Hire Date (YYYY-MM-DD):*", hireDateField);
        
        // Setup manager panel
        setupManagerPanel();
        
        // Add manager panel
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(managerPanel, gbc);
        
        // Scroll pane for form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        ProfessionalTheme.styleScrollPane(scrollPane);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(ProfessionalTheme.BACKGROUND_DARK);
        
        JButton saveButton = new JButton("Save");
        ProfessionalTheme.styleButton(saveButton, "success");
        
        JButton cancelButton = new JButton("Cancel");
        ProfessionalTheme.styleButton(cancelButton, "secondary");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        saveButton.addActionListener(e -> saveEmployee());
        cancelButton.addActionListener(e -> dispose());
        
        setContentPane(mainPanel);
    }
    
    /**
     * Adds a label-field row to the form panel
     */
    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, 
                           String labelText, JComponent field) {
        // Label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        
        JLabel label = new JLabel(labelText);
        label.setFont(ProfessionalTheme.FONT_DEFAULT);
        label.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        panel.add(label, gbc);
        
        // Field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
    
    /**
     * Sets up the manager-specific fields panel
     */
    private void setupManagerPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        int row = 0;
        
        // Team Size
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel teamLabel = new JLabel("Team Size:");
        teamLabel.setFont(ProfessionalTheme.FONT_DEFAULT);
        teamLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        managerPanel.add(teamLabel, gbc);
        
        gbc.gridx = 1;
        managerPanel.add(teamSizeField, gbc);
        
        // Management Level
        gbc.gridx = 0;
        gbc.gridy = ++row;
        JLabel levelLabel = new JLabel("Management Level:");
        levelLabel.setFont(ProfessionalTheme.FONT_DEFAULT);
        levelLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        managerPanel.add(levelLabel, gbc);
        
        gbc.gridx = 1;
        managerPanel.add(levelComboBox, gbc);
        
        // Bonus Percentage
        gbc.gridx = 0;
        gbc.gridy = ++row;
        JLabel bonusLabel = new JLabel("Bonus %:");
        bonusLabel.setFont(ProfessionalTheme.FONT_DEFAULT);
        bonusLabel.setForeground(ProfessionalTheme.FOREGROUND_PRIMARY);
        managerPanel.add(bonusLabel, gbc);
        
        gbc.gridx = 1;
        managerPanel.add(bonusField, gbc);
        
        // Initially hide manager panel if adding regular employee
        if (existingEmployee == null || !(existingEmployee instanceof Manager)) {
            managerPanel.setVisible(false);
        }
    }
    
    // ============================================================
    // EVENT HANDLERS
    // ============================================================
    
    /**
     * Sets up event handlers for interactive components
     */
    private void setupEventHandlers() {
        // Type combo box - show/hide manager panel
        typeComboBox.addActionListener(e -> {
            boolean isManager = typeComboBox.getSelectedIndex() == 1;
            managerPanel.setVisible(isManager);
            
            // If editing and changing type, clear manager fields
            if (existingEmployee != null && isManager != (existingEmployee instanceof Manager)) {
                teamSizeField.setText("");
                levelComboBox.setSelectedIndex(0);
                bonusField.setText("");
            }
            
            // Repack to adjust size
            pack();
        });
    }
    
    // ============================================================
    // POPULATE FIELDS (Edit Mode)
    // ============================================================
    
    /**
     * Populates form fields with existing employee data
     * @param employee Employee to edit
     */
    private void populateFields(Employee employee) {
        // Set type
        boolean isManager = employee instanceof Manager;
        typeComboBox.setSelectedIndex(isManager ? 1 : 0);
        
        // Basic fields
        idField.setText(employee.getId());
        idField.setEditable(false);  // Can't change ID
        idField.setBackground(ProfessionalTheme.BACKGROUND_MEDIUM);
        
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        emailField.setText(employee.getEmail());
        phoneField.setText(employee.getPhone());
        departmentField.setText(employee.getDepartment());
        positionField.setText(employee.getPosition());
        ratingField.setText(String.valueOf(employee.getPerformanceRating()));
        salaryField.setText(String.valueOf(employee.getSalary()));
        hireDateField.setText(employee.getHireDate());
        
        // Manager fields
        if (isManager) {
            Manager manager = (Manager) employee;
            teamSizeField.setText(String.valueOf(manager.getTeamSize()));
            levelComboBox.setSelectedItem(manager.getManagementLevel());
            bonusField.setText(String.valueOf(manager.getBonusPercentage()));
            managerPanel.setVisible(true);
        }
    }
    
    // ============================================================
    // SAVE OPERATION
    // ============================================================
    
    /**
     * Validates input and saves the employee
     */
    private void saveEmployee() {
        // Validate required fields
        if (!validateFields()) {
            return;
        }
        
        try {
            // Get values from fields
            String id = idField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String department = departmentField.getText().trim();
            String position = positionField.getText().trim();
            double rating = Double.parseDouble(ratingField.getText().trim());
            double salary = Double.parseDouble(salaryField.getText().trim());
            String hireDate = hireDateField.getText().trim();
            
            Employee employee;
            
            // Check if creating manager
            if (typeComboBox.getSelectedIndex() == 1) {
                // Manager fields
                int teamSize = Integer.parseInt(teamSizeField.getText().trim());
                String level = (String) levelComboBox.getSelectedItem();
                double bonus = Double.parseDouble(bonusField.getText().trim());
                
                // Create manager using factory
                employee = EmployeeFactory.createEmployee(
                    EmployeeFactory.TYPE_MANAGER,
                    id, firstName, lastName, email, phone,
                    department, position, rating, salary, hireDate,
                    teamSize, level, bonus
                );
            } else {
                // Create regular employee using factory
                employee = EmployeeFactory.createEmployee(
                    EmployeeFactory.TYPE_REGULAR,
                    id, firstName, lastName, email, phone,
                    department, position, rating, salary, hireDate
                );
            }
            
            // Save to data manager
            if (existingEmployee != null) {
                // Update existing
                dataManager.updateEmployee(employee);
            } else {
                // Add new
                if (!dataManager.addEmployee(employee)) {
                    JOptionPane.showMessageDialog(this,
                        "Employee ID already exists!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            saved = true;
            dispose();  // Close dialog
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers for rating, salary, team size, and bonus.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Validates all required fields
     * @return true if all fields are valid
     */
    private boolean validateFields() {
        StringBuilder errors = new StringBuilder();
        
        // Check required fields
        if (idField.getText().trim().isEmpty()) {
            errors.append("- Employee ID is required\n");
        }
        if (firstNameField.getText().trim().isEmpty()) {
            errors.append("- First Name is required\n");
        }
        if (lastNameField.getText().trim().isEmpty()) {
            errors.append("- Last Name is required\n");
        }
        if (departmentField.getText().trim().isEmpty()) {
            errors.append("- Department is required\n");
        }
        if (positionField.getText().trim().isEmpty()) {
            errors.append("- Position is required\n");
        }
        if (ratingField.getText().trim().isEmpty()) {
            errors.append("- Performance Rating is required\n");
        }
        if (salaryField.getText().trim().isEmpty()) {
            errors.append("- Salary is required\n");
        }
        if (hireDateField.getText().trim().isEmpty()) {
            errors.append("- Hire Date is required\n");
        }
        
        // Manager fields validation
        if (typeComboBox.getSelectedIndex() == 1) {
            if (teamSizeField.getText().trim().isEmpty()) {
                errors.append("- Team Size is required for managers\n");
            }
            if (bonusField.getText().trim().isEmpty()) {
                errors.append("- Bonus Percentage is required for managers\n");
            }
        }
        
        // Validate rating range
        try {
            double rating = Double.parseDouble(ratingField.getText().trim());
            if (rating < 0 || rating > 100) {
                errors.append("- Rating must be between 0 and 100\n");
            }
        } catch (NumberFormatException e) {
            errors.append("- Rating must be a valid number\n");
        }
        
        // Show errors if any
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this,
                "Please fix the following errors:\n\n" + errors.toString(),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    // ============================================================
    // PUBLIC METHODS
    // ============================================================
    
    /**
     * Checks if employee was saved successfully
     * @return true if saved
     */
    public boolean isSaved() {
        return saved;
    }
}
