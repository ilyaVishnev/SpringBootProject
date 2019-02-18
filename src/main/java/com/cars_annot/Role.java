package com.cars_annot;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "roles")
public class Role {
    private int id;
    private String role;
    private List<Holder> holders = new ArrayList<>();

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "roles")
    public List<Holder> getHolders() {
        return holders;
    }

    public void setHolders(List<Holder> holders) {
        this.holders = holders;
    }
}
