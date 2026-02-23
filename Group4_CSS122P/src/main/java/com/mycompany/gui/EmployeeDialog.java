package com.mycompany.group4_css122p.gui;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.factory.EmployeeFactory;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;
import javax.swing.*;
import java.awt.*;

/**
 * Dialog for adding/editing employees
 * @author Kage
 */
public class EmployeeDialog extends JDialog {
    private EmployeeDataManager dataManager;
    private Employee employee;
    private boolean isEditMode;
    
    // Form fields
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
    private JComboBox<String> typeCombo;
    
    // Manager-specific fields
    private JTextField teamSizeField;
    private JTextField managementLevelField;
    private JTextField bonusField;
    private JPanel managerPanel;
    
    public EmployeeDialog(JFrame parent, EmployeeDataManager dataManager, Employee employee) {
        super(parent, employee == null ? "Add Employee" : "Edit Employee", true);
        this.dataManager = dataManager;
        this.employee = employee;
        this.isEditMode = (employee != null);
        
        initializeComponents();
        setupLayout();
        
        if (isEditMode) {
            populateFields();
            idField.setEditable(false);
        }
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void initializeComponents() {
        idField = new JTextField(15);
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        emailField = new JTextField(15);
        phoneField = new JTextField(15);
        departmentField = new JTextField(15);
        positionField = new JTextField(15);
        ratingField = new JTextField(15);
        salaryField = new JTextField(15);
        hireDateField = new JTextField(15);
        
        String[] types = {"REGULAR", "MANAGER"};
        typeCombo = new JComboBox<>(types);
        typeCombo.setEnabled(!isEditMode);
        typeCombo.addActionListener(e -> toggleManagerFields());
        
        // Manager fields
        teamSizeField = new JTextField(15);
        managementLevelField = new JTextField(15);
        bonusField = new JTextField(15);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // Employee Type
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Employee Type:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(typeCombo, gbc);
        row++;
        
        // ID
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Employee ID:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);
        row++;
        
        // First Name
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("First Name:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);
        row++;
        
        // Last Name
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Last Name:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);
        row++;
        
        // Email
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Email:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        row++;
        
        // Phone
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Phone:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        row++;
        
        // Department
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Department:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(departmentField, gbc);
        row++;
        
        // Position
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Position:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(positionField, gbc);
        row++;
        
        // Performance Rating
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Performance Rating (0-100):*"), gbc);
        gbc.gridx = 1;
        formPanel.add(ratingField, gbc);
        row++;
        
        // Salary
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Salary:*"), gbc);
        gbc.gridx = 1;
        formPanel.add(salaryField, gbc);
        row++;
        
        // Hire Date
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Hire Date (YYYY-MM-DD):*"), gbc);
        gbc.gridx = 1;
        formPanel.add(hireDateField, gbc);
        row++;
        
        // Manager-specific fields panel
        managerPanel = new JPanel(new GridBagLayout());
        managerPanel.setBorder(BorderFactory.createTitledBorder("Manager Details"));
        managerPanel.setVisible(false);
        
        GridBagConstraints mgbc = new GridBagConstraints();
        mgbc.insets = new Insets(5, 5, 5, 5);
        mgbc.anchor = GridBagConstraints.WEST;
        
        mgbc.gridx = 0; mgbc.gridy = 0;
        managerPanel.add(new JLabel("Team Size:*"), mgbc);
        mgbc.gridx = 1;
        managerPanel.add(teamSizeField, mgbc);
        
        mgbc.gridx = 0; mgbc.gridy = 1;
        managerPanel.add(new JLabel("Management Level:*"), mgbc);
        mgbc.gridx = 1;
        managerPanel.add(managementLevelField, mgbc);
        
        mgbc.gridx = 0; mgbc.gridy = 2;
        managerPanel.add(new JLabel("Bonus %:*"), mgbc);
        mgbc.gridx = 1;
        managerPanel.add(bonusField, mgbc);
        
        gbc.gridx = 0; gbc.gridy = row;
        gbc.gridwidth = 2;
        formPanel.add(managerPanel, gbc);
        
        add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> saveEmployee());
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void toggleManagerFields() {
        boolean isManager = typeCombo.getSelectedItem().equals("MANAGER");
        managerPanel.setVisible(isManager);
        pack();
    }
    
    private void populateFields() {
        idField.setText(employee.getId());
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        emailField.setText(employee.getEmail());
        phoneField.setText(employee.getPhone());
        departmentField.setText(employee.getDepartment());
        positionField.setText(employee.getPosition());
        ratingField.setText(String.valueOf(employee.getPerformanceRating()));
        salaryField.setText(String.valueOf(employee.getSalary()));
        hireDateField.setText(employee.getHireDate());
        
        if (employee instanceof Manager) {
            typeCombo.setSelectedItem("MANAGER");
            Manager mgr = (Manager) employee;
            teamSizeField.setText(String.valueOf(mgr.getTeamSize()));
            managementLevelField.setText(mgr.getManagementLevel());
            bonusField.setText(String.valueOf(mgr.getBonusPercentage()));
            managerPanel.setVisible(true);
        } else {
            typeCombo.setSelectedItem("REGULAR");
            managerPanel.setVisible(false);
        }
    }
    
    private void saveEmployee() {
        // Validate required fields
        if (!validateFields()) {
            return;
        }
        
        try {
            String type = (String) typeCombo.getSelectedItem();
            String id = idField.getText().trim();
            
            // Check for duplicate ID in add mode
            if (!isEditMode && dataManager.findEmployeeById(id) != null) {
                JOptionPane.showMessageDialog(this, 
                    "Employee ID already exists!", 
                    "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Employee newEmployee;
            
            if (type.equals("MANAGER")) {
                newEmployee = EmployeeFactory.createEmployee(
                    EmployeeFactory.TYPE_MANAGER,
                    id,
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    departmentField.getText().trim(),
                    positionField.getText().trim(),
                    Double.parseDouble(ratingField.getText().trim()),
                    Double.parseDouble(salaryField.getText().trim()),
                    hireDateField.getText().trim(),
                    Integer.parseInt(teamSizeField.getText().trim()),
                    managementLevelField.getText().trim(),
                    Double.parseDouble(bonusField.getText().trim())
                );
            } else {
                newEmployee = EmployeeFactory.createEmployee(
                    EmployeeFactory.TYPE_REGULAR,
                    id,
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    departmentField.getText().trim(),
                    positionField.getText().trim(),
                    Double.parseDouble(ratingField.getText().trim()),
                    Double.parseDouble(salaryField.getText().trim()),
                    hireDateField.getText().trim()
                );
            }
            
            if (isEditMode) {
                dataManager.updateEmployee(newEmployee);
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } else {
                dataManager.addEmployee(newEmployee);
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            }
            
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for rating, salary, and other numeric fields.", 
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validateFields() {
        if (idField.getText().trim().isEmpty() ||
            firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            departmentField.getText().trim().isEmpty() ||
            positionField.getText().trim().isEmpty() ||
            ratingField.getText().trim().isEmpty() ||
            salaryField.getText().trim().isEmpty() ||
            hireDateField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Please fill in all required fields.", 
                "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validate performance rating
        try {
            double rating = Double.parseDouble(ratingField.getText().trim());
            if (rating < 0 || rating > 100) {
                JOptionPane.showMessageDialog(this, 
                    "Performance rating must be between 0 and 100.", 
                    "Invalid Rating", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Performance rating must be a valid number.", 
                "Invalid Rating", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validate manager fields if manager type
        if (typeCombo.getSelectedItem().equals("MANAGER")) {
            if (teamSizeField.getText().trim().isEmpty() ||
                managementLevelField.getText().trim().isEmpty() ||
                bonusField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all manager-specific fields.", 
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
}