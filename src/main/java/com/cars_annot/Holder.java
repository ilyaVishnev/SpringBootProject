package com.cars_annot;

import com.avito.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "holders")
public class Holder {
    @JsonView(Views.Public.class)
    private int id;
    @JsonView(Views.Private.class)
    private String login;
    @JsonView(Views.Private.class)
    private String password;
    @JsonView(Views.Private.class)
    private List<Role> roles = new ArrayList<>();
    @JsonView(Views.Private.class)
    private List<Car> cars = new ArrayList<>();

    public Holder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "holders_roles",
            joinColumns = {@JoinColumn(name = "id_holder")},
            inverseJoinColumns = {@JoinColumn(name = "id_roles")}
    )
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return this.getLogin() + " " + this.getPassword();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Holder other = (Holder) obj;
        if (!this.getLogin().equals(other.getLogin()))
            return false;
        if (!this.getPassword().equals(other.getPassword()))
            return false;
        return true;
    }
}
