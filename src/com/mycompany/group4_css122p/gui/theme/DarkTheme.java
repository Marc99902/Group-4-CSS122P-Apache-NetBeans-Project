package com.mycompany.group4_css122p.gui.theme;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * DarkTheme.java - Dark Mode Theme Configuration
 * 
 * This class provides centralized dark theme styling for the entire application.
 * It defines color schemes, fonts, and utility methods to apply dark styling
 * to Swing components.
 * 
 * COLOR SCHEME:
 * - Background: Dark gray (#1E1E1E, #2D2D2D, #3C3C3C)
 * - Foreground: Light gray/white (#FFFFFF, #E0E0E0)
 * - Accent: Blue (#007ACC, #0098FF)
 * - Success: Green (#28A745)
 * - Warning: Yellow (#FFC107)
 * - Danger: Red (#DC3545)
 * 
 * USAGE:
 * Call DarkTheme.applyTheme() before creating any GUI components,
 * or use individual styling methods for specific components.
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class DarkTheme {
    
    // ============================================================
    // COLOR PALETTE - Dark Theme Colors
    // ============================================================
    
    // Background Colors (from darkest to lightest)
    public static final Color BACKGROUND_DARKEST = new Color(0x1E1E1E);  // #1E1E1E
    public static final Color BACKGROUND_DARK = new Color(0x2D2D2D);     // #2D2D2D
    public static final Color BACKGROUND_MEDIUM = new Color(0x3C3C3C);   // #3C3C3C
    public static final Color BACKGROUND_LIGHT = new Color(0x4A4A4A);    // #4A4A4A
    public static final Color BACKGROUND_LIGHTER = new Color(0x555555);  // #555555
    
    // Foreground Colors (text)
    public static final Color FOREGROUND_PRIMARY = new Color(0xFFFFFF);  // #FFFFFF - White
    public static final Color FOREGROUND_SECONDARY = new Color(0xE0E0E0); // #E0E0E0 - Light gray
    public static final Color FOREGROUND_MUTED = new Color(0xAAAAAA);    // #AAAAAA - Muted gray
    
    // Accent Colors
    public static final Color ACCENT_PRIMARY = new Color(0x007ACC);      // #007ACC - Blue
    public static final Color ACCENT_HOVER = new Color(0x0098FF);        // #0098FF - Light blue
    public static final Color ACCENT_PRESSED = new Color(0x005A9E);      // #005A9E - Dark blue
    
    // Status Colors
    public static final Color SUCCESS = new Color(0x28A745);             // #28A745 - Green
    public static final Color SUCCESS_LIGHT = new Color(0x34CE57);       // #34CE57 - Light green
    public static final Color WARNING = new Color(0xFFC107);             // #FFC107 - Yellow
    public static final Color DANGER = new Color(0xDC3545);              // #DC3545 - Red
    public static final Color DANGER_LIGHT = new Color(0xE4606D);        // #E4606D - Light red
    public static final Color INFO = new Color(0x17A2B8);                // #17A2B8 - Cyan
    
    // Table Specific Colors
    public static final Color TABLE_GRID = new Color(0x555555);          // Grid lines
    public static final Color TABLE_SELECTION_BG = new Color(0x094771);  // Selected row
    public static final Color TABLE_ALTERNATE_ROW = new Color(0x2D2D2D); // Alternate row
    
    // Border Colors
    public static final Color BORDER_COLOR = new Color(0x555555);        // #555555
    public static final Color BORDER_FOCUS1 = new Color(0x007ACC);        // #007ACC
    
    // ============================================================
    // FONTS
    // ============================================================
    
    public static final Font FONT_DEFAULT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 12);
    
    // ============================================================
    // BORDERS
    // ============================================================
    
    public static final Border BORDER_DEFAULT = BorderFactory.createLineBorder(BORDER_COLOR);
    public static final Border BORDER_FOCUS = BorderFactory.createLineBorder(BORDER_FOCUS1, 2);
    public static final Border BORDER_EMPTY_5 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    public static final Border BORDER_EMPTY_10 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    public static final Border BORDER_COMPOUND = BorderFactory.createCompoundBorder(
        BORDER_DEFAULT, BORDER_EMPTY_5);
    
    // ============================================================
    // GLOBAL THEME APPLICATION
    // ============================================================
    
    /**
     * Applies dark theme to entire application
     * Should be called before creating any GUI components
     */
    public static void applyTheme() {
        // Set UIManager defaults for all Swing components
        UIManager.put("Panel.background", BACKGROUND_DARK);
        UIManager.put("Panel.foreground", FOREGROUND_PRIMARY);
        
        UIManager.put("Label.background", BACKGROUND_DARK);
        UIManager.put("Label.foreground", FOREGROUND_PRIMARY);
        UIManager.put("Label.font", FONT_DEFAULT);
        
        UIManager.put("Button.background", ACCENT_PRIMARY);
        UIManager.put("Button.foreground", FOREGROUND_PRIMARY);
        UIManager.put("Button.font", FONT_BOLD);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(8, 16, 8, 16));
        UIManager.put("Button.focus", new ColorUIResource(ACCENT_HOVER));
        
        UIManager.put("TextField.background", BACKGROUND_MEDIUM);
        UIManager.put("TextField.foreground", FOREGROUND_PRIMARY);
        UIManager.put("TextField.caretForeground", FOREGROUND_PRIMARY);
        UIManager.put("TextField.border", BORDER_DEFAULT);
        UIManager.put("TextField.font", FONT_DEFAULT);
        
        UIManager.put("TextArea.background", BACKGROUND_MEDIUM);
        UIManager.put("TextArea.foreground", FOREGROUND_PRIMARY);
        UIManager.put("TextArea.caretForeground", FOREGROUND_PRIMARY);
        UIManager.put("TextArea.font", FONT_DEFAULT);
        
        UIManager.put("ComboBox.background", BACKGROUND_MEDIUM);
        UIManager.put("ComboBox.foreground", FOREGROUND_PRIMARY);
        UIManager.put("ComboBox.selectionBackground", ACCENT_PRIMARY);
        UIManager.put("ComboBox.selectionForeground", FOREGROUND_PRIMARY);
        UIManager.put("ComboBox.font", FONT_DEFAULT);
        
        UIManager.put("Table.background", BACKGROUND_DARK);
        UIManager.put("Table.foreground", FOREGROUND_PRIMARY);
        UIManager.put("Table.selectionBackground", TABLE_SELECTION_BG);
        UIManager.put("Table.selectionForeground", FOREGROUND_PRIMARY);
        UIManager.put("Table.gridColor", TABLE_GRID);
        UIManager.put("Table.font", FONT_DEFAULT);
        UIManager.put("Table.rowHeight", 25);
        
        UIManager.put("TableHeader.background", BACKGROUND_MEDIUM);
        UIManager.put("TableHeader.foreground", FOREGROUND_PRIMARY);
        UIManager.put("TableHeader.font", FONT_BOLD);
        
        UIManager.put("ScrollPane.background", BACKGROUND_DARK);
        UIManager.put("ScrollBar.background", BACKGROUND_DARK);
        UIManager.put("ScrollBar.thumb", BACKGROUND_LIGHT);
        UIManager.put("ScrollBar.thumbDarkShadow", BACKGROUND_DARK);
        UIManager.put("ScrollBar.thumbHighlight", BACKGROUND_LIGHTER);
        
        UIManager.put("List.background", BACKGROUND_MEDIUM);
        UIManager.put("List.foreground", FOREGROUND_PRIMARY);
        UIManager.put("List.selectionBackground", ACCENT_PRIMARY);
        UIManager.put("List.selectionForeground", FOREGROUND_PRIMARY);
        
        UIManager.put("MenuBar.background", BACKGROUND_DARK);
        UIManager.put("MenuBar.foreground", FOREGROUND_PRIMARY);
        UIManager.put("Menu.background", BACKGROUND_DARK);
        UIManager.put("Menu.foreground", FOREGROUND_PRIMARY);
        UIManager.put("MenuItem.background", BACKGROUND_DARK);
        UIManager.put("MenuItem.foreground", FOREGROUND_PRIMARY);
        
        UIManager.put("TitledBorder.titleColor", FOREGROUND_SECONDARY);
        UIManager.put("TitledBorder.font", FONT_BOLD);
        
        UIManager.put("OptionPane.background", BACKGROUND_DARK);
        UIManager.put("OptionPane.messageForeground", FOREGROUND_PRIMARY);
        
        UIManager.put("ProgressBar.background", BACKGROUND_MEDIUM);
        UIManager.put("ProgressBar.foreground", ACCENT_PRIMARY);
        UIManager.put("ProgressBar.selectionBackground", ACCENT_PRIMARY);
        UIManager.put("ProgressBar.selectionForeground", FOREGROUND_PRIMARY);
        
        UIManager.put("Slider.background", BACKGROUND_DARK);
        UIManager.put("Slider.foreground", ACCENT_PRIMARY);
        
        UIManager.put("TabbedPane.background", BACKGROUND_DARK);
        UIManager.put("TabbedPane.foreground", FOREGROUND_PRIMARY);
        UIManager.put("TabbedPane.selectedForeground", ACCENT_PRIMARY);
        UIManager.put("TabbedPane.contentAreaColor", BACKGROUND_DARK);
        UIManager.put("TabbedPane.borderHighlightColor", BORDER_COLOR);
        
        UIManager.put("ToolTip.background", BACKGROUND_LIGHT);
        UIManager.put("ToolTip.foreground", FOREGROUND_PRIMARY);
        UIManager.put("ToolTip.border", BORDER_DEFAULT);
    }
    
    // ============================================================
    // COMPONENT STYLING METHODS
    // ============================================================
    
    /**
     * Styles a JButton with dark theme
     * @param button Button to style
     * @param type Button type ("primary", "success", "danger", "default")
     */
    public static void styleButton(JButton button, String type) {
        button.setFont(FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        Color bgColor, hoverColor, pressedColor;
        
        switch (type.toLowerCase()) {
            case "primary":
                bgColor = ACCENT_PRIMARY;
                hoverColor = ACCENT_HOVER;
                pressedColor = ACCENT_PRESSED;
                break;
            case "success":
                bgColor = SUCCESS;
                hoverColor = SUCCESS_LIGHT;
                pressedColor = new Color(0x1E7E34);
                break;
            case "danger":
                bgColor = DANGER;
                hoverColor = DANGER_LIGHT;
                pressedColor = new Color(0xC82333);
                break;
            case "secondary":
                bgColor = BACKGROUND_LIGHT;
                hoverColor = BACKGROUND_LIGHTER;
                pressedColor = BACKGROUND_MEDIUM;
                break;
            default:
                bgColor = BACKGROUND_MEDIUM;
                hoverColor = BACKGROUND_LIGHT;
                pressedColor = BACKGROUND_DARK;
        }
        
        button.setBackground(bgColor);
        button.setForeground(FOREGROUND_PRIMARY);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(pressedColor);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
        });
    }
    
    /**
     * Styles a JTextField with dark theme
     * @param textField Text field to style
     */
    public static void styleTextField(JTextField textField) {
        textField.setBackground(BACKGROUND_MEDIUM);
        textField.setForeground(FOREGROUND_PRIMARY);
        textField.setCaretColor(FOREGROUND_PRIMARY);
        textField.setFont(FONT_DEFAULT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BORDER_DEFAULT,
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        textField.setSelectionColor(ACCENT_PRIMARY);
        textField.setSelectedTextColor(FOREGROUND_PRIMARY);
    }
    
    /**
     * Styles a JComboBox with dark theme
     * @param comboBox Combo box to style
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(BACKGROUND_MEDIUM);
        comboBox.setForeground(FOREGROUND_PRIMARY);
        comboBox.setFont(FONT_DEFAULT);
        comboBox.setBorder(BORDER_DEFAULT);
        
        // Style the renderer
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? ACCENT_PRIMARY : BACKGROUND_MEDIUM);
                setForeground(FOREGROUND_PRIMARY);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
    }
    
    /**
     * Styles a JTable with dark theme
     * @param table Table to style
     */
    public static void styleTable(JTable table) {
        table.setBackground(BACKGROUND_DARK);
        table.setForeground(FOREGROUND_PRIMARY);
        table.setSelectionBackground(TABLE_SELECTION_BG);
        table.setSelectionForeground(FOREGROUND_PRIMARY);
        table.setGridColor(TABLE_GRID);
        table.setFont(FONT_DEFAULT);
        table.setRowHeight(28);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // Style header
        table.getTableHeader().setBackground(BACKGROUND_MEDIUM);
        table.getTableHeader().setForeground(FOREGROUND_PRIMARY);
        table.getTableHeader().setFont(FONT_BOLD);
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));
        table.getTableHeader().setReorderingAllowed(false);
    }
    
    /**
     * Styles a JPanel with dark theme
     * @param panel Panel to style
     * @param title Optional title (null for no title)
     */
    public static void stylePanel(JPanel panel, String title) {
        panel.setBackground(BACKGROUND_DARK);
        panel.setForeground(FOREGROUND_PRIMARY);
        panel.setBorder(title != null ? 
            BorderFactory.createTitledBorder(
                BORDER_DEFAULT, 
                title, 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                FONT_BOLD, 
                FOREGROUND_SECONDARY
            ) : BORDER_EMPTY_10);
    }
    
    /**
     * Styles a JScrollPane with dark theme
     * @param scrollPane Scroll pane to style
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBackground(BACKGROUND_DARK);
        scrollPane.setBorder(BORDER_DEFAULT);
        scrollPane.getViewport().setBackground(BACKGROUND_DARK);
        
        // Style scrollbar
        scrollPane.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrollPane.getVerticalScrollBar().setBackground(BACKGROUND_DARK);
        scrollPane.getHorizontalScrollBar().setBackground(BACKGROUND_DARK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
    }
    
    /**
     * Styles a JLabel with dark theme
     * @param label Label to style
     * @param type Label type ("header", "title", "default", "muted")
     */
    public static void styleLabel(JLabel label, String type) {
        switch (type.toLowerCase()) {
            case "header":
                label.setFont(FONT_HEADER);
                label.setForeground(FOREGROUND_PRIMARY);
                break;
            case "title":
                label.setFont(FONT_TITLE);
                label.setForeground(FOREGROUND_PRIMARY);
                break;
            case "muted":
                label.setFont(FONT_DEFAULT);
                label.setForeground(FOREGROUND_MUTED);
                break;
            default:
                label.setFont(FONT_DEFAULT);
                label.setForeground(FOREGROUND_PRIMARY);
        }
    }
    
    // ============================================================
    // CUSTOM SCROLLBAR UI
    // ============================================================
    
    /**
     * Custom ScrollBar UI for dark theme
     */
    private static class DarkScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            thumbColor = BACKGROUND_LIGHT;
            thumbDarkShadowColor = BACKGROUND_DARK;
            thumbHighlightColor = BACKGROUND_LIGHTER;
            trackColor = BACKGROUND_DARK;
            trackHighlightColor = BACKGROUND_MEDIUM;
        }
        
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }
        
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }
        
        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
        
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                               RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, 
                            thumbBounds.width - 4, thumbBounds.height - 4, 8, 8);
            g2.dispose();
        }
        
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(trackColor);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }
    }
}
