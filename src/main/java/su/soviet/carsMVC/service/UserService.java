package su.soviet.carsMVC.service;

import su.soviet.carsMVC.model.User;

public interface UserService {
    User getUser(Long id);

    String assessLoan(Long id);
}
