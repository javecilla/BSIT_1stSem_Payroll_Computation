package Providers;

import java.util.Scanner;
import Exceptions.CodeNotFoundException;
import Exceptions.InvalidInputException;

/**
 * Represents an employee and manages their information.
 */
public class Employee {
	private String name;
    private String code;
    private String taxStatus;
    private int hoursOfWork;
    
    /**
     * Reads and validates user input to gather employee information.
     * @param scanner Scanner object for user input
     */
    public void readEmployeeInfo(Scanner scanner) {
        obtainEmployeeName(scanner);
        obtainEmployeeCode(scanner);
        obtainEmployeeStatus(scanner);
        obtainHoursOfWork(scanner);
    }
    
    /**
     * Obtains and validates the employee's name.
     * @param scanner Scanner object for user input
     */
    private void obtainEmployeeName(Scanner scanner) {
        System.out.print("\n\t\t\u001B[1mEnter employee name: \u001B[0m");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("\t\tEmployee name is required!");
            obtainEmployeeName(scanner); // Recursive call to ensure valid input
        } else {
            this.name = input;
        }
    }
    
    /**
     * Obtains and validates the employee's code.
     * @param scanner Scanner object for user input
     */
    private void obtainEmployeeCode(Scanner scanner) {
    	System.out.print("\n\t\t\u001B[1mEnter employee code: \u001B[0m");
        this.code = scanner.nextLine().toUpperCase().trim();
        try {
            if (this.code.isEmpty()) {
                throw new InvalidInputException("Employee code is required!");
            } else {
                String result = new Rates().getRateByCode(this.code);
                if (result.isEmpty()) {
                    System.out.println(result);
                    obtainEmployeeCode(scanner); // Recursive call to ensure valid input
                }
            }
        } catch (InvalidInputException | CodeNotFoundException e) {
            System.out.println("\t\t"+e.getMessage() + "\n");
            obtainEmployeeCode(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Obtains and validates the employee's dependent status.
     * @param scanner Scanner object for user input
     */
    private void obtainEmployeeStatus(Scanner scanner) {
        System.out.println("\n\u001B[1m\t\t\t[1] With Dependent\n\t\t[0] Without Dependent");
        System.out.print("\t\tChoose your status: \u001B[0m");
        this.taxStatus = scanner.nextLine().trim();
        try {
            if (this.taxStatus.isEmpty() || (!this.taxStatus.equals("1") && !this.taxStatus.equals("0"))) {
                throw new InvalidInputException("\t\tInvalid dependent status!");
            } else {
                this.taxStatus = (this.taxStatus.equals("1")) ? "With Dependent" : "Without Dependent";
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage() + "\n");
            obtainEmployeeStatus(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Displays employee information.
     */
    private void obtainHoursOfWork(Scanner scanner) {
        try {
        	System.out.print("\n\t\t\u001B[1mEnter hours of work: \u001B[0m");
            String input = scanner.nextLine().trim();
            this.hoursOfWork = Integer.parseInt(input);
            if (this.hoursOfWork <= 0) {
                throw new InvalidInputException("Invalid hours of work!");
            }
        } catch (NumberFormatException | InvalidInputException e) {
            System.out.println("\t\tInvalid hours of work!");
            obtainHoursOfWork(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Retrieves the employee code.
     * @return Employee code
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Retrieves the employee code.
     * @return Employee code
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Retrieves the employee's tax status.
     * @return Tax status of the employee
     */
    public String getTaxStatus() {
        return this.taxStatus;
    }
    
    /**
     * Retrieves the hours of work of the employee.
     * @return Hours of work
     */
    public int getHoursOfWork() {
        return this.hoursOfWork;
    }
}
