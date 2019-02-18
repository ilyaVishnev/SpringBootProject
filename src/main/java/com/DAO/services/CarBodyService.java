package com.DAO.services;

import com.DAO.repositories.CarBodyRepository;
import com.cars_annot.CarBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBodyService {
    @Autowired
    private CarBodyRepository carBodyRepository;

    public List<CarBody> findAll(){
        return carBodyRepository.findAll();
    }
}
