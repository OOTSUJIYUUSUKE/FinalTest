package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User create(String name, String birthday,  BindingResult bindingResult);
    void update(int id, String name, String birthday, BindingResult bindingResult);
    void delete(int id);
}
