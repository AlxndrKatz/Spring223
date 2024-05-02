package su.soviet.loanMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.soviet.loanMVC.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
