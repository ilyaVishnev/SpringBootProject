package com.DAO.repositories;

import com.cars_annot.CarBody;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {
    List<CarBody> findAllById(Integer id);
    List<CarBody> findAll();
}
