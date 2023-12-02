package Main;

import java.util.Scanner;

import Exceptions.InvalidCredentialsException;
import Providers.Employee;
import Services.ComputationService;
import Services.AuthenticationService;
import Interfaces.Authentication;

/**
 * The main application class responsible for initiating the payroll computation and handling user authentication.
 */
public class App implements Authentication {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Entry point for the application. Initiates the login process and payroll computation.
     */
    public static void main(String[] args) {
        App app = new App();
        app.login(); 
        app.greet();
        
        Employee employee = new Employee();
        employee.readEmployeeInfo(SCANNER);
        employee.displayEmployeeInfo();
        
        ComputationService computationService = new ComputationService();
        computationService.calculatePayroll(employee);
        computationService.displayPayroll();
    }

    /**
     * Initiates the login process by prompting the user for credentials and performing authentication.
     */
    @Override
    public void login() {
        loginHeader();
        final int MAX_ATTEMPT = 3;
        int loginAttempt = 0;
        boolean isAuthenticated = false;
        
        while (loginAttempt < MAX_ATTEMPT) { 
            try {
                // Get username and password
                System.out.print("Enter username: ");
                String username = SCANNER.nextLine().toLowerCase();

                System.out.print("Enter password: ");
                String password = SCANNER.nextLine();
                
                // Check if either username or password is empty
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    System.out.println("All fields are required! Please enter username and password.");
                    continue; // Skip authentication attempt if fields are empty
                }
                
                AuthenticationService authService = new AuthenticationService();
                String validationResult = authService.validateLoginCredentials(username, password);
             
                // If login is successful, set isAuthenticated to true
                if (validationResult.equals("Login Successfuly!")) {
                    System.out.println("\n"+ validationResult + " Welcome back, " + username);
                    isAuthenticated = true;
                    break; // Exit the loop
                }
             // If login is unsuccessful, increment attempt count and display error message
                System.out.println(validationResult);
                
            } catch(InvalidCredentialsException e) {
                System.out.println(e.getMessage());
            }
            
            loginAttempt++;
             
            if (loginAttempt == MAX_ATTEMPT) {
                System.out.println("You have reached the maximum login attempts! "
                        + "\nDue to the repeated and many login attempts, "
                        + "\nlogin for your account is disabled.");
                break; // Exit the loop and program
            }
        }
        
        if (!isAuthenticated) {
            System.exit(0); // Exit the program if maximum attempts reached without successful login
        }
    }

    

    /**
     * Displays a welcome message to the user upon successful login.
     */
    public void greet() {
        System.out.println("\n==================================================================");
        System.out.println("\t\t\tWELCOME ADMIN\t\t\t\t");
        System.out.println("\t\t     Payroll Computation\t\t\t");
        System.out.println("==================================================================");
    }

    /**
     * Displays the header for the login prompt.
     */
    public static void loginHeader() {
        System.out.println("==================================================================");
        System.out.println("\t\t\t WELCOME TO THE LOGIN PORTAL\t\t\t");
        System.out.println("==================================================================");
        System.out.println("\nPlease enter your credentials to access your account:\n");
    }
}
