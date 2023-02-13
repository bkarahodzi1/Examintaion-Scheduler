package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.Dao;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static javax.xml.bind.DatatypeConverter.parseInteger;

public class Main {
    public static void main(String[] args) throws HospitalException, InterruptedException {
        String input;
        do {
            String username = new String();
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
    }
    public static boolean LogIn(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Username: ");
            String username = new String(in.nextLine());
            System.out.print("Password: ");
            /*Console console = System.console();
            char[] password = console.readPassword();*/
            Doctor doc = DaoFactory.DoctorDao().getByUsername(username);
            String password = new String (in.nextLine());
            if(!doc.getPassword().equals(password))throw new HospitalException("");

            return true;
        } catch (HospitalException e) {
            in.nextLine();
            System.out.println("Incorrect username or password. To try again type 1. To go back type anything else.");
            String input = in.nextLine();
            if(input.equals("1"))
                LogIn();
            else return false;
        }
        return true;
    }

    public static boolean CreateAccount() throws HospitalException, InterruptedException {
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

}
