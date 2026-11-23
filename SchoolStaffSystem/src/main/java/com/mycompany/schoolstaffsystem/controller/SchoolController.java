package com.mycompany.schoolstaffsystem.controller;


import com.mycompany.schoolstaffsystem.model.Employee;
import com.mycompany.schoolstaffsystem.model.StaffRegister;
import com.mycompany.schoolstaffsystem.view.ConsoleView;
import java.io.IOException;
import java.util.List; 


/**
 * Controller - gets the user's choice from the view, asks the model to do
 * the work, then tells the view what to show.
 *
 * @author shahz
 */
public class SchoolController {

    private static final int NAMES_TO_SHOW = 20;
    private StaffRegister model;
    private ConsoleView view;

    public SchoolController(StaffRegister model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        loadFile();

        boolean running = true;
        while (running) {
            view.showMenu();
            MenuOption option = MenuOption.fromInput(view.ask(""));

            if (option == null) {
                view.showError("Invalid choice, enter a number from 1 to " + MenuOption.values().length);
                continue;
            }
            view.showMessage(option.getLabel() + " selected");

            switch (option) {
                case SORT:
                     sortStaff();
                    break;
                case SEARCH:
                    searchStaff();     
                    break;
                case ADD_RECORDS:
                case BINARY_TREE:
                    view.showMessage("(not built yet - coming in the next commits)");
                    break;
                case EXIT:
                    view.showMessage("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    // keeps asking until the file opens
    private void loadFile() {
        while (true) {
            String fileName = view.ask("Please enter the filename to read: ").trim();
            try {
                int count = model.loadFromFile(fileName);
                view.showMessage("File read successfully (" + count + " records loaded)");
                for (String skipped : model.getSkippedLines()) {
                    view.showMessage("  Skipped " + skipped);
                }
                return;
            } catch (IOException e) {
                view.showError("Could not read '" + fileName + "', check the name and try again.");
            }
        }
    }
    
     private void sortStaff() {
        model.sortByName();
        view.showTitle("Staff sorted A-Z - first " + NAMES_TO_SHOW + " of " + model.getEmployeeCount());
        view.showEmployeeTable(model.getFirst(NAMES_TO_SHOW));
    }
     
        
    private void searchStaff() {
        // binary search needs a sorted list
        if (!model.isSorted()) {
            model.sortByName();
            view.showMessage("(list wasn't sorted yet so it was sorted first)");
        }

        String name = view.ask("Enter the full name to search (e.g. Kate Summer): ");
        if (name.trim().isEmpty()) {
            view.showError("Name can't be empty.");
            return;
        }

        List<Employee> matches = model.searchByName(name);
        if (matches.isEmpty()) {
            view.showMessage("No staff member called '" + name.trim() + "' was found.");
            view.showMessage("Tip: enter first AND last name, e.g. Kate Summer");
            return;
        }

        view.showTitle("Search result");
        for (Employee e : matches) {
            view.showEmployeeDetails(e);
        }
    }
}
