package com.mycompany.schoolstaffsystem.view;

import com.mycompany.schoolstaffsystem.controller.MenuOption;
import java.util.Scanner;

/**
 * View - all printing and reading from the keyboard happens here.
 *
 * @author shahz
 */
public class ConsoleView {

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
}
