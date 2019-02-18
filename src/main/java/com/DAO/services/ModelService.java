package com.DAO.services;

import com.DAO.repositories.ModelRepository;
import com.cars_annot.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> findAll(){
        return modelRepository.findAll();
    }
}
