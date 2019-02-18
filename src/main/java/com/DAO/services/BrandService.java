package com.DAO.services;

import com.DAO.repositories.BrandRepository;
import com.cars_annot.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
}
