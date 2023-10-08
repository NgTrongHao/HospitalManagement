/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datasaver;

import java.util.HashMap;
import data.Patient;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import data.Nurse;
import java.text.SimpleDateFormat;

/**
 *
 * @author ngtro
 */
public class PatientDataSaver {

    public static void savePatientDatatoFile(HashMap<String, Patient> patients, String fileName) {
        ArrayList<Patient> patientList = new ArrayList<>(patients.values());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Patient patient : patientList) {
                String assignedNurse = getAssignedNurseString(patient.getNurseAssigned());
                String admissionDate = dateFormat.format(patient.getAdmissionDate());
                String dischargeDate = dateFormat.format(patient.getDischargeDate());
                
                String line = patient.getId() + "," + patient.getName() + "," + patient.getAge() + "," + patient.getGender() + ","
                        + patient.getAddress() + "," + patient.getPhone() + "," + patient.getDiagnosis() + "," + admissionDate + ","
                        + dischargeDate + "," + assignedNurse;
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            System.out.println("Patient data has been saved");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getAssignedNurseString(HashMap<String, Nurse> assignedNurse) {
        StringBuilder sb = new StringBuilder();
        for (Nurse nurse : assignedNurse.values()) {
            sb.append(nurse.getStaffID()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
