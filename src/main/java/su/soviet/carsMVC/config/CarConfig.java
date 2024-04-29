package su.soviet.carsMVC.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("car")
public class CarConfig {
    private int maxCars;
    private String[] enableSortingFields;

    public int getMaxCars() {
        return maxCars;
    }

    public void setMaxCars(int maxCars) {
        this.maxCars = maxCars;
    }

    public String[] getEnableSortingFields() {
        return enableSortingFields;
    }

    public void setEnableSortingFields(String[] enableSortingFields) {
        this.enableSortingFields = enableSortingFields;
    }
}