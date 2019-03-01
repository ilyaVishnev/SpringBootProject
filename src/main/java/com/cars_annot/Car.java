package com.cars_annot;

import com.avito.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@Entity
@Table(name = "cars")
public class Car {

    @JsonView(Views.Public.class)
    private int id;
    @JsonView(Views.Public.class)
    private int price;
    @JsonView(Views.Private.class)
    @Temporal(TemporalType.DATE)
    private LocalDate date = LocalDate.now();
    @JsonView(Views.Public.class)
    private Holder holder;
    @JsonView(Views.Public.class)
    private CarBody carBody;
    @JsonView(Views.Public.class)
    private Engine engine;
    @JsonView(Views.Public.class)
    private Gearbox gearbox;
    @JsonView(Views.Public.class)
    private String description;
    @JsonView(Views.Public.class)
    private Boolean status;
    @JsonView(Views.Public.class)
    private String photo;
    @JsonView(Views.Private.class)
    private int year;

    public Car() {
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_holder", referencedColumnName = "id")
    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cb", referencedColumnName = "id")
    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBodyA) {
        this.carBody = carBodyA;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_en", referencedColumnName = "id")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engineA) {
        this.engine = engineA;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_box", referencedColumnName = "id")
    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }


    @Override
    public String toString() {
        return "number" + this.getId();
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "create_date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (this.getPrice() == car.getPrice()) {
            return true;
        }
        return false;
    }
}
