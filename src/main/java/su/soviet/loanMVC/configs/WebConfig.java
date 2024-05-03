package su.soviet.loanMVC.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("web")
public class WebConfig {
    private String incomeUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getIncomeUrl() {
        return incomeUrl;
    }

    public void setIncomeUrl(String incomeUrl) {
        this.incomeUrl = incomeUrl;
    }
}
