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
    private List<Employee> newlyAdded = new ArrayList<>();
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
    
    public boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        String text = name.trim();
        return text.length() >= 2 && text.length() <= 30 && text.matches("[A-Za-z][A-Za-z '\\-]*");
    }
    
        // a school only has one Principal
    public boolean hasPrincipal() {
        for (Employee e : employees) {
            if (e.getManagerType() == ManagerType.PRINCIPAL) {
                return true;
            }
        }
        return false;
    }

    // same name + same role + same department = duplicate
    public boolean isDuplicate(String firstName, String lastName, ManagerType type, Department dept) {
        String fullName = firstName.trim() + " " + lastName.trim();
        for (Employee e : employees) {
            if (e.getFullName().equalsIgnoreCase(fullName) && e.getManagerType() == type
                    && e.getDepartment() == dept) {
                return true;
            }
        }
        return false;
    }

    public Employee addEmployee(String firstName, String lastName, ManagerType type, Department dept) {
        Employee emp = new Employee(capitalise(firstName), capitalise(lastName), type, dept);
        employees.add(emp);
        newlyAdded.add(emp);
        sortByName(); // keep the list sorted so search still works
        return emp;
    }

    public List<Employee> getNewlyAdded() {
        return new ArrayList<>(newlyAdded);
    }

    // "kate" -> "Kate" (rest left as typed so O'Brien stays O'Brien)
    private String capitalise(String text) {
        String t = text.trim();
        return t.substring(0, 1).toUpperCase() + t.substring(1);
    }
}
