package com.DAO.services;

import com.DAO.repositories.CarRepository;
import com.cars_annot.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAllByDateAndPhotoAndBrand(Integer brandId) {
        return carRepository.findAllByDateAndPhotoAndBrand(brandId);
    }

    public List<Car> findAllByBrandAndPhoto(Integer brandId) {
        return carRepository.findAllByBrandAndPhoto(brandId);
    }

    public List<Car> findAllByBrandAndDate(Integer brandId) {
        return carRepository.findAllByBrandAndDate(brandId);
    }

    public List<Car> findAllByDateAndPhoto() {
        return carRepository.findAllByDateAndPhoto();
    }

    public List<Car> findAllByBrand(Integer brandId) {
        return carRepository.findAllByBrand(brandId);
    }

    public List<Car> findAllByPhoto() {
        return carRepository.findAllByPhoto();
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public Car findById(Integer id) {
        return carRepository.findById(id).get();
    }

    public List<Car> findAllByDate() {
        return carRepository.findAllByDate();
    }
}
