package su.soviet.loanMVC.service;

import su.soviet.loanMVC.model.User;

public interface UserService {
    User getUser(Long id);

    String assessLoan(Long id);
}
