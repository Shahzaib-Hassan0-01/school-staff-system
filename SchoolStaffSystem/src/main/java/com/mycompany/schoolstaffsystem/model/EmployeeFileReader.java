package com.mycompany.schoolstaffsystem.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads Applicants_Form.txt and makes an Employee from each line.
 * Columns: First name, Last name, Gender, Email, Salary, Department, Position, Manager Type, School
 *
 * @author shahz
 */
public class EmployeeFileReader {

    private List<String> skippedLines = new ArrayList<>();

    public List<Employee> read(String fileName) throws IOException {
        List<Employee> employees = new ArrayList<>();
        skippedLines.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // skip the header
            int lineNo = 1;

            while ((line = reader.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                // -1 so empty columns are kept
                String[] parts = line.split(",", -1);
                if (parts.length < 9) {
                    skippedLines.add("line " + lineNo + " - missing columns");
                    continue;
                }

                Department dept = Department.fromInput(parts[5]);
                ManagerType type = ManagerType.fromInput(parts[7]);
                if (dept == null || type == null) {
                    skippedLines.add("line " + lineNo + " - unknown department or manager type");
                    continue;
                }

                double salary = 0;
                try {
                    salary = Double.parseDouble(parts[4].trim());
                } catch (NumberFormatException e) {
                    // leave it as 0
                }

                employees.add(new Employee(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                        parts[3].trim(), salary, dept, parts[6].trim(), type, parts[8].trim()));
            }
        }
        return employees;
    }

    public List<String> getSkippedLines() {
        return skippedLines;
    }
}
