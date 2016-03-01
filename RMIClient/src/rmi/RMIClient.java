package rmi;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author matang
 */
 
public class RMIClient {

    private void start() {
        try {
            
            // fire to localhost port 2016
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 2016);
            // search for myMessage service
            Message impl = (Message) myRegistry.lookup("rmi");
            Scanner scanner = new Scanner(System.in);
            this.getUserInput(impl, scanner);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //get user input from command line
    private void getUserInput(Message impl, Scanner scanner) throws IOException {

        System.out.print("Enter 1 to Login and 2 to Register : ");
        String choice = scanner.next();

        try {
            if (Integer.parseInt(choice) == 1) {
                this.login(impl, scanner);
            } else if (Integer.parseInt(choice) == 2) {
                this.register(impl, scanner);
            } else {
                this.getUserInput(impl, scanner);
            }
        } catch (NumberFormatException e) {
            this.getUserInput(impl, scanner);
        }
    }

    // validate user credentials and login
    private void login(Message impl, Scanner scanner) throws IOException {

        String adminUserName = "admin";
        System.out.print("Enter username : ");
        String userName = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();

        // call server's method
        int response = impl.login(userName, password);
        if (response == 0) {
            System.out.println("Invalid username or password");
        } else {

            if (userName.equals(adminUserName)) {
                System.out.println("Total registered user count " + response);
            } else {
                System.out.println("Welcome " + userName);
            }
        }
    }

    //register a new user
    private void register(Message impl, Scanner scanner) throws IOException {
        System.out.print("Enter username : ");
        String userName = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();

        // call server's method
        int response = impl.register(userName, password);
        if (response == 0) {
            System.out.println("Username already exists. Try different username");
            this.register(impl, scanner);
        } else {
            this.askLogin(impl, scanner);
        }
    }

    // ask user to login after registeration
    private void askLogin(Message impl, Scanner scanner) throws IOException {
        System.out.print("Do you need to login ? (yes/no)");
        String subChoice = scanner.next();
        if (subChoice.equals("yes")) {
            this.login(impl, scanner);
        } else if (subChoice.equals("no")) {
        } else {
            System.out.println("Invalid choice");
            this.askLogin(impl, scanner);
        }
    }

    public static void main(String[] args) {
        RMIClient main = new RMIClient();
        main.start();
    }
}
