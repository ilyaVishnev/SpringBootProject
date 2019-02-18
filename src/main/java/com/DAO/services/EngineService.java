package com.DAO.services;

import com.DAO.repositories.EngineRepository;
import com.cars_annot.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public List<Engine> findAll(){
        return engineRepository.findAll();
    }
}
