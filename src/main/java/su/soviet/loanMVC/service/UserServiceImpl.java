package su.soviet.loanMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.soviet.loanMVC.config.UserConfig;
import su.soviet.loanMVC.model.User;
import su.soviet.loanMVC.repository.UserRepository;

@Service
@PropertySource("application.yml")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserConfig config;

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
    public String assessLoan(Long id) {
        updateUserIncome();
        User user = getUser(id);
        if (assessUserProperty(user) || assessUserIncome(user)) {
            return String.valueOf(calculateLoanAmount(user));
        }
        return "ОТКАЗ";
    }

    private void updateUserIncome() {
        User[] users = template.getForObject("https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income",
                User[].class);
        assert users != null;
        for (User user : users) {
            User dbUser = repo.findById(user.getId()).orElse(null);
            assert dbUser != null;
            dbUser.setIncome(user.getIncome());
            repo.save(dbUser);
        }
    }

    private boolean assessUserProperty(User user) {
        return user.getCar().getPrice() > config.getMinimalCarPrice();
    }

    private boolean assessUserIncome(User user) {
        return user.getIncome() > config.getMinimalIncome();
    }

    private Integer calculateLoanAmount(User user) {
        if (user.getIncome()*config.getSixMonthsIncomeCoeff() >
                user.getCar().getPrice()*config.getPropertyCoeff()) {
            return user.getIncome()*config.getSixMonthsIncomeCoeff();
        } else {
            return (int) (user.getCar().getPrice()* config.getPropertyCoeff());
        }
    }
}