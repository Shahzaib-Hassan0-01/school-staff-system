package com.mycompany.schoolstaffsystem.model;

/**
 * Roles in the school. Rank is used for the hierarchy tree (1 = top).
 *
 * @author shahz
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

    // accepts the number from the list (e.g. "3") or the name (e.g. "year head")
    // returns null if nothing matches
    public static ManagerType fromInput(String input) {
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
            // not a number, check the names instead
        }

        for (ManagerType type : values()) {
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
