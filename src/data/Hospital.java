/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import dataloader.NurseDataLoader;
import dataloader.PatientDataLoader;
import datasaver.NurseDataSaver;
import datasaver.PatientDataSaver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import menu.Menu;
import util.TryCatch;

/**
 *
 * @author ngtro
 */
public class Hospital {

    public HashMap<String, Nurse> nurses = new HashMap<>();
    public HashMap<String, Patient> patients = new HashMap<>();

    public boolean loadFile() {
        nurses.putAll(NurseDataLoader.loadNurses("NurseList.txt"));
        patients.putAll(PatientDataLoader.loadPatient("PatientList.txt"));
        for (Nurse nurse : nurses.values()) {
            for (Patient patient : patients.values()) {
                if (patient.getNurseAssigned().containsKey(nurse.getStaffID())) {
                    nurse.getAssignedPatient().put(patient.getId(), patient);
                }
            }
        }
        System.out.println("\n*** Nurse List ***");
        for (Nurse nurse : nurses.values()) {
            nurse.showProfile();
        }
        System.out.println("\n*** Patient List ***");
        for (Patient patient : patients.values()) {
            patient.showProfile();
        }
        return true;
    }

    public void saveFile() {
        NurseDataSaver.saveNurseDataToFile(nurses, "NursList.txt");
        PatientDataSaver.savePatientDatatoFile(patients, "PatientList.txt");
    }

    public boolean addNurse() {
        String id, name, gender, address, phone;
        int age;
        String staffID, department, shift;
        double salary;

        boolean existID;
        do {
            id = TryCatch.getID("Input ID: ", "This field is required!", "^[(P)|(p)]*[0-9]{6}$");
            existID = existID(id);
            if (existID == true) {
                System.out.println("This ID is exist! Input another one!");
            }
        } while (existID == true);

        name = TryCatch.getString("Input name: ", "This field is required!");
        gender = TryCatch.getString("Input gender (Male | Female | Other): ", "This field is required!", "(?i)^(Male|Female|Other)$");
        address = TryCatch.getString("Input address: ", "This field is required!");
        phone = TryCatch.getString("Input phone number: ", "This field is required!", "^[0-9]{10}$");
        age = TryCatch.getAnInteger("Input age: ", "This field is required!", 0, 200);

        Person existStaffID;
        do {
            staffID = TryCatch.getID("Input Staff ID: ", "This field is required!", "^[(N)|(n)]*[0-9]{6}$");
            existStaffID = existNurseByStaffID(staffID);
            if (existStaffID != null) {
                System.out.println("This ID existed! Input another one!");
            }
        } while (existStaffID != null);

        department = TryCatch.getString("Input nurse's department: ", "This field is required!");
        shift = TryCatch.getString("Input working shift: ", "This field is required!");
        salary = TryCatch.getADouble("Input nurse's salary: ", "This field is required!");

        Nurse nurse = new Nurse(staffID, department, shift, salary, id, name, age, gender, address, phone);
        nurse.showProfile();
        nurses.put(staffID, nurse);

        return true;
    }

    public Nurse findNurse(String name) {
        for (Nurse nurse : nurses.values()) {
            if (nurse.getName().toUpperCase().contains(name.toUpperCase())) {
                return nurse;
            }
        }
        return null;
    }
    
    public HashMap <String, Nurse> findNurseBySalary(double salary) {
        HashMap<String, Nurse> nurseSalary = new HashMap<>();
        for (Nurse nurse : nurses.values()) {
            if(salary == nurse.getSalary()) {
                nurseSalary.put(nurse.getStaffID(), nurse);
            }
        }
        return nurseSalary;
    }

    public void updateANurse() {
        String staffID;
        staffID = TryCatch.getID("Input Staff ID that you want to update: ", "This field is required! Try again! (Nxxxxxx))", "^[(N)|(n)]*[0-9]{6}$");
        Nurse nurse = existNurseByStaffID(staffID);
        if (nurse == null) {
            System.out.println("This nurse does not exist!");
        } else {
            Menu updateNurseMenu = new Menu("Update Nurse");
            updateNurseMenu.addNewOption("1. Update Personal's ID");
            updateNurseMenu.addNewOption("2. Update name");
            updateNurseMenu.addNewOption("3. Update age");
            updateNurseMenu.addNewOption("4. Update gender");
            updateNurseMenu.addNewOption("5. Update address");
            updateNurseMenu.addNewOption("6. Update phone number");
            updateNurseMenu.addNewOption("7. Update staff ID");
            updateNurseMenu.addNewOption("8. Update department");
            updateNurseMenu.addNewOption("9. Update working shift");
            updateNurseMenu.addNewOption("10. Update salary");
            updateNurseMenu.addNewOption("11. Update assigned patient list");
            updateNurseMenu.addNewOption("12. Back to Nurse's Management");

            int updateMenuChoice;
            do {
                updateNurseMenu.printMenu();
                updateMenuChoice = updateNurseMenu.getChoice();
                switch (updateMenuChoice) {
                    case 1:
                        String inputPersonalID;
                        inputPersonalID = TryCatch.getID("Input updating ID: ", "This field is required! (Pxxxxxx)", "^[(P)|(p)]*[0-9]{6}$");
                        boolean existID;
                        existID = existID(inputPersonalID);
                        if (existID == true) {
                            System.out.println("This ID is exist!");
                        } else {
                            nurse.setId(inputPersonalID);
                            System.out.println("Update success!");
                        }
                        break;
                    case 2:
                        String inputName;
                        inputName = TryCatch.getString("Input updating nurse's name: ", "This field is required!");
                        nurse.setName(inputName);
                        System.out.println("Update success!");
                        break;
                    case 3:
                        int inputAge;
                        inputAge = TryCatch.getAnInteger("Input updating age: ", "This field is required!", 0, 200);
                        nurse.setAge(inputAge);
                        System.out.println("Update success!");
                        break;
                    case 4:
                        String inputGender;
                        inputGender = TryCatch.getString("Input updating gender( Male | Female | Other): ", "This field is required!", "(?i)^(Male|Female|Other)$");
                        nurse.setGender(inputGender);
                        System.out.println("Update success!");
                        break;
                    case 5:
                        String inputAddress;
                        inputAddress = TryCatch.getString("Input updating address: ", "This fields is required!");
                        nurse.setAddress(inputAddress);
                        System.out.println("Update success!");
                        break;
                    case 6:
                        String inputPhone;
                        inputPhone = TryCatch.getString("Input updating phone number: ", "This field is required!", "^[0-9]{10}$");
                        nurse.setPhone(inputPhone);
                        System.out.println("Update success!");
                        break;
                    case 7:
                        String inputStaffID;
                        inputStaffID = TryCatch.getID("Input updating staff ID: ", "This field is required! (Nxxxxxx)", "^[(N)|(n)]*[0-9]{6}$");
                        Nurse existStaffID;
                        existStaffID = existNurseByStaffID(staffID);
                        if (existStaffID == null) {
                            nurse.setStaffID(inputStaffID);
                            System.out.println("Update success!");
                        } else {
                            System.out.println("This staff ID is existed");
                        }
                        break;
                    case 8:
                        String inputDepartment;
                        inputDepartment = TryCatch.getString("Input updating department: ", "This field is required!");
                        nurse.setDepartment(inputDepartment);
                        System.out.println("Update success!");
                        break;
                    case 9:
                        String inputShift;
                        inputShift = TryCatch.getString("Input updating working shift: ", "This field is required!");
                        nurse.setDepartment(inputShift);
                        System.out.println("Update success!");
                        break;
                    case 10:
                        Double inputSalary;
                        inputSalary = TryCatch.getADouble("Input updating salary: ", "This field is required!");
                        nurse.setSalary(inputSalary);
                        System.out.println("Update success!");
                        break;
                    case 11:
                        HashMap<String, Patient> inputAssignedPatient = new HashMap<>();
                        String id1;
                        Patient patient1;
                        boolean existIDPatient;
                        do {
                            id1 = TryCatch.getID("Input patient's personal ID: ", "This field is required", "^[(P)|(p)]*[0-9]{6}$");
                            existIDPatient = existIDPatient(id1);
                            if (existIDPatient == false) {
                                System.out.println("This patient not exist!");
                            } else {
                                patient1 = patients.get(id1);
                                patient1.showProfile();
                                if (patient1.getNurseAssigned().size() >= 2) {
                                    System.out.println("This patient has been taken care of by 2 nurse already!");
                                } else {
                                    inputAssignedPatient.put(id1, patient1);
                                }
                            }
                        } while (existIDPatient == false);
                        
                        nurse.setAssignedPatient(inputAssignedPatient);
                        System.out.println("Update success!");
                        break;
                }
                nurse.showProfile();
            } while (updateMenuChoice != 12);
        }
    }

    public boolean deleteANurse() {
        String staffID;
        staffID = TryCatch.getID("Input Staff ID that you want to delete: ", "Try again! (Nxxxxxx)", "^[(N)|(n)]*[0-9]{6}$");
        Nurse nurse = existNurseByStaffID(staffID);
        if (nurse == null) {
            System.out.println("This nurse's ID " + staffID + " does not exist!");
            return false;
        } else {
            nurse.showProfile();
            if (nurse.getAssignedPatient().isEmpty()) {
                String confirmation = TryCatch.getString("Are you sure to delete this nurse? (YES | NO) :", "This field is required!");
                if (confirmation.equalsIgnoreCase("YES")) {
                    nurses.remove(staffID);
                    return true;  
                } else {
                    return false;
                }
            } else {
                System.out.println("This nurse still has task to do");
                return false;
            }
        }
    }
    

    public boolean addPatient() {
        String id, name, gender, address, phone, diagnosis;
        int age;
        Date admissionDate, dischargeDate;
        HashMap<String, Nurse> nurseAssigned = new HashMap<>();

        boolean existID;
        do {
            id = TryCatch.getID("Input ID: ", "This field is required!", "^[(P)|(p)]*[0-9]{6}$");
            existID = existID(id);
            if (existID == true) {
                System.out.println("This ID existed! Try another one!");
            }
        } while (existID == true);

        name = TryCatch.getString("Input patient's name: ", "This field is required!");
        gender = TryCatch.getString("Input gender (Male | Female | Other): ", "This field is required!", "(?i)^(Male|Female)$");
        address = TryCatch.getString("Input address: ", "This field is required!");
        phone = TryCatch.getString("Input phone number: ", "This field is required!", "^[0-9]{10}$");
        age = TryCatch.getAnInteger("Input patient's age: ", "This field is required!", 0, 200);
        diagnosis = TryCatch.getString("Input patient's diagnosis: ", "This field is required!");
        admissionDate = TryCatch.getDate("Input admission date: ", "This field is required! (dd/mm/yyyy)");
        dischargeDate = TryCatch.getDate("Input discharge date: ", "This field is required! (dd/mm/yyyy)");
        
        Patient patient = new Patient(diagnosis, admissionDate, dischargeDate, id, name, age, gender, address, phone);
        
        
        while (patient.getNurseAssigned().size() < 2) {
            boolean addSuccess = true;
            do {
                Nurse nurse = null;
                try {
                    System.out.println("This is nurse list: ");
                    if (nurses != null) {
                        for (Nurse x : nurses.values()) {
                            x.showProfile();
                        }
                        System.out.println("This is valid nurse to take care of this patient: ");
                        for (Nurse x : nurses.values()) {
                            if (x.getAssignedPatient().size() < 2 && patient.getNurseAssigned().containsValue(x) == false) {
                                x.showProfile();
                            }
                        }
                    }
                    String nurseStaffID = TryCatch.getID("Input nurse's staff ID for this patient: ", "This field is required!", "^[(N)|(n)]*[0-9]{6}$");
                    nurse = existNurseByStaffID(nurseStaffID);
                    if (nurse != null && nurse.getAssignedPatient().size() < 2) {
                        patient.getNurseAssigned().put(nurseStaffID, nurse);
                        for (Nurse x : nurses.values()) {
                            if (x.getStaffID().equalsIgnoreCase(nurseStaffID)) {
                                x.getAssignedPatient().put(id, patient);
                            }
                        }
                        
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    if (nurse == null) {
                        System.out.println("This nurse's staff ID is not exist!");
                    } else if (nurse.getAssignedPatient().size() >= 2) {
                        System.out.println("This nurse too busy to take more task!");
                    } else if (patient.getNurseAssigned().containsValue(nurse)){
                        System.out.println("This nurse has taken care this patient already!");
                    }
                }
                System.out.println("");
            } while (addSuccess != true);
        }
        
        patients.put(id, patient);
        
        return true;
    }

    public void displayPatient(Date startDate, Date endDate) {
        System.out.println("List of Patient");
        System.out.println("Star date: " + startDate);
        System.out.println("End date: " + endDate);
        for (Patient patient : patients.values()) {
            if (patient.getAdmissionDate().compareTo(startDate) >= 0 && patient.getAdmissionDate().compareTo(endDate) <= 0) {
                patient.showProfile();
            }
        }
    }

    public void sortPatientByID() {
        ArrayList<Patient> patientList = new ArrayList<>(patients.values());
        Comparator<Patient> patientComparator = new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
        Collections.sort(patientList, patientComparator);
        System.out.println("Patient List sorting by asceding name:");
        for (Patient patient : patientList) {
            patient.showProfile();
        }
    }

    public void sortPatientByIDDescending() {
        ArrayList<Patient> patientList = new ArrayList<>(patients.values());
        Comparator<Patient> patientComparator = new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                // Sử dụng compareTo() đảo ngược để so sánh ID giảm dần
                return p2.getName().compareTo(p1.getName());
            }
        };
        Collections.sort(patientList, patientComparator);
        System.out.println("Patient List sorting by descending ID:");
        for (Patient patient : patientList) {
            patient.showProfile();
        }
    }

    public Nurse existNurseByStaffID(String staffID) {
        if (nurses.isEmpty()) {
            return null;
        }
        if (nurses.containsKey(staffID)) {
            return nurses.get(staffID);
        }
        return null;
    }

    public boolean existID(String id) {
        if (nurses.isEmpty() && patients.isEmpty()) {
            return false;
        }

        if (nurses.isEmpty() != true) {
            for (Person person : nurses.values()) {
                if (person.getId().contains(id)) {
                    return true;
                }
            }
        }

        if (nurses.isEmpty() != true) {
            for (Person person : patients.values()) {
                if (person.getId().contains(id)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public boolean existIDPatient (String id) {
        if (patients.isEmpty()){
            return false;
        }
        if (patients.isEmpty() != true) {
            for (Patient patient : patients.values()) {
                if (patient.getId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

}
