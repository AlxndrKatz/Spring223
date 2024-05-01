package su.soviet.carsMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import su.soviet.carsMVC.config.UserConfig;
import su.soviet.carsMVC.model.User;
import su.soviet.carsMVC.repository.UserRepository;

@Service
@PropertySource("application.yml")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserConfig config;

    @Override
    public User getUser(Long id) {
        if (repo.existsById(id)) {
            return repo.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public String assessLoan(Long id) {

        System.out.println(config.getMinimalCarPrice() + " " + config.getMinimalIncome());

        User user = getUser(id);
        if (assessUserProperty(user) || assessUserIncome(user)) {
            return String.valueOf(calculateLoanAmount(user));
        }
        return "ОТКАЗ";
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
