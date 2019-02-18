package com.DAO.repositories;

import com.cars_annot.Holder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends CrudRepository<Holder, Integer> {
    List<Holder> findAllById(Integer id);
    Holder findByLoginAndPassword(String login, String password);
    List<Holder> findAll();
    Holder findByLogin(String login);
}
