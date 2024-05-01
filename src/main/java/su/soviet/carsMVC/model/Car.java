package su.soviet.carsMVC.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String manufacturer;
    @Column
    private String model;
    @Column
    private int price;
    @OneToOne(mappedBy = "car")
    private User user;

    public Car() {
    }

    public Car(String manufacturer, String model, int power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = power;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}