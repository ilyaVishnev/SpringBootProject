package com.DAO.repositories;

import com.cars_annot.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query("from Car c where c.engine.model.brand.id=:brandId")
    List<Car> findAllByBrand(@Param("brandId") Integer brandId);

    @Query("from Car c where c.photo not like 'null'")
    List<Car> findAllByPhoto();

    @Query("from Car c where c.date = current_date ")
    List<Car> findAllByDate();

    @Query("from Car c where c.engine.model.brand.id=:brandId and c.photo not like 'null' and c.date = current_date ")
    List<Car> findAllByDateAndPhotoAndBrand(@Param("brandId") Integer brandId);

    @Query("from Car c where c.engine.model.brand.id=:brandId and c.date = current_date ")
    List<Car> findAllByBrandAndDate(@Param("brandId") Integer brandId);

    @Query("from Car c where c.engine.model.brand.id=:brandId and c.photo not like 'null' ")
    List<Car> findAllByBrandAndPhoto(@Param("brandId") Integer brandId);

    @Query("from Car c where c.date = current_date and c.photo not like 'null' ")
    List<Car> findAllByDateAndPhoto();

    @Query("from Car c")
    List<Car> findAll();
}
