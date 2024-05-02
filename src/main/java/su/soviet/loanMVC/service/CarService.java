package su.soviet.loanMVC.service;

import su.soviet.loanMVC.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(Long count, String sort);
}
