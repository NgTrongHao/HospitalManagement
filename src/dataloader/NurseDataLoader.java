/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataloader;

import data.Nurse;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.HashMap;

/**
 *
 * @author ngtro
 */
public class NurseDataLoader {

    public static HashMap<String, Nurse> loadNurses(String fileName) {

        HashMap<String, Nurse> nurses = new HashMap<>();
        String[] arr;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                String id = arr[0].trim();
                String name = arr[1].trim();
                int age = Integer.parseInt(arr[2].trim());
                String gender = arr[3].trim();
                String address = arr[4].trim();
                String phone = arr[5].trim();
                String staffID = arr[6].trim();
                String department = arr[7].trim();
                String shift = arr[8].trim();
                double salary = Double.parseDouble(arr[9].trim());

                Nurse nurse = new Nurse(staffID, department, shift, salary, id, name, age, gender, address, phone);

                nurses.put(staffID, nurse);

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return nurses;
    }

}
