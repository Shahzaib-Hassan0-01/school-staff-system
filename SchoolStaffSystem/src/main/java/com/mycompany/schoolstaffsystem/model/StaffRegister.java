package com.mycompany.schoolstaffsystem.model;


import com.mycompany.schoolstaffsystem.algorithms.BinarySearch;  
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
    
    public List<Employee> searchByName(String name) {
        List<Employee> matches = new ArrayList<>();
        String cleanName = name.trim().replaceAll("\\s+", " "); // remove extra spaces

        int index = BinarySearch.search(employees, cleanName, 0, employees.size() - 1);
        if (index == -1) {
            return matches;
        }

        // if two people have the same name they'll be next to each other, so check both sides
        int start = index;
        while (start > 0 && employees.get(start - 1).getFullName().equalsIgnoreCase(cleanName)) {
            start--;
        }
        int end = index;
        while (end < employees.size() - 1 && employees.get(end + 1).getFullName().equalsIgnoreCase(cleanName)) {
            end++;
        }
        for (int i = start; i <= end; i++) {
            matches.add(employees.get(i));
        }
        return matches;
    }
}
