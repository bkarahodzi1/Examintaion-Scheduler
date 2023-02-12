package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws HospitalException {
        String input;
        do {
            String username = new String();
            System.out.println("Press 1 to log in, 2 to create a new account or 0 to exit. Than press enter.");
            Scanner in = new Scanner(System.in);
            input = new String(in.nextLine());
            if (input.equals("1")) {
                LogIn();
                break;
            } else if (input.equals("2")) {
                CreateAccount();
                break;
            }
            else if (input.equals("0")){
                System.out.println("Goodbye.");
                return;
            }
            else System.out.println("Please follow the given instructions.");
        } while (true);
    }
    public static void LogIn(){

    }

    public static void CreateAccount() {

    }
}
