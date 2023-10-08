/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataloader;

import data.Nurse;
import java.util.HashMap;
import data.Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ngtro
 */
public class PatientDataLoader {

    public static HashMap<String, Patient> loadPatient(String fileName) {

        HashMap<String, Patient> patients = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        HashMap<String, Nurse> nurses = NurseDataLoader.loadNurses("C:\\Users\\ngtro\\OneDrive\\Máy tính\\HospitalManagementV5\\NurseList.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] arr;
            while (line != null) {
                arr = line.split(",");
                String id = arr[0].trim();
                String name = arr[1].trim();
                int age = Integer.parseInt(arr[2]);
                String gender = arr[3].trim();
                String address = arr[4].trim();
                String phone = arr[5].trim();
                String diagnosis = arr[6].trim();
                Date admissionDate = dateFormat.parse(arr[7].trim());
                Date dischargeDate = dateFormat.parse(arr[8].trim());
                
                
                String nurseId1 = arr[9].trim();
                String nurseId2 = arr[10].trim();
                HashMap<String, Nurse> nurseAssigned = new HashMap<>();
                Nurse nurse1, nurse2;
                if (!nurseId1.isEmpty()) {
                    nurse1 = nurses.get(nurseId1);
                    if (nurse1 != null) {
                        nurseAssigned.put(nurseId1, nurse1);

                    }
                }
                if (!nurseId2.isEmpty()) {
                    nurse2 = nurses.get(nurseId2);
                    if (nurse2 != null) {
                        nurseAssigned.put(nurseId2, nurse2);
                    }
                }

                Patient patient = new Patient(diagnosis, admissionDate, dischargeDate, id, name, age, gender, address, phone);
                patient.setNurseAssigned(nurseAssigned);
                patients.put(id, patient);

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return patients;
    }
}
