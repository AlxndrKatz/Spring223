package su.soviet.carsMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.soviet.carsMVC.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
