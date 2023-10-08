/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalmanagementv5;

import data.Hospital;
import data.Nurse;
import data.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import menu.Menu;
import util.TryCatch;

/**
 *
 * @author ngtro
 */
public class HospitalManagementV5 {

    public static void main(String[] args) {
        HospitalManagementProgram();
    }

    public static void HospitalManagementProgram() {

        Hospital hospital = new Hospital();

        Menu hospitalMenu = new Menu("Hospital Menu");
        hospitalMenu.addNewOption("1. Nurse's Management");
        hospitalMenu.addNewOption("2. Patient's Management");
        hospitalMenu.addNewOption("3. Print nurse's list");
        hospitalMenu.addNewOption("4. Print patient's list");
        hospitalMenu.addNewOption("5. Load data from file");
        hospitalMenu.addNewOption("6. Save data");
        hospitalMenu.addNewOption("7. Exit program!");

        int choice;
        do {
            System.out.println("___________________________________________");
            hospitalMenu.printMenu();
            choice = hospitalMenu.getChoice();
            switch (choice) {
                case 1:
                    NurseMenu(hospital);
                    break;
                case 2:
                    PatientMenu(hospital);
                    break;
                case 3:
                    for (Nurse nurse : hospital.nurses.values()) {
                        nurse.showProfile();
                    }
                    break;
                case 4:
                    for (Patient patient : hospital.patients.values()) {
                        patient.showProfile();
                    }
                    break;
                case 5:
                    boolean checkSuccess = hospital.loadFile();
                    if (checkSuccess == true) {
                        System.out.println("\nLoad Success");
                    } else {
                        System.out.println("Fail!");
                    }
                    break;
                case 6:
                    hospital.saveFile();
                    break;

            }
        } while (choice != 7);

    }

    public static void NurseMenu(Hospital hospital) {
        Menu nurseMenu = new Menu("Nurse's Management");
        nurseMenu.addNewOption("1. Create a nurse");
        nurseMenu.addNewOption("2. Find a nurse");
        nurseMenu.addNewOption("3. Update the nurse");
        nurseMenu.addNewOption("4. Delete a nurse");
        nurseMenu.addNewOption("5. Nurse Salary");
        nurseMenu.addNewOption("6. Back to Hospital Management Program Menu");

        int nursechoice;
        do {
            nurseMenu.printMenu();
            nursechoice = nurseMenu.getChoice();
            switch (nursechoice) {
                case 1:
                    boolean checkSuccess = hospital.addNurse();
                    if (checkSuccess == true) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("Add fail!");
                    }
                    break;
                case 2:
                    Nurse nurse = hospital.findNurse(TryCatch.getString("Input the nurse's name that you want to find: ", "This field is required!"));
                    if (nurse != null) {
                        System.out.println("This nurse that you find: ");
                        nurse.showProfile();
                    } else {
                        System.out.println("This nurse does not exist!");
                    }
                    break;
                case 3:
                    hospital.updateANurse();
                    break;
                case 4:
                    boolean checkDelete;
                    checkDelete = hospital.deleteANurse();
                    if (checkDelete == true) {
                        System.out.println("Delete Success");
                    } else {
                        System.out.println("Delete Fail!");
                    }
                    break;
                case 5:
                    HashMap<String, Nurse> nurseSalary = new HashMap<>();
                    double salary = TryCatch.getADouble("Input salary", "This field is required!");
                    nurseSalary = hospital.findNurseBySalary(salary);
                    
                    ArrayList<Nurse> nurselist = new ArrayList<>(nurseSalary.values());
                    Comparator<Nurse> nurseNameComparator = new Comparator<Nurse>() {
                @Override
                public int compare(Nurse o1, Nurse o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
                    };
                    Collections.sort(nurselist, nurseNameComparator);
                    
//                    for (Nurse x : nurseSalary.values()) {
//                        x.showProfile();
//                    }
                    for (Nurse object : nurselist) {
                        object.showProfile();
                    }

            }
        } while (nursechoice != 6);
        System.out.println("");
    }

    public static void PatientMenu(Hospital hospital) {

        Menu patientMenu = new Menu("Patient's Manage");
        patientMenu.addNewOption("1. Add patient");
        patientMenu.addNewOption("2. Display patients");
        patientMenu.addNewOption("3. Sort the patient list");
        patientMenu.addNewOption("4. Back to Hospital Manage");

        int patientchoice;
        do {
            patientMenu.printMenu();
            patientchoice = patientMenu.getChoice();
            switch (patientchoice) {
                case 1:
                    boolean checkSuccess = hospital.addPatient();
                    ;
                    if (checkSuccess == true) {
                        System.out.println("Add success!");
                    } else {
                        System.out.println("Add fail!");
                    }
                    break;
                case 2:
                    hospital.displayPatient(TryCatch.getDate("Input start date: ", "This field is required! (dd/mm/yyyy)"), TryCatch.getDate("Input end date: ", "This field is required! (dd/mm/yyyy)"));
                    break;
                case 3:
                    String sort = TryCatch.getString("Input your choice (ASC, DESC): ", "This field is required!");
                    if (sort.equalsIgnoreCase("ASC")) {
                        hospital.sortPatientByID();
                    }
                    if (sort.equalsIgnoreCase("DESC")) {
                        hospital.sortPatientByIDDescending();
                    }
                    break;
            }
        } while (patientchoice != 4);
        System.out.println("");
    }

}
