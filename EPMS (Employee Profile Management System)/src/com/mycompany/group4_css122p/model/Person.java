package com.mycompany.group4_css122p.model;

import java.io.Serializable;

/**
 * Person.java - Abstract Class for Person
 * 
 * This abstract class serves as the base class for all person types
 * in the system. It defines common attributes and abstract methods
 * that must be implemented by subclasses.
 * 
 * OOP CONCEPTS USED:
 * - ABSTRACTION: Cannot be instantiated directly, defines contract for subclasses
 * - ENCAPSULATION: All fields are private, accessed via getters/setters
 * 
 * @author Group 4 (Balugay, Blaza, Resurrecion, Tenoria, Tomaro)
 * @version 1.0
 */
public abstract class Person implements Serializable {
    
    // ============================================================
    // INSTANCE VARIABLES - Person Data (Private for Encapsulation)
    // ============================================================
    private String id;              // Unique person ID
    private String firstName;       // Person's first name
    private String lastName;        // Person's last name
    private String email;           // Email address
    private String phone;           // Phone number
    
    // ============================================================
    // CONSTRUCTORS
    // ============================================================
    
    /**
     * Default Constructor - Creates empty Person object
     */
    public Person() {
        // Initialize with empty values
    }
    
    /**
     * Full Parameter Constructor - Creates Person with all data
     * 
     * @param id Person ID
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param phone Phone number
     */
    public Person(String id, String firstName, String lastName, 
                  String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    // ============================================================
    // GETTER METHODS - Access private fields (Encapsulation)
    // ============================================================
    
    public String getId() { 
        return id; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    /**
     * Returns full name by combining first and last name
     * @return Full name (e.g., "Juan Dela Cruz")
     */
    public String getFullName() { 
        return firstName + " " + lastName; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public String getPhone() { 
        return phone; 
    }
    
    // ============================================================
    // SETTER METHODS - Modify private fields (Encapsulation)
    // ============================================================
    
    public void setId(String id) { 
        this.id = id; 
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    
    // ============================================================
    // ABSTRACT METHODS - Must be implemented by subclasses
    // ============================================================
    
    /**
     * ABSTRACT METHOD: Returns the role of the person
     * Must be implemented by subclasses (Employee, Manager, etc.)
     * 
     * @return Role description
     */
    public abstract String getRole();
    
    /**
     * ABSTRACT METHOD: Returns formatted details string
     * Must be implemented by subclasses
     * 
     * @return Formatted details
     */
    public abstract String getDetails();
    
    // ============================================================
    // OVERRIDE METHODS
    // ============================================================
    
    /**
     * Returns string representation of Person
     * @return String with basic person info
     */
    @Override
    public String toString() {
        return "Person [" + id + "] " + getFullName();
    }
    
    /**
     * Compares two Person objects for equality
     * Based on person ID (unique identifier)
     * @param obj Object to compare
     * @return true if same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Person person = (Person) obj;
        return id != null && id.equals(person.id);
    }
    
    /**
     * Generates hash code based on person ID
     * Required when overriding equals()
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
