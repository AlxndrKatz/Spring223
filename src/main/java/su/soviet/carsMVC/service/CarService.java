package su.soviet.carsMVC.service;

import su.soviet.carsMVC.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(Long count, String sort);
}
