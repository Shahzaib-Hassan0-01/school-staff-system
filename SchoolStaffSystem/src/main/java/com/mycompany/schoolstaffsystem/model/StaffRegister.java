package com.mycompany.schoolstaffsystem.model;

import com.mycompany.schoolstaffsystem.algorithms.MergeSort;
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
    private boolean sorted = false;
    
    public int loadFromFile(String fileName) throws IOException {
        employees = fileReader.read(fileName);
         sorted = false;
        return employees.size();
    }

    public List<String> getSkippedLines() {
        return fileReader.getSkippedLines();
    }

    
    public void sortByName() {
        employees = MergeSort.sort(employees, Employee.BY_NAME);
        sorted = true;
    }

    public boolean isSorted() {
        return sorted;
    }

    public List<Employee> getFirst(int n) {
        int end = Math.min(n, employees.size());
        return new ArrayList<>(employees.subList(0, end));
    }
    
    public int getEmployeeCount() {
        return employees.size();
    }
}
