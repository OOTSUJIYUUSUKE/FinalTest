package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User create(String name, String birthday);

    void update(int id, String name, String birthday);

    void delete(int id);
}
