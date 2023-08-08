package com.example.RaiseTech11.mapper;

import com.example.RaiseTech11.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UsersMapper {
    List<Users> findAll();
    Optional<Users> findById(int id);
    int insert (Users conversionUser);
    void update(int id, Users conversionUser);
    void delete(int id);
}
