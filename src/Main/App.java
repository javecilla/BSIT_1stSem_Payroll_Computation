package Main;

import java.util.Scanner;

import Exceptions.InvalidPassCodeException;
import Providers.Employee;
import Services.ComputationService;
import Services.AuthenticationService;
import Interfaces.Authentication;

/**
 * Source Code Available at: [https://github.com/javecilla/Payroll_Computation]
 * 
 * The primary class handling the payroll computation system's execution, 
 * user interactions, and authentication procedures.
 * Implements the Authentication interface to ensure authentication protocols.
 */
public class App implements Authentication {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final App APP = new App();
    /**
     * Entry point for the application. 
     */
    public static void main(String[] args) {
    	displayWelcome();
    	APP.run();
    }
    
    /**
     * Runs the main application logic.
     */
    public void run() {
        displayMenu();
        processUserChoice();
    }

    /**
     * Processes the user's choice from the menu.
     */
    private static void processUserChoice() {
        boolean isValidChoice = false;
        while (!isValidChoice) {
            System.out.print("\t\t\t\u001B[1mENTER YOUR CHOICE: \u001B[0m");
            String choice = SCANNER.nextLine().toUpperCase();
            if (choice.trim().isEmpty()) {
    	        System.out.println("\tChoice is required! Please enter your choice.\n");
    	    } else {
    	        switch (choice) {
    	            case "A": //main application
    	            	APP.login(); 
    	                APP.greet();
    	             	 
    	                Employee employee = new Employee();
    	                employee.readEmployeeInfo(SCANNER);
    	                
    	                ComputationService computationService = new ComputationService();
    	                computationService.calculatePayroll(employee);
    	                computationService.displayPayroll(employee);
    	                isValidChoice = true; // Set the flag to exit the loop
    	                break;
    	            case "B": //about application
    	                APP.about();
    	                isValidChoice = true; 
    	                break;
    	            case "C": //credits team
    	            	APP.credits();
    	                isValidChoice = true; 
    	                break;
    	            case "D": //exit application
    	                APP.exit();
    	                isValidChoice = true; 
    	                break;
    	            default:
    	                System.out.println("\t\tInvalid choice! Please try again.\n");
    	                break;
    	        }
    	    }
        }
    }
    
    /**
     * Initiates the login process by prompting the user for credentials and performing authentication.
     */
    @Override
    public void login() {
    	System.out.println("\n==================================================================");
    	System.out.println("\u001B[34m\u001B[1m\t\t\t\t  PAYROLL PORTAL\u001B[0m");
    	System.out.println("==================================================================");
        System.out.println("\n\u001B[1m\t\tTo access the system, please enter the passcode.\n\u001B[0m");
        final int MAX_ATTEMPT = 3;
        int loginAttempt = 0;
        
        while (loginAttempt < MAX_ATTEMPT) { 
            try {
                // Get input passcode
            	System.out.print("\t\t\t\u001B[1mEnter passcode: \u001B[0m");
                String passcode = SCANNER.nextLine().toLowerCase();

                // Check if passcode field is empty
                if (passcode.trim().isEmpty()) {
                    System.out.println("\tPasscode is required! Please enter the passcode.\n");
                    continue; // Skip authentication attempt if fields are empty
                }
                
                AuthenticationService authService = new AuthenticationService();
                String validationResult = authService.validatePassCode(passcode);
             
                // If login is successful, set isAuthenticated to true
                if (validationResult.equals("success")) {
                    break; // Exit the loop
                }
             // If login is unsuccessful, increment attempt count and display error message
                System.out.println(validationResult);
                
            } catch(InvalidPassCodeException e) {
                System.out.println("\t\t\t"+e.getMessage()+"\n");
            }
            
            loginAttempt++;
             
            if (loginAttempt == MAX_ATTEMPT) {
                System.out.println("\t   You have been blocked from using the system!");
                System.exit(0); // Exit the loop and program
            }
        }
    }
    
    /**
     * Displays the welcome message upon starting the application.
     */
    private static void displayWelcome() {
        System.out.println("\n==================================================================");
        System.out.println("\u001B[34m\u001B[1m\t\t\tWELCOME TO PAYROLL COMPUTATION SYSTEM\t\t\t\u001B[0m");
        System.out.println("==================================================================");
    }
    
    /**
     * Displays the menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n\u001B[34m\u001B[1m\t\t\t\t\tMENU\t\t\t\u001B[0m");
        System.out.println("\n\t\t\u001B[1m[A]  - START \t\t[C] - CREDITS\t\t\t\n\n");
    	System.out.println("\t\t[B]  - ABOUT \t\t[D] - EXIT\t\t\t\n\n");
    }
    
    /**
     * Displays a short overview of this project
     */
    public void about() {
    	System.out.println("\n\u001B[34m\u001B[1m\t\t\t\t\tABOUT\t\t\t\u001B[0m\n");
    	System.out.println("\t\u001B[1mPayroll Computation System - \u001B[0mis a Java-based console"
    			+ "\n\tapplication meticulously crafted for precise employee"
    			+ "\n\tpayroll calculations. This program boasts an intuitive "
    			+ "\n\tuser payroll calculations. This program boasts an "
    			+ "\n\tintuitive user interface facilitating the seamless "
    			+ "\n\tinput of essential employee details, including name, "
    			+ "\n\tcode, dependent status, and hours of work."
    			+ "\n\n\tFueled by object-oriented programming paradigms and"
    			+ "\n\tadhering to SOLID principles, the system is structured"
    			+ "\n\twith modular components, ensuring optimal efficiency in "
    			+ "\n\tdata management and processing. This design approach "
    			+ "\n\tnot only enhances the system's reliability, scalable and "
    			+ "\n\tmaintainable software solutions.");
    }
    
    /**
     * Displays the proponents of this project
     */
    public void credits() {
    	System.out.println("\n\u001B[34m\u001B[1m\t\t\t\t\tCREDITS\t\t\t\u001B[0m\n");
    	System.out.println("\n\t\t\t      \u001B[1mPROGRAMMER:\u001B[0m");
    	System.out.println("\t\t\t   Jerome Avecilla");
    	System.out.println("\n\t\t\t      \u001B[1mRESEARCHER:\u001B[0m");
    	System.out.println("\t\t\t   Anthony Feliciano");
    	System.out.println("\n\t\t\t       \u001B[1mDESIGNER:\u001B[0m");
    	System.out.println("\t\t\t    Jansen Sipagan");
    	System.out.println("\n\t\t\t      \u001B[1mDOCUMENTATION:\u001B[0m");
    	System.out.println("\t\t\t      Jan Roy Cruz");
    	System.out.println("\t\t\t   Johanna Constantino");
    }
    
    /**
     * Terminate or Exit the program
     */
    public void exit() {
    	System.out.println("\n\t\t\tExiting application...");
    	System.exit(0);
    }

    /**
     * Displays a welcome message to the user upon successful login.
     */
    public void greet() {
    	System.out.println("\n==================================================================");
    	System.out.println("\u001B[34m\u001B[1m\t\t\t  PAYROLL COMPUTATION - CPANEL\t\t\t\u001B[0m");
    	System.out.println("==================================================================");
    }
}
