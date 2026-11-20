/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.schoolstaffsystem;
import com.mycompany.schoolstaffsystem.model.Employee;
import com.mycompany.schoolstaffsystem.model.EmployeeFileReader;
import com.mycompany.schoolstaffsystem.controller.SchoolController;
import com.mycompany.schoolstaffsystem.model.StaffRegister;
import com.mycompany.schoolstaffsystem.view.ConsoleView;
import java.io.IOException;
import java.util.List;
/**
 * Riverside Community School - staff management system (CA2)
 *
 * @author shahz
 */
public class SchoolStaffSystem {

    public static void main(String[] args) {
       StaffRegister model = new StaffRegister();
       ConsoleView view = new ConsoleView();
       SchoolController controller = new SchoolController(model, view);
       controller.run();
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
