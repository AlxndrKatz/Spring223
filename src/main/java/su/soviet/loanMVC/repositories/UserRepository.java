package su.soviet.loanMVC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.soviet.loanMVC.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
