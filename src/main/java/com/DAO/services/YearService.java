package com.DAO.services;

import com.DAO.repositories.YearRepository;
import com.cars_annot.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService {
    @Autowired
    private YearRepository yearRepository;

    public List<Year> findAll() {
        return yearRepository.findAll();
    }
}
