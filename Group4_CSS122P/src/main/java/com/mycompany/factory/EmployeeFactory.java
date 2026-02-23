package com.mycompany.group4_css122p.factory;

import com.mycompany.group4_css122p.model.Employee;
import com.mycompany.group4_css122p.model.Manager;

/**
 * FACTORY PATTERN - EmployeeFactory
 * Creates different types of employees without exposing instantiation logic
 * @author Kage
 */
public class EmployeeFactory {
    
    // Employee type constants
    public static final String TYPE_REGULAR = "REGULAR";
    public static final String TYPE_MANAGER = "MANAGER";
    
    /**
     * Factory method to create employees based on type
     * @param type The type of employee to create
     * @param params Array of parameters for employee creation
     * @return Employee object of the specified type
     */
    public static Employee createEmployee(String type, Object... params) {
        switch (type.toUpperCase()) {
            case TYPE_REGULAR:
                return createRegularEmployee(params);
            case TYPE_MANAGER:
                return createManager(params);
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }
    
    private static Employee createRegularEmployee(Object[] params) {
        return new Employee(
            (String) params[0],
            (String) params[1],
            (String) params[2],
            (String) params[3],
            (String) params[4],
            (String) params[5],
            (String) params[6],
            toDouble(params[7]),
            toDouble(params[8]),
            (String) params[9]
        );
    }
    
    private static Manager createManager(Object[] params) {
        return new Manager(
            (String) params[0],
            (String) params[1],
            (String) params[2],
            (String) params[3],
            (String) params[4],
            (String) params[5],
            (String) params[6],
            toDouble(params[7]),
            toDouble(params[8]),
            (String) params[9],
            toInt(params[10]),
            (String) params[11],
            toDouble(params[12])
        );
    }
    
    // Helper method to convert Number to double
    private static double toDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        return Double.parseDouble(obj.toString());
    }
    
    // Helper method to convert Number to int
    private static int toInt(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return Integer.parseInt(obj.toString());
    }
    
    public static String[] getEmployeeTypes() {
        return new String[]{TYPE_REGULAR, TYPE_MANAGER};
    }
}