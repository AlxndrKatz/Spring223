package su.soviet.carsMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.soviet.carsMVC.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
