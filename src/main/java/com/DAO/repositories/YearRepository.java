package com.DAO.repositories;

import com.cars_annot.Year;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearRepository extends CrudRepository<Year, Integer> {
    List<Year> findAllById(Integer id);
    List<Year> findAll();
}
