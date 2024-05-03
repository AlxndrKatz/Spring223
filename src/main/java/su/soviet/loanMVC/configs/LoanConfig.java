package su.soviet.loanMVC.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("loan")
public class LoanConfig {
    private int minimalIncome;
    private int minimalCarPrice;
    private int sixMonthsIncomeCoeff;
    private double propertyCoeff;

    public int getMinimalIncome() {
        return minimalIncome;
    }

    public void setMinimalIncome(int minimalIncome) {
        this.minimalIncome = minimalIncome;
    }

    public int getMinimalCarPrice() {
        return minimalCarPrice;
    }

    public void setMinimalCarPrice(int minimalCarPrice) {
        this.minimalCarPrice = minimalCarPrice;
    }

    public int getSixMonthsIncomeCoeff() {
        return sixMonthsIncomeCoeff;
    }

    public void setSixMonthsIncomeCoeff(int sixMonthsIncomeCoeff) {
        this.sixMonthsIncomeCoeff = sixMonthsIncomeCoeff;
    }

    public double getPropertyCoeff() {
        return propertyCoeff;
    }

    public void setPropertyCoeff(double propertyCoeff) {
        this.propertyCoeff = propertyCoeff;
    }
}
