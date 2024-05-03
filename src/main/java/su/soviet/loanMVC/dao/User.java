package su.soviet.loanMVC.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    public User() {
    }

    public User(Long id, Car car, int income) {
        this.id = id;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
