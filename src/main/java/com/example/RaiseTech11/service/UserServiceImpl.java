package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.User;
import com.example.RaiseTech11.exception.ResourceNotFoundException;
import com.example.RaiseTech11.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) {
        return this.userMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }

    @Override
    public User create(String name, String birthday) {
        User user = new User(name, birthday);
        userMapper.insert(user);
        return user;
    }

    @Override
    public void update(int id, String name, String birthday) {
        User user = userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        userMapper.update(id, name, birthday);
    }

    @Override
    public void delete(int id) {
        userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        userMapper.delete(id);
    }
}
