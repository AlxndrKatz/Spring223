package su.soviet.loanMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import su.soviet.loanMVC.client.IncomeClient;
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
    private IncomeClient incomeClient;

    @Override
    public Integer assessLoan(Long id) {
        User user = userService.getUser(id);
        int income = getIncome(id);

        if (assessCarPrice(user) || assessUserIncome(income)) {
            return calculateLoanAmount(user, income);
        }
        return 0;
    }

    private boolean assessCarPrice(User user) {
        return user.getCar().getPrice() > loanConfig.getMinimalCarPrice();
    }

    private boolean assessUserIncome(int income) {
        return income > loanConfig.getMinimalIncome();
    }

    private Integer calculateLoanAmount(User user, int income) {
        if (income * loanConfig.getSixMonthsIncomeCoeff() >
                user.getCar().getPrice() * loanConfig.getPropertyCoeff()) {
            return income * loanConfig.getSixMonthsIncomeCoeff();
        } else {
            return (int) (user.getCar().getPrice() * loanConfig.getPropertyCoeff());
        }
    }

    private int getIncome(Long id) {
        return incomeClient.getIncome(id);
    }
}
