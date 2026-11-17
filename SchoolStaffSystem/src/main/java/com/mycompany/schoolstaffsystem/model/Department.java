package com.mycompany.schoolstaffsystem.model;

/**
 * Departments that exist in the school.
 *
 * @author shahz
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

    // same idea as ManagerType - number or name, null if not valid
    public static Department fromInput(String input) {
        if (input == null) {
            return null;
        }
        String text = input.trim();

        try {
            int num = Integer.parseInt(text);
            if (num >= 1 && num <= values().length) {
                return values()[num - 1];
            }
            return null;
        } catch (NumberFormatException e) {
            // not a number
        }

        for (Department dept : values()) {
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
