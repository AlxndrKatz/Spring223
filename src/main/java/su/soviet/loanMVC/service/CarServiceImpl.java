package su.soviet.loanMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import su.soviet.loanMVC.config.CarConfig;
import su.soviet.loanMVC.exceptions.ApplicationException;
import su.soviet.loanMVC.model.Car;
import su.soviet.loanMVC.repository.CarRepository;

import java.util.Arrays;
import java.util.List;

@Service
@PropertySource("application.yml")
public class CarServiceImpl implements CarService {

    @Autowired
    CarConfig carConfig;

    @Autowired
    private CarRepository repo;

    @Override
    public List<Car> getCars(Long count, String sort) {

        count = checkCount(count);
        sort = checkSort(sort);
        if (sort == null) {
            return repo
                    .findAll(PageRequest
                            .of(0, Math.toIntExact(count)))
                    .getContent();
        }
        return repo
                .findAll(PageRequest
                        .of(0, Math.toIntExact(count), Sort.by(Sort.Order.asc(sort))))
                .getContent();
    }

    private Long checkCount(Long count) {
        if (count == null || count == 0 || count > carConfig.getMaxCars()) {
            count = (long) Integer.MAX_VALUE;
        }
        return count;
    }

    private String checkSort(String sort) {
        if (sort == null) {
            return null;
        }
        if (!Arrays.asList(carConfig.getEnableSortingFields()).contains(sort)) {
            throw new ApplicationException();
        }
        return sort;
    }
}
