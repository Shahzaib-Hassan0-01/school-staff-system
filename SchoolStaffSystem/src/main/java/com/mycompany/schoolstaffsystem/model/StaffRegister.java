package com.mycompany.schoolstaffsystem.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model - keeps the staff list in memory and does the work on it.
 * The controller calls these methods.
 *
 * @author shahz
 */
public class StaffRegister {

    private List<Employee> employees = new ArrayList<>();
    private EmployeeFileReader fileReader = new EmployeeFileReader();

    public int loadFromFile(String fileName) throws IOException {
        employees = fileReader.read(fileName);
        return employees.size();
    }

    public List<String> getSkippedLines() {
        return fileReader.getSkippedLines();
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
