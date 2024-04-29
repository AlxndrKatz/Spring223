package su.soviet.carsMVC.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user")
public class UserConfig {
    private int minimalIncome;
    private  int minimalCarPrice;
    private int incomeCoeff;
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

    public int getIncomeCoeff() {
        return incomeCoeff;
    }

    public void setIncomeCoeff(int incomeCoeff) {
        this.incomeCoeff = incomeCoeff;
    }

    public double getPropertyCoeff() {
        return propertyCoeff;
    }

    public void setPropertyCoeff(double propertyCoeff) {
        this.propertyCoeff = propertyCoeff;
    }
}
