package com.mycompany.schoolstaffsystem.controller;

/**
 * Main menu options. The view loops through these to print the menu and
 * the controller switches on them, so no hard coded numbers anywhere else.
 *
 * @author shahz
 */
public enum MenuOption {
    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD_RECORDS(3, "ADD RECORDS"),
    BINARY_TREE(4, "Create a binary tree"),
    EXIT(5, "EXIT");

    private final int number;
    private final String label;

    MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }

    // "1" or "sort" -> SORT, null if no match
    public static MenuOption fromInput(String input) {
        if (input == null) {
            return null;
        }
        String text = input.trim();
        for (MenuOption option : values()) {
            if (String.valueOf(option.number).equals(text) || option.label.equalsIgnoreCase(text)) {
                return option;
            }
        }
        return null;
    }
}
