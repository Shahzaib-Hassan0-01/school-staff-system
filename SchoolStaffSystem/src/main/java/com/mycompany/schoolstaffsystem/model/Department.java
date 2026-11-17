package com.mycompany.schoolstaffsystem.model;

/**
 * All valid Departments in the school.
 * Using an enum means a new employee can only be placed in a
 * department that already exists (data integrity).
 */
public enum Department {

    ADMINISTRATION("Administration"),
    MATHEMATICS("Mathematics"),
    SCIENCE("Science"),
    ENGLISH("English"),
    COMPUTER_SCIENCE("Computer Science"),
    FINANCE("Finance"),
    STUDENT_SERVICES("Student Services");

    private final String label;

    Department(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Finds a Department from text, e.g. "science" or "3".
     * Returns null if the text does not match any existing Department.
     */
    public static Department fromInput(String input) {
        if (input == null) {
            return null;
        }
        String text = input.trim();

        // Option 1: the user typed the number shown in the list
        try {
            int number = Integer.parseInt(text);
            Department[] all = Department.values();
            if (number >= 1 && number <= all.length) {
                return all[number - 1];
            }
            return null;
        } catch (NumberFormatException e) {
            // not a number, so try matching the name below
        }

        // Option 2: the user typed the name (not case sensitive)
        for (Department dept : Department.values()) {
            if (dept.label.equalsIgnoreCase(text)) {
                return dept;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
