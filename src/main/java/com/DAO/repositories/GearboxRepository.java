package com.DAO.repositories;

import com.cars_annot.Gearbox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearboxRepository extends CrudRepository<Gearbox, Integer> {
    List<Gearbox> findAllById(Integer id);
    List<Gearbox>findAll();
}
