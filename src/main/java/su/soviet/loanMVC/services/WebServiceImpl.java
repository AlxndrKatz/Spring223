package su.soviet.loanMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.soviet.loanMVC.configs.LoanConfig;
import su.soviet.loanMVC.configs.WebConfig;
import su.soviet.loanMVC.dto.UserDTO;

import java.util.Objects;

@Service
@PropertySource("application.yml")
public class WebServiceImpl implements WebService {

    @Autowired
    private WebConfig webConfig;
    @Autowired
    private RestTemplate template;

    @Override
    public int getIncome(Long id) {
        UserDTO[] users = template.getForObject(webConfig.getIncomeUrl(), UserDTO[].class);
        for (UserDTO userDTO : users) {
            if (Objects.equals(userDTO.getId(), id)) {
                return userDTO.getIncome();
            }
        }
        return 0;
    }
}
