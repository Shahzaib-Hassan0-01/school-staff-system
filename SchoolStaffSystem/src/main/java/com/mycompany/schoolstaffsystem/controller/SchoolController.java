package com.mycompany.schoolstaffsystem.controller;


import com.mycompany.schoolstaffsystem.model.Employee;
import com.mycompany.schoolstaffsystem.model.Department;
import com.mycompany.schoolstaffsystem.model.ManagerType;
import com.mycompany.schoolstaffsystem.model.StaffRegister;
import com.mycompany.schoolstaffsystem.view.ConsoleView;
import com.mycompany.schoolstaffsystem.model.EmployeeTree;
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
                    addRecords();
                    break;
                case BINARY_TREE:
                    showHierarchy();
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
    
        private void addRecords() {
        boolean again = true;
        while (again) {
            String firstName = askName("Enter first name: ");
            String lastName = askName("Enter last name: ");
            ManagerType type = askManagerType();
            Department dept = askDepartment();

            if (model.isDuplicate(firstName, lastName, type, dept)) {
                view.showError("This person is already in the list with the same role and department.");
            } else {
                Employee added = model.addEmployee(firstName, lastName, type, dept);
                view.showMessage("Record added: " + added);
            }

            again = view.ask("Add another record? (Y/N): ").trim().equalsIgnoreCase("Y");
        }

        List<Employee> newOnes = model.getNewlyAdded();
       if (newOnes.isEmpty()) {
            view.showMessage("No new records were added.");
        } else {
            view.showTitle("Newly added records (" + newOnes.size() + ")");
            view.showEmployeeTable(newOnes);
            view.showMessage("Total staff now: " + model.getEmployeeCount());
        }
    }

    // keeps asking until the name is valid
    private String askName(String prompt) {
        while (true) {
            String name = view.ask(prompt);
            if (model.isValidName(name)) {
                return name.trim();
            }
            view.showError("Name must be 2-30 letters (spaces, - and ' allowed).");
        }
    }

    // keeps asking until the manager type exists
    private ManagerType askManagerType() {
        view.showManagerTypes();
        while (true) {
            ManagerType type = ManagerType.fromInput(view.ask("Choose Manager Type (number or name): "));
            if (type == null) {
                view.showError("That Manager Type doesn't exist, pick one from the list.");
            } else if (type == ManagerType.PRINCIPAL && model.hasPrincipal()) {
                view.showError("The school already has a Principal, pick another type.");
            } else {
                return type;
            }
        }
    }

    // keeps asking until the department exists
    private Department askDepartment() {
        view.showDepartments();
        while (true) {
            Department dept = Department.fromInput(view.ask("Choose Department (number or name): "));
            if (dept != null) {
                return dept;
            }
            view.showError("That Department doesn't exist, pick one from the list.");
        }
    }
    
        private void showHierarchy() {
        if (model.getEmployeeCount() < StaffRegister.MIN_TREE_RECORDS) {
            view.showError("Need at least " + StaffRegister.MIN_TREE_RECORDS + " records to build the tree, you have "
                    + model.getEmployeeCount());
            return;
        }

        EmployeeTree tree = model.buildHierarchyTree();
        view.showTitle("School hierarchy (level order)");
        view.showTree(tree.levelOrder(), tree.getHeight(), tree.getNodeCount());
    }
}
