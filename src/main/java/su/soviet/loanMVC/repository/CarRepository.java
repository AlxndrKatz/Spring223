package su.soviet.loanMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.soviet.loanMVC.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
