package su.soviet.loanMVC.services;

import su.soviet.loanMVC.models.User;

public interface UserService {
    User getUser(Long id);

    Integer assessLoan(Long id);
}
