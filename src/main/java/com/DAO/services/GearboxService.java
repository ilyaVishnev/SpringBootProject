package com.DAO.services;

import com.DAO.repositories.GearboxRepository;
import com.cars_annot.Gearbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GearboxService {
    @Autowired
    private GearboxRepository gearboxRepository;

    public List<Gearbox> findAll(){
        return gearboxRepository.findAll();
    }
}
