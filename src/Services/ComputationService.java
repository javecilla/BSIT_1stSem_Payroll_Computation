package Services;

import Exceptions.CodeNotFoundException;
import Exceptions.StatusNotFoundException;
import Providers.Rates;
import Providers.Employee;

/**
 * The ComputationService derived with Class Rates
 */
public class ComputationService extends Rates {
    private double hourlyRate;
    private double taxRate;
    private double grossPay;
    private double tax;
    private double netPay;

    /**
     * Calculates payroll details for the provided employee.
     *
     * @param employee The employee for whom payroll is to be calculated
     */
    public void calculatePayroll(Employee employee) {
        try {
            // Retrieve rate per hour by employee code
            String ratePerHourString = getRateByCode(employee.getCode());
            this.hourlyRate = Double.parseDouble(ratePerHourString);
            // Retrieve tax rate by dependent status
            String taxRateString = getTaxRateByStatus(employee.getTaxStatus());
            this.taxRate = Double.parseDouble(taxRateString);

            // Calculate grossPay, tax, and netPay
            this.grossPay = this.hourlyRate * employee.getHoursOfWork();
            this.tax = this.taxRate * this.grossPay;
            this.netPay = this.grossPay - this.tax;
        } catch (CodeNotFoundException | StatusNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Display the payroll summary.
     */
    public void displayPayroll(Employee employee) {
    	System.out.println("\n==================================================================");
    	System.out.println("\u001B[34m\u001B[1m\t\t\t    PAYROLL COMPUTATION v1.0\u001B[0m");
    	System.out.println("==================================================================");
    	System.out.println("\u001B[34m\u001B[1m\t\tEMPLOYEE INFORMATION\t\u001B[0m  |    \u001B[34m\u001B[1mPAYROLL SUMMARY\t\t\u001B[0m");
    	System.out.println("==================================|==================================");
    	System.out.println("\t\t\t\t  |");
    	System.out.println("\t\u001B[1mName: \u001B[0m" + employee.getName() + "\t  |\u001B[1m    Gross Pay: ₱\u001B[0m" + this.grossPay);
    	System.out.println("\t\u001B[1mCode: \u001B[0m" + employee.getCode() + "\t\t\t  |\u001B[1m    Total Tax: ₱\u001B[0m" + this.tax);
    	System.out.println("\t\u001B[1mStatus: \u001B[0m" + employee.getTaxStatus() + " |\u001B[1m    Net Pay: ₱\u001B[0m" + this.netPay);
    	System.out.println("\t\t\t\t  |");
    	System.out.println("==================================================================\n\n\n");
    }
}
