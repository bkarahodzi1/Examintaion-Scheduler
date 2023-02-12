package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.Dao;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;

import java.io.Console;
import java.util.Scanner;

import static javax.xml.bind.DatatypeConverter.parseInteger;

public class Main {
    public static void main(String[] args) throws HospitalException {
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

    public static boolean CreateAccount() {
        do {
            System.out.println("Full name: ");
            Scanner in = new Scanner(System.in);
            String name = new String("");
            name = in.nextLine();
            if(name.equals(""))
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    CreateAccount();
                    break;
                }
                else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Seniority: ");
            Scanner in = new Scanner(System.in);
            String Seniority = new String("");
            Seniority = in.nextLine();
            int SenInt = Integer.parseInt(Seniority);
            if(Seniority.equals("") || Seniority.matches(".*[a-zA-z].*") || SenInt<0 || SenInt>45)
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    CreateAccount();
                    break;
                }
                else return false;
            }
            break;
        }while(true);
        do {
            System.out.println("Specialization: ");
            Scanner in = new Scanner(System.in);
            String Specialization = new String("");
            Specialization = in.nextLine();
            if(Specialization.equals("") || !Specialization.matches(".*[a-zA-z].*"))
            {
                System.out.println("To try again type 1. To go back type anything else.");
                String input = in.nextLine();
                if(input.equals("1")){
                    CreateAccount();
                    break;
                }
                else return false;
            }
        }while(true);
        return true;
    }

}
