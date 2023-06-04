package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.UsersForm;
import com.example.RaiseTech11.exception.ResourceNotFoundException;
import com.example.RaiseTech11.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
    final private UsersMapper usersMapper;
    @Override
    public List<UsersForm> findAll() {
        return usersMapper.findAll();
    }
    @Override
    public UsersForm findById(int id) {
        return this.usersMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }
    @Override
    public UsersForm create(UsersForm conversionUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceNotFoundException("not be validated");
        }
        usersMapper.insert(conversionUser);
        return conversionUser;
    }
    @Override
    public void update(int id, UsersForm conversionUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceNotFoundException("not be validated");
        }
        usersMapper.update(id, conversionUser);
    }
    @Override
    public void delete(int id) {
        usersMapper.delete(id);
    }
}
