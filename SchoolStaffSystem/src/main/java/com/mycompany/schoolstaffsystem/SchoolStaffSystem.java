/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.schoolstaffsystem;
import com.mycompany.schoolstaffsystem.model.Employee;
import com.mycompany.schoolstaffsystem.model.EmployeeFileReader;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author shahz
 */
public class SchoolStaffSystem {

    public static void main(String[] args) {
       EmployeeFileReader reader = new EmployeeFileReader();
        try {
            List<Employee> staff = reader.read("Applicants_Form.txt");
            System.out.println("File read successfully: " + staff.size() + " staff loaded");
            for (Employee e : staff) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
