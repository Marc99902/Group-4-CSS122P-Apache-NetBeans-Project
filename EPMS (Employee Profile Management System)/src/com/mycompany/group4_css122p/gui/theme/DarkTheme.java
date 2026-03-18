package com.mycompany.group4_css122p.gui.theme;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * DarkTheme.java - Dark Theme Styling for GUI
 * 
 * This class provides consistent dark theme styling for all GUI components.
 * It uses a modern dark color scheme with accent colors for better UX.
 * 
 * COLOR SCHEME:
 * - Background: Dark grays (#1a1a2e, #16213e, #0f3460)
 * - Foreground: Light grays and whites
 * - Accents: Blue (#e94560) for primary actions
 * - Success: Green, Warning: Yellow, Danger: Red
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class DarkTheme {
    
    // ============================================================
    // COLOR PALETTE
    // ============================================================
    
    // Background Colors (Darkest to Lightest)
    public static final Color BACKGROUND_DARKEST = new Color(0x1a1a2e);  // #1a1a2e
    public static final Color BACKGROUND_DARK = new Color(0x16213e);     // #16213e
    public static final Color BACKGROUND_MEDIUM = new Color(0x0f3460);   // #0f3460
    public static final Color BACKGROUND_LIGHT = new Color(0x1f4068);    // #1f4068
    
    // Foreground Colors
    public static final Color FOREGROUND_PRIMARY = new Color(0xeeeeee);   // #eeeeee
    public static final Color FOREGROUND_SECONDARY = new Color(0xcccccc); // #cccccc
    public static final Color FOREGROUND_MUTED = new Color(0x888888);     // #888888
    
    // Accent Colors
    public static final Color ACCENT_PRIMARY = new Color(0xe94560);      // #e94560 - Pink/Red
    public static final Color ACCENT_SECONDARY = new Color(0x533483);    // #533483 - Purple
    
    // Status Colors
    public static final Color SUCCESS = new Color(0x28a745);   // Green
    public static final Color WARNING = new Color(0xffc107);   // Yellow
    public static final Color DANGER = new Color(0xdc3545);    // Red
    public static final Color INFO = new Color(0x17a2b8);      // Cyan
    
    // Border Color
    public static final Color BORDER_COLOR = new Color(0x2a2a4a);  // #2a2a4a
    
    // ============================================================
    // FONTS
    // ============================================================
    
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_DEFAULT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 13);
    
    // ============================================================
    // THEME APPLICATION
    // ============================================================
    
    /**
     * Applies the dark theme to the entire application
     * Should be called before creating any GUI components
     */
    public static void applyTheme() {
        try {
            // Set Look and Feel to system default (we'll customize components)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Override default colors
            UIManager.put("Panel.background", BACKGROUND_DARK);
            UIManager.put("OptionPane.background", BACKGROUND_DARK);
            UIManager.put("OptionPane.messageForeground", FOREGROUND_PRIMARY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ============================================================
    // COMPONENT STYLING METHODS
    // ============================================================
    
    /**
     * Styles a button with the dark theme
     * 
     * @param button Button to style
     * @param type Button type: "primary", "success", "danger", "warning", "secondary", "default"
     */
    public static void styleButton(JButton button, String type) {
        Color bgColor;
        Color fgColor = FOREGROUND_PRIMARY;
        
        switch (type.toLowerCase()) {
            case "primary":
                bgColor = ACCENT_PRIMARY;
                break;
            case "success":
                bgColor = SUCCESS;
                break;
            case "danger":
                bgColor = DANGER;
                break;
            case "warning":
                bgColor = WARNING;
                fgColor = Color.BLACK;
                break;
            case "secondary":
                bgColor = BACKGROUND_MEDIUM;
                break;
            default:
                bgColor = BACKGROUND_LIGHT;
                break;
        }
        
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(FONT_DEFAULT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 35));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
    
    /**
     * Styles a text field with the dark theme
     * @param textField Text field to style
     */
    public static void styleTextField(JTextField textField) {
        textField.setBackground(BACKGROUND_MEDIUM);
        textField.setForeground(FOREGROUND_PRIMARY);
        textField.setCaretColor(FOREGROUND_PRIMARY);
        textField.setFont(FONT_DEFAULT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        textField.setPreferredSize(new Dimension(200, 35));
    }
    
    /**
     * Styles a combo box with the dark theme
     * @param comboBox Combo box to style
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(BACKGROUND_MEDIUM);
        comboBox.setForeground(FOREGROUND_PRIMARY);
        comboBox.setFont(FONT_DEFAULT);
        comboBox.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        comboBox.setPreferredSize(new Dimension(180, 35));
        
        // Style the popup menu
        Object popup = comboBox.getUI().getAccessibleChild(comboBox, 0);
        if (popup instanceof JPopupMenu) {
            JPopupMenu popupMenu = (JPopupMenu) popup;
            popupMenu.setBackground(BACKGROUND_MEDIUM);
            popupMenu.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        }
    }
    
    /**
     * Styles a table with the dark theme
     * @param table Table to style
     */
    public static void styleTable(JTable table) {
        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(BACKGROUND_MEDIUM);
        header.setForeground(FOREGROUND_PRIMARY);
        header.setFont(FONT_BOLD);
        header.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        header.setPreferredSize(new Dimension(0, 40));
        
        // Table body styling
        table.setBackground(BACKGROUND_DARK);
        table.setForeground(FOREGROUND_PRIMARY);
        table.setFont(FONT_DEFAULT);
        table.setGridColor(BORDER_COLOR);
        table.setSelectionBackground(ACCENT_PRIMARY);
        table.setSelectionForeground(FOREGROUND_PRIMARY);
        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        
        // Alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? BACKGROUND_DARK : BACKGROUND_MEDIUM);
                    c.setForeground(FOREGROUND_PRIMARY);
                }
                
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        });
    }
    
    /**
     * Styles a scroll pane with the dark theme
     * @param scrollPane Scroll pane to style
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBackground(BACKGROUND_DARK);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        
        // Style the scrollbar
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        
        verticalBar.setBackground(BACKGROUND_DARK);
        horizontalBar.setBackground(BACKGROUND_DARK);
        
        // Custom scrollbar UI could be added here
    }
    
    /**
     * Styles a text area with the dark theme
     * @param textArea Text area to style
     */
    public static void styleTextArea(JTextArea textArea) {
        textArea.setBackground(BACKGROUND_MEDIUM);
        textArea.setForeground(FOREGROUND_PRIMARY);
        textArea.setCaretColor(FOREGROUND_PRIMARY);
        textArea.setFont(FONT_DEFAULT);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Styles a label as a title
     * @param label Label to style
     */
    public static void styleTitleLabel(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(FOREGROUND_PRIMARY);
    }
    
    /**
     * Styles a label as a header
     * @param label Label to style
     */
    public static void styleHeaderLabel(JLabel label) {
        label.setFont(FONT_HEADER);
        label.setForeground(FOREGROUND_PRIMARY);
    }
    
    /**
     * Creates a styled panel with border
     * @param title Panel title
     * @return Styled JPanel
     */
    public static JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_DARK);
        
        if (title != null && !title.isEmpty()) {
            panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                FONT_BOLD,
                FOREGROUND_SECONDARY
            ));
        } else {
            panel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        }
        
        return panel;
    }
    
    /**
     * Creates a separator with dark theme styling
     * @return Styled JSeparator
     */
    public static JSeparator createStyledSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(BORDER_COLOR);
        separator.setBackground(BORDER_COLOR);
        return separator;
    }
}
