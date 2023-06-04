package com.example.RaiseTech11.mapper;

import com.example.RaiseTech11.entity.UsersForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UsersMapper {
    List<UsersForm> findAll();
    Optional<UsersForm> findById(int id);
    int insert (UsersForm conversionUser);
    void update(int id, UsersForm conversionUser);
    void delete(int id);
}
