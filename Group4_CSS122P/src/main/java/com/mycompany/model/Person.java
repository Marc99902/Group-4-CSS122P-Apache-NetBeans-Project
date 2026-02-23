package com.mycompany.group4_css122p.model;

/**
 * Abstract class demonstrating ABSTRACTION
 * This is the base class for all person types in the system
 * @author Kage
 */
public abstract class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    public Person(String id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    // Abstract method - must be implemented by subclasses (POLYMORPHISM)
    public abstract String getRole();
    
    public abstract String getDetails();
    
    // Getters and Setters - ENCAPSULATION
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + getFullName() + ", Role: " + getRole();
    }
}