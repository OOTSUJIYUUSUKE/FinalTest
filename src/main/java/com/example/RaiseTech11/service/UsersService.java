package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.Users;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UsersService {
    List<Users> findAll();
    Users findById(int id);
    Users create(Users conversionUser, BindingResult bindingResult);
    void update(int id, Users conversionUser, BindingResult bindingResult);
    void delete(int id);
}
