package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.UsersForm;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UsersService {
    List<UsersForm> findAll();
    UsersForm findById(int id);
    UsersForm create(UsersForm conversionUser, BindingResult bindingResult);
    void update(int id, UsersForm conversionUser, BindingResult bindingResult);
    void delete(int id);
}
