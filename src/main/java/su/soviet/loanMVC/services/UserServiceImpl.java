package su.soviet.loanMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.soviet.loanMVC.configs.LoanConfig;
import su.soviet.loanMVC.models.User;
import su.soviet.loanMVC.repositories.UserRepository;

import java.util.Objects;

@Service
@PropertySource("application.yml")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private LoanConfig loanConfig;

    @Autowired
    private RestTemplate template;

    @Override
    public User getUser(Long id) {
        if (repo.existsById(id)) {
            return repo.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public Integer assessLoan(Long id) {
        User user = getUser(id);
        user.setIncome(updateUserIncome(id));
        if (assessUserProperty(user) || assessUserIncome(user)) {
            return calculateLoanAmount(user);
        }
        return 0;
    }

    private Integer updateUserIncome(Long id) {
        User[] users = template.getForObject(loanConfig.getIncomeUrl(),
                User[].class);
        User dbUser = repo.findById(id).orElse(null);
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user.getIncome();
            }
        } return dbUser.getIncome();
    }

    private boolean assessUserProperty(User user) {
        return user.getCar().getPrice() > loanConfig.getMinimalCarPrice();
    }

    private boolean assessUserIncome(User user) {
        return user.getIncome() > loanConfig.getMinimalIncome();
    }

    private Integer calculateLoanAmount(User user) {
        if (user.getIncome() * loanConfig.getSixMonthsIncomeCoeff() >
                user.getCar().getPrice() * loanConfig.getPropertyCoeff()) {
            return user.getIncome() * loanConfig.getSixMonthsIncomeCoeff();
        } else {
            return (int) (user.getCar().getPrice() * loanConfig.getPropertyCoeff());
        }
    }
}