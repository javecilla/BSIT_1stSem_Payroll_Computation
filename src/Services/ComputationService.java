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
    public void displayPayroll() {
        System.out.println("\n==================================================================");
        System.out.println("\t\t\tPayroll Summary\t\t\t\t");
        System.out.println("==================================================================\n");
        System.out.println("Total Gross Pay: " + this.grossPay);
        System.out.println("Total Tax: " + this.tax);
        System.out.println("Total Net Pay: " + this.netPay);
    }
}
