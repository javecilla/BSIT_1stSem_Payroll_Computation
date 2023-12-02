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
        System.out.print("\nEnter employee name: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Employee name is required!");
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
        System.out.print("Employee code: ");
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
            System.out.println(e.getMessage());
            obtainEmployeeCode(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Obtains and validates the employee's dependent status.
     * @param scanner Scanner object for user input
     */
    private void obtainEmployeeStatus(Scanner scanner) {
        System.out.println("[1] With Dependent\n[0] Without Dependent");
        System.out.print("Choose your status: ");
        this.taxStatus = scanner.nextLine().trim();
        try {
            if (this.taxStatus.isEmpty() || (!this.taxStatus.equals("1") && !this.taxStatus.equals("0"))) {
                throw new InvalidInputException("Invalid dependent status! Please enter valid status.");
            } else {
                this.taxStatus = (this.taxStatus.equals("1")) ? "With Dependent" : "Without Dependent";
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            obtainEmployeeStatus(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Displays employee information.
     */
    private void obtainHoursOfWork(Scanner scanner) {
        try {
            System.out.print("Enter hours of work: ");
            String input = scanner.nextLine().trim();
            this.hoursOfWork = Integer.parseInt(input);
            if (this.hoursOfWork <= 0) {
                throw new InvalidInputException("Invalid hours of work! Cannot be negative or zero.");
            }
        } catch (NumberFormatException | InvalidInputException e) {
            System.out.println("Invalid input! Please enter a valid integer for hours of work.");
            obtainHoursOfWork(scanner); // Recursive call to ensure valid input
        }
    }
    
    /**
     * Retrieves the employee code.
     * @return Employee code
     */
    public void displayEmployeeInfo() {
        System.out.println("\n==================================================================");
        System.out.println("\t\t\tPayrol Computation\t\t\t\t");
        System.out.println("\t\t\tDecember 02, 2023\t\t\t\t");
        System.out.println("==================================================================");
        System.out.println("\nEmployee Information:");
        System.out.println("Name: " + this.name);
        System.out.println("Code: " + this.code);
        System.out.println("Status: " + this.taxStatus);
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
