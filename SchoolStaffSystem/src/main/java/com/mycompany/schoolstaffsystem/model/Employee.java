package com.mycompany.schoolstaffsystem.model;

import java.util.Comparator;

/**
 * One staff member. Only holds data, no printing here.
 *
 * @author shahz
 */
public class Employee {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private double salary;
    private Department department;
    private String position;
    private ManagerType managerType;
    private String school;

    // used when reading from the file
    public Employee(String firstName, String lastName, String gender, String email, double salary,
            Department department, String position, ManagerType managerType, String school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.managerType = managerType;
        this.school = school;
    }

    // used when the user adds someone new (only name, role and department are asked)
    public Employee(String firstName, String lastName, ManagerType managerType, Department department) {
        this(firstName, lastName, "", "", 0, department, "", managerType, "");
    }

    // sort and search both use this so they always agree on the order
    public static final Comparator<Employee> BY_NAME
            = (a, b) -> a.getFullName().compareToIgnoreCase(b.getFullName());

    // for the tree - Principal first, Support Staff last
    public static final Comparator<Employee> BY_RANK
            = (a, b) -> Integer.compare(a.getManagerType().getRank(), b.getManagerType().getRank());

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + managerType + ", " + department + ")";
    }
}
