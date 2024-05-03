package su.soviet.loanMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import su.soviet.loanMVC.configs.LoanConfig;
import su.soviet.loanMVC.dao.User;

@Service
@PropertySource("application.yml")
public class LoanServiceImpl implements LoanService {

    @Autowired
    private UserService userService;
    @Autowired
    private LoanConfig loanConfig;
    @Autowired
    WebService webService;

    @Override
    public Integer assessLoan(Long id) {
        User user = userService.getUser(id);
        if (assessUserProperty(user) || assessUserIncome(webService.getIncome(id))) {
            return calculateLoanAmount(user);
        }
        return 0;
    }

    private boolean assessUserProperty(User user) {
        return user.getCar().getPrice() > loanConfig.getMinimalCarPrice();
    }

    private boolean assessUserIncome(int income) {
        return income > loanConfig.getMinimalIncome();
    }

    private Integer calculateLoanAmount(User user) {
        if (webService.getIncome(user.getId()) * loanConfig.getSixMonthsIncomeCoeff() >
                user.getCar().getPrice() * loanConfig.getPropertyCoeff()) {
            return webService.getIncome(user.getId()) * loanConfig.getSixMonthsIncomeCoeff();
        } else {
            return (int) (user.getCar().getPrice() * loanConfig.getPropertyCoeff());
        }
    }
}
