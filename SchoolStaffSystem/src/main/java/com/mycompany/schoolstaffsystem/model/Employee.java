package com.mycompany.schoolstaffsystem.model;

import java.util.Comparator;

/**
 * MODEL: one staff member of the school.
 * This class only holds data. It never prints anything (that is the View's job).
 */
public class Employee {

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final double salary;
    private final Department department;
    private final String position;
    private final ManagerType managerType;
    private final String school;

    /** Full constructor, used when reading a line from the file. */
    public Employee(String firstName, String lastName, String gender, String email,
                    double salary, Department department, String position,
                    ManagerType managerType, String school) {
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

    /** Short constructor, used when the user adds a new record (Name, Manager Type, Department). */
    public Employee(String firstName, String lastName, ManagerType managerType, Department department) {
        this(firstName, lastName, "", "", 0.0, department, "", managerType, "");
    }

    /**
     * Compares employees by full name, ignoring upper/lower case.
     * The SORT and the SEARCH both use this same rule, so the binary search
     * always looks through the list in exactly the order it was sorted in.
     */
    public static final Comparator<Employee> BY_NAME = new Comparator<Employee>() {
        @Override
        public int compare(Employee a, Employee b) {
            return a.getFullName().compareToIgnoreCase(b.getFullName());
        }
    };

    /** Compares employees by rank (Principal first, Support Staff last). Used for the tree. */
    public static final Comparator<Employee> BY_RANK = new Comparator<Employee>() {
        @Override
        public int compare(Employee a, Employee b) {
            return Integer.compare(a.getManagerType().getRank(), b.getManagerType().getRank());
        }
    };

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
