package com.mycompany.schoolstaffsystem.model;

/**
 * All valid Manager Types (roles) in the school.
 * Using an enum means only these values can ever exist, so a user
 * cannot type an invalid role (data integrity).
 *
 * The rank is used to build the hierarchy tree: rank 1 is the top of the school.
 */
public enum ManagerType {

    PRINCIPAL("Principal", 1),
    DEPUTY_PRINCIPAL("Deputy Principal", 2),
    HEAD_OF_DEPARTMENT("Head of Department", 3),
    YEAR_HEAD("Year Head", 4),
    TEACHER("Teacher", 5),
    SUPPORT_STAFF("Support Staff", 6);

    private final String label;
    private final int rank;

    ManagerType(String label, int rank) {
        this.label = label;
        this.rank = rank;
    }

    public String getLabel() {
        return label;
    }

    public int getRank() {
        return rank;
    }

    /**
     * Finds a ManagerType from text, e.g. "head of department" or "3".
     * Returns null if the text does not match any valid Manager Type.
     */
    public static ManagerType fromInput(String input) {
        if (input == null) {
            return null;
        }
        String text = input.trim();

        // Option 1: the user typed the number shown in the list (1, 2, 3...)
        try {
            int number = Integer.parseInt(text);
            ManagerType[] all = ManagerType.values();
            if (number >= 1 && number <= all.length) {
                return all[number - 1];
            }
            return null;
        } catch (NumberFormatException e) {
            // not a number, so try matching the name below
        }

        // Option 2: the user typed the name (not case sensitive)
        for (ManagerType type : ManagerType.values()) {
            if (type.label.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
