package Providers;

import Exceptions.CodeNotFoundException;
import Exceptions.StatusNotFoundException;

/**
 * Manages rates for employees such as hourly rates and tax rates based on their dependent status.
 */
public class Rates {
    // Employee code and their respective rate per hour
    private final String[][] employeeRatePerHour = {
        {"A", "250.00"},
        {"B", "320.00"},
        {"C", "450.00"}
    };

    // Employee tax rates based on dependent status
    private final String[][] employeeTaxRate = {
        {"With Dependent", "0.1"}, // 10% tax rate
        {"Without Dependent", "0.2"} // 20% tax rate
    };

    /**
     * Retrieves the rate per hour based on the provided employee code.
     * @param code Employee code
     * @return Rate per hour corresponding to the provided code
     * @throws CodeNotFoundException When the provided code is not found in the rate list
     */
    public String getRateByCode(String code) throws CodeNotFoundException {
        for (String[] ratePerHour : employeeRatePerHour) {
            if (ratePerHour[0].equals(code)) {
                return ratePerHour[1];
            }
        }
        throw new CodeNotFoundException("Code not found!");
    }

    /**
     * Retrieves the tax rate based on the provided dependent status.
     * @param dependentType Dependent status of the employee
     * @return Tax rate corresponding to the provided dependent status
     * @throws StatusNotFoundException When the provided dependent status doesn't have a corresponding tax rate
     */
    public String getTaxRateByStatus(String dependentType) throws StatusNotFoundException {
        for (String[] taxRate : employeeTaxRate) {
            if (taxRate[0].equals(dependentType)) {
                return taxRate[1];
            }
        }
        throw new StatusNotFoundException("Tax Rate not found!");
    }
}
