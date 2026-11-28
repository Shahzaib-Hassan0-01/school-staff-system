package com.mycompany.schoolstaffsystem.view;

import com.mycompany.schoolstaffsystem.controller.MenuOption;
import java.util.Scanner;
import com.mycompany.schoolstaffsystem.model.Department;
import com.mycompany.schoolstaffsystem.model.TreeNode;
import com.mycompany.schoolstaffsystem.model.Employee;
import com.mycompany.schoolstaffsystem.model.ManagerType;
import java.util.List;

/**
 * View - all printing and reading from the keyboard happens here.
 *
 * @author shahz
 */
public class ConsoleView {
    
    private static final String ROW = "%-4s %-22s %-20s %-18s%n";
    private Scanner scanner = new Scanner(System.in);

    public String ask(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showError(String msg) {
        System.out.println("ERROR: " + msg);
    }

    public void showTitle(String title) {
        System.out.println();
        System.out.println("===== " + title + " =====");
    }

    public void showMenu() {
        System.out.println();
        System.out.println("Do You wish to SORT or SEARCH:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getNumber() + ". " + option.getLabel());
        }
    }
    
    public void showEmployeeTable(List<Employee> list) {
        System.out.printf(ROW, "No.", "Name", "Manager Type", "Department");
        System.out.println("-----------------------------------------------------------------");
        int n = 1;
        for (Employee e : list) {
            System.out.printf(ROW, n + ".", e.getFullName(), e.getManagerType(), e.getDepartment());
            n++;
        }
    }
    
    public void showEmployeeDetails(Employee e) {
        System.out.println("  Name         : " + e.getFullName());
        System.out.println("  Manager Type : " + e.getManagerType());
        System.out.println("  Department   : " + e.getDepartment());
        if (!e.getEmail().isEmpty()) {
            System.out.println("  Email        : " + e.getEmail());
        }
        if (!e.getPosition().isEmpty()) {
            System.out.println("  Level        : " + e.getPosition());
        }
        System.out.println();
    }
    
        public void showManagerTypes() {
        System.out.println("Valid Manager Types:");
        for (ManagerType type : ManagerType.values()) {
            System.out.println("  " + (type.ordinal() + 1) + ". " + type.getLabel());
        }
    }

    public void showDepartments() {
        System.out.println("Existing Departments:");
        for (Department dept : Department.values()) {
            System.out.println("  " + (dept.ordinal() + 1) + ". " + dept.getLabel());
        }
    }
    
    public void showTree(List<List<TreeNode>> levels, int height, int nodeCount) {
        for (int i = 0; i < levels.size(); i++) {
            List<TreeNode> level = levels.get(i);
            System.out.println("Level " + i + " (" + level.size() + " node(s)):");
            for (TreeNode node : level) {
                System.out.println("  " + node.getEmployee() + children(node));
            }
        }
        System.out.println();
        System.out.println("Tree height (number of levels): " + height);
        System.out.println("Total node count: " + nodeCount);
    }

    // shows who is under this person, e.g. "-> L: Kate Summer | R: Ken Winter"
    private String children(TreeNode node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return "";
        }
        String left = (node.getLeft() == null) ? "-" : node.getLeft().getEmployee().getFullName();
        String right = (node.getRight() == null) ? "-" : node.getRight().getEmployee().getFullName();
        return "  ->  L: " + left + " | R: " + right;
    }
}
