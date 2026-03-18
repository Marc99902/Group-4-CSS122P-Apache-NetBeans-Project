package com.mycompany.group4_css122p.gui.theme;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.*;
import java.awt.*;

/**
 * ProfessionalTheme.java - Dark Professional Theme for GUI
 * 
 * This class provides a clean, dark corporate theme for all GUI components.
 * The palette uses slate surfaces with restrained blue accents to keep the
 * interface readable and consistent across all windows.
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public class ProfessionalTheme {
    
    // ============================================================
    // COLOR PALETTE - Dark Professional
    // ============================================================
    
    // Primary Colors
    public static final Color PRIMARY_BLUE = new Color(0x4C8DFF);
    public static final Color HEADER_BLUE = new Color(0x163257);
    public static final Color HOVER_BLUE = new Color(0x6AA4FF);
    public static final Color LIGHT_BLUE = new Color(0x223A5E);
    
    // Background Colors
    public static final Color BACKGROUND_WHITE = new Color(0x121826);
    public static final Color PANEL_GRAY = new Color(0x1A2233);
    public static final Color TABLE_HEADER_BG = new Color(0x1F2C43);
    public static final Color TABLE_ALT_ROW = new Color(0x162033);
    
    // Border Colors
    public static final Color BORDER_LIGHT = new Color(0x2A3650);
    public static final Color BORDER_MEDIUM = new Color(0x40506F);
    
    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(0xF3F6FB);
    public static final Color TEXT_SECONDARY = new Color(0xC1CAE0);
    public static final Color TEXT_WHITE = new Color(0xFFFFFF);
    public static final Color TEXT_MUTED = new Color(0x8C98B0);
    
    // Status Colors
    public static final Color SUCCESS_GREEN = new Color(0x2FB171);
    public static final Color WARNING_YELLOW = new Color(0xF0B94B);
    public static final Color DANGER_RED = new Color(0xE36D6D);
    public static final Color INFO_CYAN = new Color(0x57B7D9);
    
    // ============================================================
    // FONTS
    // ============================================================
    
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_DEFAULT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 12);
    public static final Color BACKGROUND_DARKEST = new Color(0x0D1420);
    public static final Color BACKGROUND_DARK = PANEL_GRAY;
    public static final Color BACKGROUND_MEDIUM = new Color(0x24324A);
    public static final Color BACKGROUND_LIGHT = new Color(0x2D3B57);
    public static final Color ACCENT_PRIMARY = PRIMARY_BLUE;
    public static final Color ACCENT_SECONDARY = HEADER_BLUE;
    public static final Color BORDER_COLOR = BORDER_LIGHT;
    public static final Color FOREGROUND_PRIMARY = TEXT_PRIMARY;
    public static final Color FOREGROUND_SECONDARY = TEXT_SECONDARY;
    public static final Color FOREGROUND_MUTED = TEXT_MUTED;
    public static final Color SUCCESS = SUCCESS_GREEN;
    public static final Color WARNING = WARNING_YELLOW;
    public static final Color DANGER = DANGER_RED;
    public static final Color INFO = INFO_CYAN;
    
    // ============================================================
    // THEME APPLICATION
    // ============================================================
    
    /**
     * Applies the professional theme to the entire application
     * Should be called before creating any GUI components
     */
    public static void applyTheme() {
        try {
            // Set Look and Feel to system default (we'll customize components)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Override common defaults so dialogs inherit the dark palette.
            UIManager.put("Panel.background", BACKGROUND_DARKEST);
            UIManager.put("OptionPane.background", BACKGROUND_DARKEST);
            UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
            UIManager.put("OptionPane.foreground", TEXT_PRIMARY);
            UIManager.put("Button.background", BACKGROUND_MEDIUM);
            UIManager.put("Button.foreground", TEXT_PRIMARY);
            UIManager.put("Label.foreground", TEXT_PRIMARY);
            UIManager.put("TextField.background", BACKGROUND_LIGHT);
            UIManager.put("TextField.foreground", TEXT_PRIMARY);
            UIManager.put("TextField.caretForeground", TEXT_PRIMARY);
            UIManager.put("ComboBox.background", BACKGROUND_LIGHT);
            UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
            UIManager.put("Table.background", BACKGROUND_DARK);
            UIManager.put("Table.foreground", TEXT_PRIMARY);
            UIManager.put("Table.selectionBackground", LIGHT_BLUE);
            UIManager.put("Table.selectionForeground", TEXT_PRIMARY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ============================================================
    // COMPONENT STYLING METHODS
    // ============================================================
    
    /**
     * Styles a button with the professional theme
     * 
     * @param button Button to style
     * @param type Button type: "primary", "success", "danger", "secondary", "default"
     */
    public static void styleButton(JButton button, String type) {
        Color bgColor;
        Color fgColor = TEXT_WHITE;
        Color borderColor = BORDER_MEDIUM;
        Color disabledBg = BACKGROUND_LIGHT;
        Color disabledFg = FOREGROUND_MUTED;
        
        switch (type.toLowerCase()) {
            case "primary":
                bgColor = PRIMARY_BLUE;
                break;
            case "success":
                bgColor = SUCCESS_GREEN;
                break;
            case "danger":
                bgColor = DANGER_RED;
                break;
            case "secondary":
                bgColor = BACKGROUND_MEDIUM;
                fgColor = TEXT_PRIMARY;
                break;
            default:
                bgColor = BACKGROUND_LIGHT;
                fgColor = TEXT_PRIMARY;
                break;
        }
        
        // Force a basic UI so Windows LAF doesn't repaint buttons white.
        button.setUI(new BasicButtonUI());
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(FONT_DEFAULT);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setRolloverEnabled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 32));
        button.setDisabledIcon(null);
        button.setDisabledSelectedIcon(null);
        button.getModel().setRollover(false);
        
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor),
            BorderFactory.createEmptyBorder(6, 16, 6, 16)
        ));
        
        // Add hover effect
        final Color originalBg = bgColor;
        final Color originalFg = fgColor;
        final Color hoverBg = type.equals("secondary") || type.equals("default")
            ? BACKGROUND_LIGHT.brighter()
            : HOVER_BLUE;
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(hoverBg);
                }
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(button.isEnabled() ? originalBg : disabledBg);
            }
        });

        button.addPropertyChangeListener("enabled", evt -> {
            boolean enabled = Boolean.TRUE.equals(evt.getNewValue());
            button.setBackground(enabled ? originalBg : disabledBg);
            button.setForeground(enabled ? originalFg : disabledFg);
            button.setCursor(new Cursor(enabled ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        });

        if (!button.isEnabled()) {
            button.setBackground(disabledBg);
            button.setForeground(disabledFg);
        }
    }
    
    /**
     * Styles a text field with the professional theme
     * @param textField Text field to style
     */
    public static void styleTextField(JTextField textField) {
        textField.setBackground(BACKGROUND_LIGHT);
        textField.setForeground(TEXT_PRIMARY);
        textField.setCaretColor(TEXT_PRIMARY);
        textField.setSelectionColor(LIGHT_BLUE);
        textField.setSelectedTextColor(TEXT_PRIMARY);
        textField.setFont(FONT_DEFAULT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_MEDIUM),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        textField.setPreferredSize(new Dimension(180, 32));
    }
    
    /**
     * Styles a combo box with the professional theme
     * @param comboBox Combo box to style
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton("\u25BE");
                arrowButton.setUI(new BasicButtonUI());
                arrowButton.setBackground(BACKGROUND_LIGHT);
                arrowButton.setForeground(TEXT_PRIMARY);
                arrowButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, BORDER_MEDIUM));
                arrowButton.setFocusPainted(false);
                arrowButton.setContentAreaFilled(true);
                arrowButton.setOpaque(true);
                return arrowButton;
            }
        });

        comboBox.setBackground(BACKGROUND_LIGHT);
        comboBox.setForeground(TEXT_PRIMARY);
        comboBox.setFont(FONT_DEFAULT);
        comboBox.setBorder(BorderFactory.createLineBorder(BORDER_MEDIUM));
        comboBox.setPreferredSize(new Dimension(160, 32));
        comboBox.setFocusable(false);

        ListCellRenderer<? super Object> renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
                label.setBackground(isSelected ? LIGHT_BLUE : BACKGROUND_LIGHT);
                label.setForeground(TEXT_PRIMARY);
                return label;
            }
        };
        comboBox.setRenderer(renderer);
        
        // Style the popup menu
        Object popup = comboBox.getUI().getAccessibleChild(comboBox, 0);
        if (popup instanceof JPopupMenu) {
            JPopupMenu popupMenu = (JPopupMenu) popup;
            popupMenu.setBackground(BACKGROUND_LIGHT);
            popupMenu.setForeground(TEXT_PRIMARY);
            popupMenu.setBorder(BorderFactory.createLineBorder(BORDER_MEDIUM));
        }
    }
    
    /**
     * Styles a table with the professional theme
     * @param table Table to style
     */
    public static void styleTable(JTable table) {
        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(TABLE_HEADER_BG);
        header.setForeground(TEXT_PRIMARY);
        header.setFont(FONT_BOLD);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_BLUE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        header.setPreferredSize(new Dimension(0, 36));
        header.setOpaque(true);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                label.setOpaque(true);
                label.setBackground(TABLE_HEADER_BG);
                label.setForeground(TEXT_PRIMARY);
                label.setFont(FONT_BOLD);
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 1, BORDER_MEDIUM),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
                return label;
            }
        });
        
        // Table body styling
        table.setBackground(BACKGROUND_DARK);
        table.setForeground(TEXT_PRIMARY);
        table.setFont(FONT_DEFAULT);
        table.setGridColor(BORDER_LIGHT);
        table.setSelectionBackground(LIGHT_BLUE);
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setRowHeight(32);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        
        // Alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? BACKGROUND_DARK : TABLE_ALT_ROW);
                    c.setForeground(TEXT_PRIMARY);
                }
                
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                setHorizontalAlignment(column == 0 ? SwingConstants.CENTER : SwingConstants.LEFT);
                return c;
            }
        });
    }
    
    /**
     * Styles a scroll pane with the professional theme
     * @param scrollPane Scroll pane to style
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBackground(BACKGROUND_DARK);
        scrollPane.getViewport().setBackground(BACKGROUND_DARK);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT));
        
        // Style the scrollbar
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        
        verticalBar.setBackground(BACKGROUND_DARK);
        horizontalBar.setBackground(BACKGROUND_DARK);
    }
    
    /**
     * Styles a text area with the professional theme
     * @param textArea Text area to style
     */
    public static void styleTextArea(JTextArea textArea) {
        textArea.setBackground(BACKGROUND_LIGHT);
        textArea.setForeground(TEXT_PRIMARY);
        textArea.setCaretColor(TEXT_PRIMARY);
        textArea.setSelectionColor(LIGHT_BLUE);
        textArea.setFont(FONT_DEFAULT);
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_MEDIUM),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }
    
    /**
     * Styles a label as a title
     * @param label Label to style
     */
    public static void styleTitleLabel(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(TEXT_PRIMARY);
    }
    
    /**
     * Styles a label as a header
     * @param label Label to style
     */
    public static void styleHeaderLabel(JLabel label) {
        label.setFont(FONT_HEADER);
        label.setForeground(TEXT_PRIMARY);
    }
    
    /**
     * Creates a styled panel with border
     * @param title Panel title (can be null)
     * @return Styled JPanel
     */
    public static JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_DARK);
        
        if (title != null && !title.isEmpty()) {
            panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_LIGHT),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                FONT_BOLD,
                TEXT_SECONDARY
            ));
        } else {
            panel.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT));
        }
        
        return panel;
    }
    
    /**
     * Creates a header panel with blue background
     * @param title Header title text
     * @return Styled header JPanel
     */
    public static JPanel createHeaderPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(HEADER_BLUE);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_HEADER);
        titleLabel.setForeground(TEXT_WHITE);
        panel.add(titleLabel, BorderLayout.WEST);
        
        return panel;
    }
    
    /**
     * Creates a statistics card panel
     * @param title Card title
     * @param valueLabel Label to display value
     * @return Styled card JPanel
     */
    public static JPanel createStatCard(String title, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(BACKGROUND_DARK);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_LIGHT),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_SMALL);
        titleLabel.setForeground(TEXT_SECONDARY);
        card.add(titleLabel, BorderLayout.NORTH);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(PRIMARY_BLUE);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Creates a separator with professional theme styling
     * @return Styled JSeparator
     */
    public static JSeparator createStyledSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(BORDER_LIGHT);
        separator.setBackground(BORDER_LIGHT);
        return separator;
    }
    
    /**
     * Creates an action button panel for the side panel
     * @param text Button text
     * @param type Button type
     * @return Styled JButton
     */
    public static JButton createActionButton(String text, String type) {
        JButton button = new JButton(text);
        styleButton(button, type);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}
