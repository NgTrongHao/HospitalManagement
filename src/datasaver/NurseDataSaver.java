/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datasaver;

import java.util.HashMap;
import data.Nurse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author ngtro
 */
public class NurseDataSaver {

    public static void saveNurseDataToFile(HashMap<String, Nurse> nurses, String fileName) {
        ArrayList<Nurse> nurseList = new ArrayList<>(nurses.values());

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Nurse nurse : nurseList) {
                String line = nurse.getId() + "," + nurse.getName() + "," + nurse.getAge() + "," + nurse.getGender() + ","
                        + nurse.getAddress() + "," + nurse.getPhone() + "," + nurse.getStaffID() + ","
                        + nurse.getDepartment() + "," + nurse.getShift() + "," + nurse.getSalary();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            System.out.println("Nures data has been saved!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
