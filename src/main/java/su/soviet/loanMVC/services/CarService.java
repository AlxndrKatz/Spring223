package su.soviet.loanMVC.services;

import su.soviet.loanMVC.models.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(Long count, String sort);
}
