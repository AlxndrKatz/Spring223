package su.soviet.loanMVC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.soviet.loanMVC.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
