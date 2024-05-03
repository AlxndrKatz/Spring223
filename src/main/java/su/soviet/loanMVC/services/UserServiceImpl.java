package su.soviet.loanMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import su.soviet.loanMVC.dao.User;
import su.soviet.loanMVC.repositories.UserRepository;

@Service
@PropertySource("application.yml")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User getUser(Long id) {
        if (repo.existsById(id)) {
            return repo.findById(id).orElse(null);
        }
        return null;
    }
}