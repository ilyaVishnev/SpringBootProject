package com.DAO.repositories;

import com.cars_annot.Engine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends CrudRepository<Engine, Integer> {
    List<Engine> findAllById(Integer id);
    List<Engine> findAll();
}
