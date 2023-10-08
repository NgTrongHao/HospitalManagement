/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.HashMap;

/**
 *
 * @author ngtro
 */
public class Nurse extends Person {

    private String staffID;
    private String department;
    private String shift;
    private double salary;
    private HashMap<String, Patient> assignedPatient = new HashMap<>();

    public Nurse(String staffID, String department, String shift, double salary, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public HashMap<String, Patient> getAssignedPatient() {
        return assignedPatient;
    }

    public void setAssignedPatient(HashMap<String, Patient> assignedPatient) {
        this.assignedPatient = assignedPatient;
    }

    @Override
    public void showProfile() {
        String msg;
        msg = String.format("|%6s|%-30s|%-20s|%-8s|%10s|", staffID, name, department, shift, salary + "$");
        System.out.println(msg);
    }

}
