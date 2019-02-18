package com.DAO.repositories;

import com.cars_annot.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {
    List<Model> findAllById(Integer id);
    List<Model> findAll();
}
