package com.mycompany.group4_css122p.gui;

import com.mycompany.group4_css122p.data.EmployeeDataManager;
import com.mycompany.group4_css122p.gui.theme.DarkTheme;
import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * StatisticsDashboard.java - Second GUI Application
 * 
 * This is the SECOND GUI that connects to the MainGUI. It provides
 * comprehensive statistics, charts, and analytics about employees.
 * 
 * CONNECTION TO MAINGUI:
 * - Opened from MainGUI via "Statistics Dashboard" button
 * - Shares the same EmployeeDataManager instance
 * - Updates in real-time when data changes
 * 
 * FEATURES:
 * - Visual statistics cards with key metrics
 * - Performance distribution chart
 * - Department breakdown
 * - Top performers list
 * - Payroll analysis
 * - Manager statistics
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class StatisticsDashboard extends JFrame {
    
    // ============================================================
    // INSTANCE VARIABLES
    // ============================================================
    
    /** Reference to main GUI (parent) */
    private MainGUI mainGUI;
    
    /** Data manager shared with main GUI */
    private EmployeeDataManager dataManager;
    
    // Statistics labels
    private JLabel totalEmpValue;
    private JLabel totalMgrValue;
    private JLabel totalDeptValue;
    private JLabel avgRatingValue;
    private JLabel eeCountValue;
    private JLabel meCountValue;
    private JLabel pipCountValue;
    private JLabel totalPayrollValue;
    private JLabel avgSalaryValue;
    private JLabel highestPaidValue;
    
    // Charts panel
    private JPanel performanceChartPanel;
    private JPanel departmentChartPanel;
    
    // Top performers text area
    private JTextArea topPerformersArea;
    
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    
    /**
     * Creates the Statistics Dashboard
     * 
     * @param parent MainGUI reference
     * @param dataManager Shared data manager
     */
    public StatisticsDashboard(MainGUI parent, EmployeeDataManager dataManager) {
        this.mainGUI = parent;
        this.dataManager = dataManager;
        
        // Initialize components
        initializeComponents();
        
        // Setup layout
        setupLayout();
        
        // Load initial statistics
        refreshStatistics();
        
        // Window settings
        setTitle("Employee Statistics Dashboard");
        setSize(900, 700);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // ============================================================
    // INITIALIZATION
    // ============================================================
    
    /**
     * Creates and initializes all components
     */
    private void initializeComponents() {
        // Statistics value labels
        totalEmpValue = createValueLabel("0");
        totalMgrValue = createValueLabel("0");
        totalDeptValue = createValueLabel("0");
        avgRatingValue = createValueLabel("0.0");
        eeCountValue = createValueLabel("0");
        meCountValue = createValueLabel("0");
        pipCountValue = createValueLabel("0");
        totalPayrollValue = createValueLabel("$0");
        avgSalaryValue = createValueLabel("$0");
        highestPaidValue = createValueLabel("-");
        
        // Chart panels
        performanceChartPanel = new JPanel();
        performanceChartPanel.setBackground(DarkTheme.BACKGROUND_DARK);
        
        departmentChartPanel = new JPanel();
        departmentChartPanel.setBackground(DarkTheme.BACKGROUND_DARK);
        
        // Top performers area
        topPerformersArea = new JTextArea(10, 30);
        topPerformersArea.setFont(DarkTheme.FONT_MONO);
        topPerformersArea.setBackground(DarkTheme.BACKGROUND_MEDIUM);
        topPerformersArea.setForeground(DarkTheme.FOREGROUND_PRIMARY);
        topPerformersArea.setEditable(false);
        topPerformersArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Creates a styled value label
     */
    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label.setForeground(DarkTheme.ACCENT_PRIMARY);
        return label;
    }
    
    // ============================================================
    // LAYOUT SETUP
    // ============================================================
    
    /**
     * Arranges all components in the window
     */
    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(15, 15));
        contentPane.setBackground(DarkTheme.BACKGROUND_DARKEST);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // ============================================================
        // HEADER PANEL
        // ============================================================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DarkTheme.BACKGROUND_DARKEST);
        
        JLabel titleLabel = new JLabel("Statistics Dashboard");
        titleLabel.setFont(DarkTheme.FONT_TITLE);
        titleLabel.setForeground(DarkTheme.FOREGROUND_PRIMARY);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Refresh button
        JButton refreshBtn = new JButton("Refresh Data");
        DarkTheme.styleButton(refreshBtn, "primary");
        refreshBtn.addActionListener(e -> refreshStatistics());
        headerPanel.add(refreshBtn, BorderLayout.EAST);
        
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        // ============================================================
        // MAIN CONTENT PANEL (Split into left and right)
        // ============================================================
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBackground(DarkTheme.BACKGROUND_DARKEST);
        splitPane.setDividerLocation(500);
        splitPane.setDividerSize(5);
        splitPane.setBorder(null);
        
        // Left panel - Key metrics and charts
        JPanel leftPanel = createLeftPanel();
        splitPane.setLeftComponent(leftPanel);
        
        // Right panel - Detailed analysis
        JPanel rightPanel = createRightPanel();
        splitPane.setRightComponent(rightPanel);
        
        contentPane.add(splitPane, BorderLayout.CENTER);
        
        // ============================================================
        // FOOTER PANEL
        // ============================================================
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(DarkTheme.BACKGROUND_DARK);
        footerPanel.setBorder(BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR));
        
        JLabel footerLabel = new JLabel("Connected to Employee Management System");
        footerLabel.setFont(DarkTheme.FONT_SMALL);
        footerLabel.setForeground(DarkTheme.FOREGROUND_MUTED);
        footerPanel.add(footerLabel);
        
        contentPane.add(footerPanel, BorderLayout.SOUTH);
        
        setContentPane(contentPane);
    }
    
    /**
     * Creates the left panel with key metrics and charts
     */
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DarkTheme.BACKGROUND_DARKEST);
        
        // Key Metrics Section
        JPanel metricsPanel = createMetricsPanel();
        panel.add(metricsPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // Performance Chart Section
        JPanel perfPanel = createPerformanceChartPanel();
        panel.add(perfPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // Department Chart Section
        JPanel deptPanel = createDepartmentChartPanel();
        panel.add(deptPanel);
        
        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        DarkTheme.styleScrollPane(scrollPane);
        scrollPane.setBorder(null);
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(DarkTheme.BACKGROUND_DARKEST);
        wrapper.add(scrollPane, BorderLayout.CENTER);
        
        return wrapper;
    }
    
    /**
     * Creates the key metrics panel
     */
    private JPanel createMetricsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setBackground(DarkTheme.BACKGROUND_DARKEST);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Key Metrics",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        
        panel.add(createMetricCard("Total Employees", totalEmpValue, DarkTheme.INFO));
        panel.add(createMetricCard("Total Managers", totalMgrValue, DarkTheme.WARNING));
        panel.add(createMetricCard("Departments", totalDeptValue, DarkTheme.SUCCESS));
        panel.add(createMetricCard("Average Rating", avgRatingValue, DarkTheme.ACCENT_PRIMARY));
        panel.add(createMetricCard("Total Payroll", totalPayrollValue, DarkTheme.SUCCESS));
        panel.add(createMetricCard("Average Salary", avgSalaryValue, DarkTheme.INFO));
        
        return panel;
    }
    
    /**
     * Creates a single metric card
     */
    private JPanel createMetricCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(DarkTheme.BACKGROUND_DARK);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, accentColor),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(DarkTheme.FONT_DEFAULT);
        titleLabel.setForeground(DarkTheme.FOREGROUND_MUTED);
        card.add(titleLabel, BorderLayout.NORTH);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Creates the performance distribution chart panel
     */
    private JPanel createPerformanceChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DarkTheme.BACKGROUND_DARK);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Performance Distribution",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        panel.setPreferredSize(new Dimension(0, 180));
        
        // Chart container
        performanceChartPanel.setLayout(new GridLayout(1, 3, 10, 0));
        performanceChartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(performanceChartPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the department breakdown chart panel
     */
    private JPanel createDepartmentChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DarkTheme.BACKGROUND_DARK);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Department Breakdown",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        panel.setPreferredSize(new Dimension(0, 200));
        
        // Chart container
        departmentChartPanel.setLayout(new BoxLayout(departmentChartPanel, BoxLayout.Y_AXIS));
        departmentChartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(departmentChartPanel);
        DarkTheme.styleScrollPane(scrollPane);
        scrollPane.setBorder(null);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the right panel with detailed analysis
     */
    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DarkTheme.BACKGROUND_DARKEST);
        
        // Top Performers Section
        JPanel topPerfPanel = new JPanel(new BorderLayout());
        topPerfPanel.setBackground(DarkTheme.BACKGROUND_DARK);
        topPerfPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Top Performers (EE)",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        topPerfPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        JScrollPane topPerfScroll = new JScrollPane(topPerformersArea);
        DarkTheme.styleScrollPane(topPerfScroll);
        topPerfScroll.setBorder(null);
        topPerfPanel.add(topPerfScroll, BorderLayout.CENTER);
        
        panel.add(topPerfPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // Manager Statistics Section
        JPanel mgrStatsPanel = createManagerStatsPanel();
        panel.add(mgrStatsPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // Highest Paid Section
        JPanel highestPaidPanel = createHighestPaidPanel();
        panel.add(highestPaidPanel);
        
        // Wrap in scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        DarkTheme.styleScrollPane(scrollPane);
        scrollPane.setBorder(null);
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(DarkTheme.BACKGROUND_DARKEST);
        wrapper.add(scrollPane, BorderLayout.CENTER);
        
        return wrapper;
    }
    
    /**
     * Creates the manager statistics panel
     */
    private JPanel createManagerStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(DarkTheme.BACKGROUND_DARK);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Performance Status Count",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        panel.add(createStatusCard("EE (Exceeds)", eeCountValue, DarkTheme.SUCCESS));
        panel.add(createStatusCard("ME (Meets)", meCountValue, DarkTheme.WARNING));
        panel.add(createStatusCard("PIP (Improvement)", pipCountValue, DarkTheme.DANGER));
        panel.add(createStatusCard("Highest Paid", highestPaidValue, DarkTheme.ACCENT_PRIMARY));
        
        return panel;
    }
    
    /**
     * Creates a status count card
     */
    private JPanel createStatusCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(DarkTheme.BACKGROUND_MEDIUM);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(DarkTheme.FONT_SMALL);
        titleLabel.setForeground(DarkTheme.FOREGROUND_MUTED);
        card.add(titleLabel, BorderLayout.NORTH);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Creates the highest paid employee panel
     */
    private JPanel createHighestPaidPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DarkTheme.BACKGROUND_DARK);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DarkTheme.BORDER_COLOR),
            "Highest Paid Employee",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            DarkTheme.FONT_BOLD,
            DarkTheme.FOREGROUND_SECONDARY
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.setPreferredSize(new Dimension(0, 100));
        
        return panel;
    }
    
    // ============================================================
    // STATISTICS REFRESH
    // ============================================================
    
    /**
     * Refreshes all statistics with current data
     */
    public void refreshStatistics() {
        // Get statistics from data manager
        Map<String, Object> stats = dataManager.getStatistics();
        
        // Update key metrics
        totalEmpValue.setText(String.valueOf(stats.get("totalEmployees")));
        totalMgrValue.setText(String.valueOf(stats.get("totalManagers")));
        totalDeptValue.setText(String.valueOf(stats.get("totalDepartments")));
        avgRatingValue.setText(String.format("%.1f", stats.get("averageRating")));
        
        // Update performance counts
        eeCountValue.setText(String.valueOf(stats.get("eeCount")));
        meCountValue.setText(String.valueOf(stats.get("meCount")));
        pipCountValue.setText(String.valueOf(stats.get("pipCount")));
        
        // Update financial metrics
        totalPayrollValue.setText(String.format("$%,.0f", stats.get("totalMonthlyPayroll")));
        avgSalaryValue.setText(String.format("$%,.0f", stats.get("averageSalary")));
        highestPaidValue.setText((String) stats.get("highestPaid"));
        
        // Update charts
        updatePerformanceChart(
            (int) stats.get("eeCount"),
            (int) stats.get("meCount"),
            (int) stats.get("pipCount"),
            (int) stats.get("totalEmployees")
        );
        
        updateDepartmentChart();
        updateTopPerformers();
    }
    
    /**
     * Updates the performance distribution chart
     */
    private void updatePerformanceChart(int eeCount, int meCount, int pipCount, int total) {
        performanceChartPanel.removeAll();
        
        if (total == 0) {
            JLabel noDataLabel = new JLabel("No data available", SwingConstants.CENTER);
            noDataLabel.setForeground(DarkTheme.FOREGROUND_MUTED);
            performanceChartPanel.add(noDataLabel);
            performanceChartPanel.revalidate();
            performanceChartPanel.repaint();
            return;
        }
        
        // Create bar chart panels
        performanceChartPanel.add(createBar("EE (Exceeds)", eeCount, total, DarkTheme.SUCCESS));
        performanceChartPanel.add(createBar("ME (Meets)", meCount, total, DarkTheme.WARNING));
        performanceChartPanel.add(createBar("PIP (Improvement)", pipCount, total, DarkTheme.DANGER));
        
        performanceChartPanel.revalidate();
        performanceChartPanel.repaint();
    }
    
    /**
     * Creates a single bar for the chart
     */
    private JPanel createBar(String label, int value, int total, Color color) {
        JPanel barPanel = new JPanel(new BorderLayout());
        barPanel.setBackground(DarkTheme.BACKGROUND_DARK);
        
        // Calculate percentage
        double percentage = total > 0 ? (value * 100.0 / total) : 0;
        
        // Label at top
        JLabel titleLabel = new JLabel(label + " (" + value + ")");
        titleLabel.setFont(DarkTheme.FONT_SMALL);
        titleLabel.setForeground(DarkTheme.FOREGROUND_SECONDARY);
        barPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Bar container
        JPanel barContainer = new JPanel(new BorderLayout());
        barContainer.setBackground(DarkTheme.BACKGROUND_MEDIUM);
        barContainer.setPreferredSize(new Dimension(0, 80));
        
        // The actual bar
        JPanel bar = new JPanel();
        bar.setBackground(color);
        int barHeight = (int) (percentage * 0.8);  // Max 80% of container
        bar.setPreferredSize(new Dimension(0, Math.max(barHeight, 10)));
        
        barContainer.add(bar, BorderLayout.SOUTH);
        barPanel.add(barContainer, BorderLayout.CENTER);
        
        // Percentage label at bottom
        JLabel pctLabel = new JLabel(String.format("%.1f%%", percentage));
        pctLabel.setFont(DarkTheme.FONT_SMALL);
        pctLabel.setForeground(color);
        pctLabel.setHorizontalAlignment(SwingConstants.CENTER);
        barPanel.add(pctLabel, BorderLayout.SOUTH);
        
        return barPanel;
    }
    
    /**
     * Updates the department breakdown chart
     */
    private void updateDepartmentChart() {
        departmentChartPanel.removeAll();
        
        List<String> departments = dataManager.getDepartments();
        
        if (departments.isEmpty()) {
            JLabel noDataLabel = new JLabel("No department data available");
            noDataLabel.setForeground(DarkTheme.FOREGROUND_MUTED);
            departmentChartPanel.add(noDataLabel);
        } else {
            int totalEmployees = dataManager.getEmployeeCount();
            
            for (String dept : departments) {
                int count = dataManager.getEmployeesByDepartment(dept).size();
                double percentage = totalEmployees > 0 ? (count * 100.0 / totalEmployees) : 0;
                
                departmentChartPanel.add(createDepartmentBar(dept, count, percentage));
                departmentChartPanel.add(Box.createVerticalStrut(5));
            }
        }
        
        departmentChartPanel.revalidate();
        departmentChartPanel.repaint();
    }
    
    /**
     * Creates a department bar row
     */
    private JPanel createDepartmentBar(String dept, int count, double percentage) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(DarkTheme.BACKGROUND_DARK);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        // Department name
        JLabel nameLabel = new JLabel(dept);
        nameLabel.setFont(DarkTheme.FONT_SMALL);
        nameLabel.setForeground(DarkTheme.FOREGROUND_PRIMARY);
        nameLabel.setPreferredSize(new Dimension(100, 20));
        panel.add(nameLabel, BorderLayout.WEST);
        
        // Progress bar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue((int) percentage);
        progressBar.setStringPainted(true);
        progressBar.setString(count + " (" + String.format("%.1f", percentage) + "%)");
        progressBar.setBackground(DarkTheme.BACKGROUND_MEDIUM);
        progressBar.setForeground(DarkTheme.ACCENT_PRIMARY);
        progressBar.setBorderPainted(false);
        panel.add(progressBar, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Updates the top performers list
     */
    private void updateTopPerformers() {
        List<Employee> topPerformers = dataManager.getTopPerformers();
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s %-20s %-15s %-10s\n", 
            "ID", "Name", "Department", "Rating"));
        sb.append("------------------------------------------------\n");
        
        for (Employee emp : topPerformers) {
            sb.append(String.format("%-8s %-20s %-15s %-10.1f\n",
                emp.getId(),
                emp.getFullName(),
                emp.getDepartment(),
                emp.getPerformanceRating()));
        }
        
        if (topPerformers.isEmpty()) {
            sb.append("No top performers found.");
        }
        
        topPerformersArea.setText(sb.toString());
    }
    
    // ============================================================
    // PUBLIC METHODS
    // ============================================================
    
    /**
     * Called when this window is closed
     * Notifies main GUI that dashboard is closed
     */
    @Override
    public void dispose() {
        if (mainGUI != null) {
            // Could notify main GUI here if needed
        }
        super.dispose();
    }
}
