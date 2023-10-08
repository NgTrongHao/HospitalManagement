/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ngtro
 */
public class Patient extends Person {

    private String diagnosis;
    private Date admissionDate;
    private Date dischargeDate;
    private HashMap<String, Nurse> nurseAssigned = new HashMap<>();

    public Patient(String diagnosis, Date admissionDate, Date dischargeDate, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public HashMap<String, Nurse> getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(HashMap<String, Nurse> nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void showProfile() {
        String msg;
        msg = String.format("|%6s|%-30s|%-20s|%-8s|%-8s|%-60s|", id, name, diagnosis, dateFormat.format(admissionDate), dateFormat.format(dischargeDate), nurseAssigned);
        System.out.println(msg);
    }
    
    
}
