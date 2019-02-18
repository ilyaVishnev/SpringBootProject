package com.DAO.services;

import com.DAO.repositories.HolderRepository;
import com.cars_annot.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolderService {

    @Autowired
    private HolderRepository holderRepository;

    public List<Holder> findAll() {
        return holderRepository.findAll();
    }

    public Holder findByLoginAndPassword(String login, String password) {
        return holderRepository.findByLoginAndPassword(login, password);
    }

    public Holder findByLogin(String login) {
        return holderRepository.findByLogin(login);
    }
}
