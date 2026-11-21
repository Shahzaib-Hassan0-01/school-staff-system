/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.schoolstaffsystem;

import com.mycompany.schoolstaffsystem.controller.SchoolController;
import com.mycompany.schoolstaffsystem.model.StaffRegister;
import com.mycompany.schoolstaffsystem.view.ConsoleView;

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

    }
}
