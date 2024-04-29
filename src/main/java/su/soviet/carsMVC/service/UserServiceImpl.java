package su.soviet.carsMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import su.soviet.carsMVC.config.UserConfig;
import su.soviet.carsMVC.model.User;
import su.soviet.carsMVC.repository.UserRepository;

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
        if (assessUserProperty(id) || assessUserIncome(id)) {
            return String.valueOf(calculateLoanAmount(id));
        }
        return "ОТКАЗ";
    }

    private boolean assessUserProperty(Long id) {
        return getUser(id).getCar().getPrice() > config.getMinimalCarPrice();
    }

    private boolean assessUserIncome(Long id) {
        return getUser(id).getIncome() > config.getMinimalIncome();
    }

    private Integer calculateLoanAmount(Long id) {
        if (getUser(id).getIncome()*config.getIncomeCoeff() >
                getUser(id).getCar().getPrice()*config.getPropertyCoeff()) {
            return getUser(id).getIncome()*config.getIncomeCoeff();
        } else {
            return (int) (getUser(id).getCar().getPrice()* config.getPropertyCoeff());
        }
        //return (getUser(id).getIncome()*config.getIncomeCoeff() > getUser(id).getCar().getPrice()*config.getPropertyCoeff()) ? getUser(id).getIncome()*config.getIncomeCoeff() : (int) (getUser(id).getCar().getPrice()* config.getPropertyCoeff());
    }
}
