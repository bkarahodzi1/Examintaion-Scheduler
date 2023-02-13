package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws HospitalException, InterruptedException {
        String input;
        String username = new String();
        do {
            System.out.println("Press 1 to log in, 2 to create a new account or 0 to exit. Than press enter.");
            Scanner in = new Scanner(System.in);
            input = new String(in.nextLine());
            if (input.equals("1")) {
                if(!LogIn())continue;
                break;
            } else if (input.equals("2")) {
                if(!CreateAccount())continue;
                break;
            }
            else if (input.equals("0")){
                System.out.println("Goodbye.");
                return;
            }
            else System.out.println("Please follow the given instructions.");
        } while (true);
        Homepage(username);
    }


    private static boolean LogIn(){
        Scanner in = new Scanner(System.in);
        String password = new String ("");
        try {
            System.out.print("Username: ");
            String username = new String(in.nextLine());
            System.out.print("Password: ");
            /*Console console = System.console();
            char[] password = console.readPassword();*/
            Doctor doc = DaoFactory.DoctorDao().getByUsername(username);
            password = in.nextLine();
            if(!doc.getPassword().equals(password))throw new HospitalException("");
            return true;
        } catch (HospitalException e) {
            if(password.equals(""))in.nextLine();
            System.out.println("Incorrect username or password. To try again type 1. To go back type anything else.");
            String input = in.nextLine();
            if(input.equals("1"))
                LogIn();
            else return false;
        }
        return true;
    }



    private static boolean CreateAccount() throws HospitalException, InterruptedException {
        String specialization = new String("");
        String name = new String("");
        String seniority = new String("");
        String username = new String("");
        String password = new String("");
        String passrepeat = new String("");
        do {
            System.out.println("Full name: ");
            Scanner in = new Scanner(System.in);
            name = in.nextLine();
            if(name.equals(""))
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    continue;
                }
                else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Seniority: ");
            Scanner in = new Scanner(System.in);
            seniority = in.nextLine();
            int SenInt = Integer.parseInt(seniority);
            if(seniority.equals("") || seniority.matches(".*[a-zA-z].*") || SenInt<0 || SenInt>45)
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    continue;
                }
                else return false;
            }
            break;
        }while(true);
        do {
            List<String> Specializations = new ArrayList<String>(Arrays.asList("Anatomical Pathology", "Anesthesiology", "Cardiology", "Cardiovascular/Thoracic Surgery", "Clinical Immunology/Allergy", "Critical Care Medicine", "Dermatology", "Diagnostic Radiology", "Emergency Medicine", "Endocrinology and Metabolism", "Family Medicine", "Gastroenterology", "General Internal Medicine", "General Surgery", "General/Clinical Pathology", "Geriatric Medicine", "Hematology", "Medical Biochemistry", "Medical Genetics", "Medical Microbiology and Infectious Diseases", "Medical Oncology", "Nephrology", "Neurology", "Neurosurgery", "Nuclear Medicine", "Obstetrics/Gynecology", "Occupational Medicine", "Ophthalmology", "Orthopedic Surgery", "Otolaryngology", "Pediatrics", "Physical Medicine and Rehabilitation (PM & R)", "Plastic Surgery", "Psychiatry", "Public Health and Preventive Medicine (PhPm)", "Radiation Oncology", "Respirology", "Rheumatology", "Urology"));
            System.out.println("Specialization: ");
            Scanner in = new Scanner(System.in);
            specialization = in.nextLine();
            if(!Specializations.contains(specialization))
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    continue;
                }
                else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Username: ");
            Scanner in = new Scanner(System.in);
            username = in.nextLine();
            if(DaoFactory.DoctorDao().usernameExists(username)){
                System.out.println("Username already exists.\nTo try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if (input.equals("1")) {
                    continue;
                } else return false;
            }
            if (username.equals("")) {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if (input.equals("1")) {
                    continue;
                } else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Password (has to contain at least 5 characters, at least one letter and one number): ");
            Scanner in = new Scanner(System.in);
            password = in.nextLine();
            if (password.length()<5 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if (input.equals("1")) {
                    continue;
                } else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Repeat password: ");
            Scanner in = new Scanner(System.in);
            passrepeat = in.nextLine();
            if (!passrepeat.equals(password)) {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if (input.equals("1")) {
                    continue;
                } else return false;
            }
            break;
        }while(true);
        Doctor doctor = new Doctor();
        doctor.setName(name);
        int sen = Integer.parseInt(seniority);
        doctor.setSeniority(sen);
        doctor.setSpecialization(specialization);
        doctor.setUsername(username);
        doctor.setPassword(password);
        DaoFactory.DoctorDao().add(doctor);
        System.out.println("Account successfully made!\nRedirecting...");
        Thread.sleep(1000);
        LogIn();
        return true;
    }


    private static void Homepage(String username) throws HospitalException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome!\n");
        List<Patient> patients = DaoFactory.PatientDao().getAll();
        List<Examination> exams = DaoFactory.ExaminationDao().getByDoctor(username);
        do{
            System.out.println("Patient             Diagnosis");
            for (Examination e : exams) {
                for (Patient p : patients) {
                    if (e.getPatient().equals(p)) {
                        String space = " ";
                        if(p.getName().length()>=17) {
                            System.out.print(p.getName().substring(0,17));
                            System.out.print("...");
                        }
                        else System.out.print(p.getName());
                        System.out.print(space.repeat(20-p.getName().length()));
                        System.out.println(e.getDiagnosis());
                    }
                }
            }
            do{
                System.out.println("\nTo search for any patient type 1, to see your patient list again type 2, to see all patients type 3, to exit type 0");
                String input = new String("");
                input = in.nextLine();
                if (input.equals("0"))return;
                else if(input.equals("1")){
                    System.out.println("Search: ");
                    String search = new String(in.nextLine());
                    System.out.println("Patient             Place               Address             Phone number        Birth date          Health insurance");
                    for (Patient p : patients) {
                        if(p.getName().contains(search)){
                            String space = " ";
                            if (p.getName().length() >= 17) {
                                System.out.print(p.getName().substring(0, 17));
                                System.out.print("...");
                            } else System.out.print(p.getName());
                            System.out.print(space.repeat(20 - p.getName().length()));
                            if (p.getPlace().length() >= 17) {
                                System.out.print(p.getPlace().substring(0, 17));
                                System.out.print("...");
                            } else System.out.print(p.getPlace());
                            System.out.print(space.repeat(20 - p.getPlace().length()));
                            if (p.getAddress().length() >= 17) {
                                System.out.print(p.getAddress().substring(0, 17));
                                System.out.print("...");
                            } else System.out.print(p.getAddress());
                            System.out.print(space.repeat(20 - p.getAddress().length()));
                            System.out.print(p.getPhone_num());
                            if (p.getPhone_num() != null)
                                System.out.print(space.repeat(20 - p.getPhone_num().length()));
                            else System.out.print(space.repeat(16));
                            System.out.print(p.getBirth_date());
                            System.out.print(space.repeat(20 - p.getBirth_date().toString().length()));
                            System.out.println(p.isHealth_insurance());
                        }
                    }
                }
                else if(input.equals("2"))break;
                else if(input.equals("3")){
                    System.out.println("All patients: ");
                    for (Patient p : patients) {
                        String space = " ";
                        if (p.getName().length() >= 17) {
                            System.out.print(p.getName().substring(0, 17));
                            System.out.print("...");
                        } else System.out.print(p.getName());
                        System.out.print(space.repeat(20 - p.getName().length()));
                        if (p.getPlace().length() >= 17) {
                            System.out.print(p.getPlace().substring(0, 17));
                            System.out.print("...");
                        } else System.out.print(p.getPlace());
                        System.out.print(space.repeat(20 - p.getPlace().length()));
                        if (p.getAddress().length() >= 17) {
                            System.out.print(p.getAddress().substring(0, 17));
                            System.out.print("...");
                        } else System.out.print(p.getAddress());
                        System.out.print(space.repeat(20 - p.getAddress().length()));
                        System.out.print(p.getPhone_num());
                        if (p.getPhone_num() != null)
                            System.out.print(space.repeat(20 - p.getPhone_num().length()));
                        else System.out.print(space.repeat(16));
                        System.out.print(p.getBirth_date());
                        System.out.print(space.repeat(20 - p.getBirth_date().toString().length()));
                        System.out.println(p.isHealth_insurance());
                    }
                }
            }while(true);
        }while(true);
    }
}
