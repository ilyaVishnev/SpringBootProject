package com.DAO.repositories;

import com.cars_annot.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BrandRepository extends CrudRepository<Brand,Integer> {
    List<Brand> findAllById(Integer id);

    List<Brand> findAll();
}
